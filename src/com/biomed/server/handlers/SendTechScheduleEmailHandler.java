package com.biomed.server.handlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.inject.Inject;

import com.biomed.server.EMailSender;
import com.biomed.server.dispatch.ActionHandler;
import com.biomed.server.dispatch.ExecutionContext;
import com.biomed.shared.api.EmptyResult;
import com.biomed.shared.api.SendTechScheduleEmailAction;
import com.biomed.shared.api.dto.WorkorderReasonDTO;
import com.google.inject.Provider;

public class SendTechScheduleEmailHandler implements ActionHandler<SendTechScheduleEmailAction, EmptyResult> {

  private static final String TECH_QUERY = "SELECT first_name, last_name, email FROM user where id = ?";
  private static final String WORKORDER_QUERY =
    "SELECT " +
      "c.client_name, " +
      "c.address, " +
      "c.city, " +
      "c.state, " +
      "c.zip, " +
      "c.notes, " +
      "w.job_start, " +
      "w.job_end, " +
      "w.reason, " +
      "w.caller, " +
      "w.remarks " +
    "FROM " +
      "client AS c, " +
      "workorder AS w " +
    "WHERE " +
      "w.client_id = c.id AND " +
      "w.tech = ? AND " +
      "w.job_date = ? " +
    "ORDER BY " +
      "w.job_start";

  @Inject
  EMailSender emailSender;

  @Inject
  Provider<Connection> connectionProvider;

  @Override
  public Class<SendTechScheduleEmailAction> getType() {
    return SendTechScheduleEmailAction.class;
  }

  @Override
  public EmptyResult execute(SendTechScheduleEmailAction action, ExecutionContext context)
      throws Exception {

    String date = SqlUtil.formatDate(action.getDate());

    Connection connection = connectionProvider.get();
    PreparedStatement techQuery = connection.prepareStatement(TECH_QUERY);
    techQuery.setInt(1, action.getUserId());
    ResultSet techResultSet = techQuery.executeQuery();
    techResultSet.first();

    String emailAddress = techResultSet.getString("email");
    String name = techResultSet.getString("first_name") + " " + techResultSet.getString("last_name");

    String subject = "[Work Schedule] " + date;

    StringBuilder messageBuilder = new StringBuilder();
    messageBuilder.append("Work schedule for " + name + " on " + date + "\n");

    PreparedStatement workorderQuery = connection.prepareStatement(WORKORDER_QUERY);
    workorderQuery.setInt(1, action.getUserId());
    workorderQuery.setString(2, date);
    ResultSet workorderResultSet = workorderQuery.executeQuery();

    while (workorderResultSet.next()) {
      messageBuilder.append(workorderResultSet.getString("client_name") + "\n");
      messageBuilder.append("From: ");
      messageBuilder.append(formatStartEndTime(workorderResultSet.getInt("job_start")));
      messageBuilder.append(" to ");
      messageBuilder.append(formatStartEndTime(workorderResultSet.getInt("job_end")));
      messageBuilder.append("\n");
      messageBuilder.append("Location:\n");
      messageBuilder.append(workorderResultSet.getString("address"));
      messageBuilder.append("\n");
      messageBuilder.append(workorderResultSet.getString("city"));
      messageBuilder.append(" ");
      messageBuilder.append(workorderResultSet.getString("state"));
      messageBuilder.append(". ");
      messageBuilder.append(workorderResultSet.getString("zip"));
      messageBuilder.append("\nReason:\n");
      messageBuilder.append(WorkorderReasonDTO.getById(workorderResultSet.getInt("reason")).getLabel());

      if (workorderResultSet.getString("caller") != null) {
        messageBuilder.append("\nRequested By:\n");
        messageBuilder.append(workorderResultSet.getString("caller"));
      }
      if (workorderResultSet.getString("remarks") != null) {
        messageBuilder.append("\nRemarks:\n");
        messageBuilder.append(workorderResultSet.getString("remarks"));
      }
      if (workorderResultSet.getString("notes") != null) {
        messageBuilder.append("\nNotes:\n");
        messageBuilder.append(workorderResultSet.getString("notes"));
      }

      messageBuilder.append("\n\n\n");
    }

    connection.close();

    emailSender.sendMessage(emailAddress, subject, messageBuilder.toString());

    return new EmptyResult();
  }

  private static String formatStartEndTime(int time) {
    int hour = time / 100;
    int min = time % 100;

    return getHour(hour) + ":" + getMin(min) + " " + getAmPm(hour);
  }

  private static String getMin(int min) {
    if (min < 10) {
      return "0" + min;
    } else {
      return Integer.toString(min);
    }
  }

  private static String getHour(int hour) {
    return Integer.toString(hour > 12 ? (hour - 12) : hour);
  }

  private static String getAmPm(int hour) {
    return hour > 11 ? "PM" : "AM";
  }
}
