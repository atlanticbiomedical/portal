package com.biomed.server.handlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.inject.Inject;

import com.biomed.server.CalendarApi;
import com.biomed.server.dispatch.ActionHandler;
import com.biomed.server.dispatch.ExecutionContext;
import com.biomed.shared.api.EmptyResult;
import com.biomed.shared.api.SaveWorkorderAction;
import com.google.inject.Provider;

public class SaveWorkorderHandler implements ActionHandler<SaveWorkorderAction, EmptyResult> {

  private static final String QUERY =
      "UPDATE workorder SET " +
        "job_date = ?, " + 
        "job_start = ?, " +
        "job_end = ?, " +
        "tech = ?, " +
        "job_status_id = ?, " +
        "reason = ?, " +
        "action_taken = ?, " + 
        "remarks = ? " + 
      "WHERE " +
        " id = ?";

  private static final String INFO_QUERY =
      "SELECT " +
        "w.google_event_id AS event_id, " +
        "u.email AS email " +
      "FROM " +
        "workorder AS w, " +
        "user AS u " +
      "WHERE " +
        "w.tech = u.id AND " +
        "w.id = ? ";

  @Inject
  Provider<Connection> connectionProvider;

  @Inject
  CalendarApi calendarApi;

  @Override
  public Class<SaveWorkorderAction> getType() {
    return SaveWorkorderAction.class;
  }

  @Override
  public EmptyResult execute(SaveWorkorderAction action, ExecutionContext context) throws Exception {
    Connection connection = connectionProvider.get();
    PreparedStatement query = createQuery(connection, action);
    query.execute();

    updateGoogleCalendar(connection, action);
    connection.close();

    return new EmptyResult();
  }

  protected PreparedStatement createQuery(Connection connection, SaveWorkorderAction action)
      throws SQLException {

    String sql = QUERY;
    PreparedStatement query = connection.prepareStatement(sql);
    query.setString(1, SqlUtil.formatDate(action.getJobDate()));
    query.setInt(2, action.getStartTime().toInteger());
    query.setInt(3, action.getEndTime().toInteger());
    query.setInt(4, action.getTechId());
    query.setInt(5, action.getWorkorderStatus().getId());
    query.setInt(6, action.getWorkorderReason().getId());
    query.setString(7,  action.getActionTaken());
    query.setString(8, action.getRemarks());
    query.setInt(9, action.getId());

    return query;
  }

  private void updateGoogleCalendar(Connection connection, SaveWorkorderAction action)
      throws SQLException {

    Date startDate = action.getStartTime().toDate(action.getJobDate());
    Date endDate = action.getEndTime().toDate(action.getJobDate());

    PreparedStatement query = connection.prepareStatement(INFO_QUERY);
    query.setInt(1,  action.getId());
    query.execute();

    ResultSet results = query.getResultSet();
    results.first();

    String email = results.getString("email");
    String eventId = results.getString("event_id");

    if (eventId != null) {
      calendarApi.rescheduleEvent(eventId, email, startDate, endDate);
    }
  }
}
