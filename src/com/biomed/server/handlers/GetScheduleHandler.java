package com.biomed.server.handlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.inject.Inject;

import com.biomed.server.AbstractSqlHandler;
import com.biomed.server.dispatch.ExecutionContext;
import com.biomed.shared.api.GetScheduleAction;
import com.biomed.shared.api.SqlResultSet;
import com.google.inject.Provider;

public class GetScheduleHandler extends AbstractSqlHandler<GetScheduleAction, SqlResultSet> {

  private static final String QUERY =
      "SELECT " + 
        "w.id as workorder_id," +
        "w.job_date as job_date," +
        "w.job_start as job_start," +
        "w.job_end as job_end," +
        "w.client_id as client_id," +
        "c.client_identification as client_identification," +
        "c.client_name as client_name," +
        "c.address as client_address," +
        "c.address_2 as client_address_2," +
        "c.city as client_city," +
        "c.state as client_state," +
        "c.zip as client_zip," +
        "u.id as tech_id," +
        "u.first_name as tech_first_name," +
        "u.last_name as tech_last_name" +
     " FROM " +
        "workorder as w," +
        "client as c," +
        "user as u" +
     " WHERE " +
        "w.client_id = c.id AND " +
        "w.tech = u.id AND " +
        "w.deleted = 0 ";

  private final Provider<Connection> connectionProvider;

  @Inject
  public GetScheduleHandler(Provider<Connection> connectionProvider) {
    this.connectionProvider = connectionProvider;
  }

  @Override
  public Class<GetScheduleAction> getType() {
    return GetScheduleAction.class;
  }

  @Override
  public SqlResultSet execute(GetScheduleAction action, ExecutionContext context) throws Exception {
    Connection conn = connectionProvider.get();

    try {
      PreparedStatement query = generateQuery(conn, action);

      ResultSet resultSet = query.executeQuery();
      return new SqlResultSet(resultSetToList(resultSet));

    } finally {
      conn.close();
    }
  }

  private static PreparedStatement generateQuery(Connection conn, GetScheduleAction action)
      throws SQLException {

    String where = " AND w.job_date BETWEEN ? AND ?";

    if (action.techId != 0) {
      where += " AND w.tech = ?";
    }

    PreparedStatement query = conn.prepareStatement(QUERY + where);
    query.setString(1, action.startDate);
    query.setString(2, action.endDate);

    if (action.techId != 0) {
      query.setInt(3, action.techId);
    }

    return query;
  }
}
