package com.biomed.server.handlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.biomed.server.AbstractSelectHandler;
import com.biomed.shared.api.GetWorkordersAction;
import com.biomed.shared.api.dto.WorkorderDTO;
import com.google.common.collect.Lists;

public class GetWorkordersHandler extends AbstractSelectHandler<GetWorkordersAction, WorkorderDTO> {

  private static final String QUERY =
      "SELECT " +
        "w.id as workorder_id, " +
        "w.job_date as workorder_job_date, " +
        "w.remarks as workorder_remarks, " +
        "w.reason as reason, " +
        "w.job_status_id as workorder_status_id, " +
        "c.id as client_id, " +
        "c.client_identification as client_identifier, " +
        "c.client_name as client_name, " +
        "u.id as user_id, " +
        "u.first_name as user_first_name, " +
        "u.last_name as user_last_name " +
      "FROM " +
        "workorder as w, " +
        "client as c, " +
        "user as u " +
      "WHERE " +
        "w.client_id = c.id AND " +
        "w.tech = u.id AND " +
        "w.deleted = 0 ";

  @Override
  public Class<GetWorkordersAction> getType() {
    return GetWorkordersAction.class;
  }

  @Override
  protected PreparedStatement createQuery(Connection connection, GetWorkordersAction action)
      throws SQLException {
    String sql = QUERY;

    if (action.hasStartDate() && action.hasEndDate()) {
      sql += "AND w.job_date BETWEEN ? AND ? ";
    }

    if (action.hasClientId()) {
      sql += "AND c.id = ? ";
    }

    if (action.hasTechId()) {
      sql += "AND u.id = ? ";
    }

    if (action.hasWorkorderStatusId()) {
      sql += "AND w.job_status_id = ?";
    }

    PreparedStatement query = connection.prepareStatement(sql);

    int index = 1;
    if (action.hasStartDate() && action.hasEndDate()) {
      query.setString(index, SqlUtil.formatDate(action.getStartDate()));
      index++;

      query.setString(index, SqlUtil.formatDate(action.getEndDate()));
      index++;
    }

    if (action.hasClientId()) {
      query.setInt(index, action.getClientId());
      index++;
    }

    if (action.hasTechId()) {
      query.setInt(index, action.getTechId());
      index++;
    }

    if (action.hasWorkorderStatusId()) {
      query.setInt(index, action.getWorkorderStatusId());
      index++;
    }

    return query;
  }

  @Override
  protected ArrayList<WorkorderDTO> prepareResults(ResultSet resultSet, GetWorkordersAction action)
      throws SQLException {
    ArrayList<WorkorderDTO> results = Lists.newArrayList();

    while (resultSet.next()) {
      WorkorderDTO workorder = new WorkorderDTO();

      workorder.setWorkorderId(resultSet.getInt("workorder_id"));
      workorder.setJobDate(SqlUtil.parseDate(resultSet.getString("workorder_job_date")));
      workorder.setRemarks(resultSet.getString("workorder_remarks"));
      workorder.setReason(resultSet.getString("reason"));
      workorder.setJobStatusId(resultSet.getInt("workorder_status_id"));
      workorder.setClientId(resultSet.getInt("client_id"));
      workorder.setClientIdentifier(resultSet.getString("client_identifier"));
      workorder.setClientName(resultSet.getString("client_name"));
      workorder.setTechId(resultSet.getInt("user_id"));
      workorder.setTechFirstName(resultSet.getString("user_first_name"));
      workorder.setTechLastName(resultSet.getString("user_last_name"));

      results.add(workorder);
    }

    return results;
  }
}
