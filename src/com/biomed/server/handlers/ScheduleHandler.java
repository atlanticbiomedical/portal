package com.biomed.server.handlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.inject.Inject;

import com.biomed.server.CalendarApi;
import com.biomed.server.dispatch.ActionHandler;
import com.biomed.server.dispatch.ExecutionContext;
import com.biomed.shared.api.EmptyResult;
import com.biomed.shared.api.ScheduleAction;
import com.google.inject.Provider;

public class ScheduleHandler implements ActionHandler<ScheduleAction, EmptyResult> {

  private static final String QUERY = 
      "INSERT INTO workorder " +
          "(job_date, client_id, tech, job_start, job_end, reason, remarks, job_status_id, job_scheduled_date, caller)" + 
          " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

  private static final String FETCH_DATA_QUERY = 
      "SELECT u.email, c.client_name, c.address, c.city, c.state, c.zip FROM user AS u, client AS c WHERE u.id = ? AND c.id = ?";

  private static final String UPDATE_EVENT_ID_QUERY =
      "UPDATE workorder SET google_event_id = ? WHERE id = ?";

  @Override
  public Class<ScheduleAction> getType() {
    return ScheduleAction.class;
  }

  @Inject
  Provider<Connection> connectionProvider;

  @Inject
  CalendarApi calendarApi;

  @Override
  public EmptyResult execute(ScheduleAction action, ExecutionContext context) throws Exception {
    Connection connection = connectionProvider.get();

    int workorderId = insertWorkorder(connection, action);
    String eventId = updateGoogleCalendar(connection, action);
    updateGoogleEventId(connection, workorderId, eventId);

    connection.close();

    return new EmptyResult();
  }

  private static int insertWorkorder(Connection connection, ScheduleAction action)
      throws SQLException {


    PreparedStatement query = connection.prepareStatement(QUERY, Statement.RETURN_GENERATED_KEYS);
    query.setString(1, SqlUtil.formatDate(action.getJobDate()));
    query.setInt(2, action.getClientId());
    query.setInt(3, action.getTechId());
    query.setInt(4, action.getStartTime().toInteger());
    query.setInt(5, action.getEndTime().toInteger());
    query.setInt(6,  action.getReason().getId());
    query.setString(7, action.getRemarks());
    query.setInt(8, action.getStatus().getId());
    query.setString(9, SqlUtil.formatDate(new Date()));
    query.setString(10, action.getRequestedBy());
    query.execute();

    ResultSet keys = query.getGeneratedKeys();
    if (keys.next()) {
      return keys.getInt(1);
    } else {
      return -1;
    }
  }

  private String updateGoogleCalendar(Connection connection, ScheduleAction action)
      throws SQLException {
    Date startDate = action.getStartTime().toDate(action.getJobDate());
    Date endDate = action.getEndTime().toDate(action.getJobDate());

    PreparedStatement query2 = connection.prepareStatement(FETCH_DATA_QUERY);
    query2.setInt(1, action.getTechId());
    query2.setInt(2, action.getClientId());
    query2.execute();

    ResultSet results = query2.getResultSet();
    results.first();

    String email = results.getString("email");
    String summary = results.getString("client_name");
    String location = results.getString("address") + " " + results.getString("city") + " " + results.getString("city") + " " + results.getString("state") + " " + results.getString("zip");
    String description = action.getReason().getLabel() + "\n" + action.getRequestedBy() + "\n" + action.getRemarks() + "\n" + action.getStatus().getLabel();

    return calendarApi.scheduleEvent(email, startDate, endDate, summary, description, location);
  }

  private static void updateGoogleEventId(Connection connection, int workorderId, String eventId)
      throws SQLException {

    PreparedStatement query = connection.prepareStatement(UPDATE_EVENT_ID_QUERY);
    query.setString(1, eventId);
    query.setInt(2, workorderId);

    query.execute();
  }

}
