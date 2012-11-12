package com.biomed.server.handlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.inject.Inject;

import com.biomed.server.EMailSender;
import com.biomed.server.dispatch.ActionHandler;
import com.biomed.server.dispatch.ExecutionContext;
import com.biomed.shared.api.EmptyResult;
import com.biomed.shared.api.SendMessageAction;
import com.google.inject.Provider;

public class SendMessageHandler implements ActionHandler<SendMessageAction, EmptyResult> {

  private static final String QUERY = "SELECT first_name, last_name, email FROM user WHERE id = ?";

  @Inject
  EMailSender emailSender;

  @Inject
  Provider<Connection> connectionProvider;

  @Override
  public Class<SendMessageAction> getType() {
    return SendMessageAction.class;
  }

  @Override
  public EmptyResult execute(SendMessageAction action, ExecutionContext context) throws Exception {

    Connection connection = connectionProvider.get();

    String emailAddress;
    String name;

    try {
      PreparedStatement query = connection.prepareStatement(QUERY);
      query.setInt(1, action.getUserId());
      ResultSet resultSet = query.executeQuery();
      resultSet.first();

      emailAddress = resultSet.getString("email");
      name = resultSet.getString("first_name") + " " + resultSet.getString("last_name");
    } finally {
      connection.close();
    }

    String subject = "Message for: " + name + "\n";

    String message = "";

    if (action.hasName()) {
      message += "Name: " + action.getName() + "\n";
    }

    if (action.hasCompany()) {
      message += "Company: " + action.getCompany() + "\n";
    }

    if (action.hasPhone()) {
      message += "Phone: " + action.getPhone() + "\n";
    }

    if (action.hasPhoneExtension()) {
      message += "Extension: " + action.getPhoneExtension() + "\n";
    }

    message += "\n\n";

    if (action.hasTimeToReturnCall()) {
      message += "Time to return call: " + action.getTimeToReturnCall() + "\n";
    }

    if (action.hasTelephoned()) {
      message += "Telephoned\n";
    }

    if (action.hasCameToSeeYou()) {
      message += "Came to see you\n";
    }

    if (action.hasWantsToSeeYou()) {
      message += "Wants tp see you\n";
    }

    if (action.hasReturnedYourCall()) {
      message += "Returned your call\n";
    }

    if (action.hasPleaseCall()) {
      message += "Please call\n";
    }

    if (action.hasWillCallAgain()) {
      message += "Will call again\n";
    }

    if (action.hasRush()) {
      message += "Rush\n";
    }

    if (action.hasSpecialAttention()) {
      message += "Special attention\n";
    }

    emailSender.sendMessage(emailAddress, subject, message);

    return new EmptyResult();
  }
}
