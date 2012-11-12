package com.biomed.server.handlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.inject.Inject;

import com.biomed.server.CalendarApi;
import com.biomed.server.dispatch.ActionHandler;
import com.biomed.server.dispatch.ExecutionContext;
import com.biomed.shared.api.DeleteWorkorderAction;
import com.biomed.shared.api.EmptyResult;
import com.google.inject.Provider;

public class DeleteWorkorderHandler implements ActionHandler<DeleteWorkorderAction, EmptyResult> {

  private static final String QUERY = "SELECT google_event_id FROM workorder WHERE id = ?";

  private static final String DELETE_QUERY =
      "UPDATE workorder SET " +
        "deleted = true, " +
        "google_event_id = NULL " +
      "WHERE " +
        "id = ?";

  @Inject
  Provider<Connection> connectionProvider;

  @Inject
  CalendarApi calendarApi;

  @Override
  public Class<DeleteWorkorderAction> getType() {
    return DeleteWorkorderAction.class;
  }

  @Override
  public EmptyResult execute(DeleteWorkorderAction action, ExecutionContext context)
      throws Exception {

    Connection connection = connectionProvider.get();

    int workorderId = action.getWorkorderId();

    String eventId = getEventId(connection, workorderId);

    if (eventId != null) {
      calendarApi.deleteEvent(eventId);
    }

    deleteWorkorder(connection, workorderId);

    return new EmptyResult();
  }

  private static String getEventId(Connection connection, int workorderId)
      throws SQLException {

    PreparedStatement query = connection.prepareStatement(QUERY);
    query.setInt(1, workorderId);
    query.execute();

    ResultSet results = query.getResultSet();
    results.first();
    return results.getString("google_event_id");
  }

  private static void deleteWorkorder(Connection connection, int workorderId)
      throws SQLException {

    PreparedStatement query = connection.prepareStatement(DELETE_QUERY);
    query.setInt(1, workorderId);
    query.execute();

  }
}
