package com.biomed.server.handlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.biomed.server.AbstractGetHandler;
import com.biomed.shared.api.GetWorkorderViewAction;
import com.biomed.shared.api.dto.EditWorkorderViewDTO;
import com.biomed.shared.api.dto.ScheduleTime;
import com.biomed.shared.api.dto.WorkorderReasonDTO;
import com.biomed.shared.api.dto.WorkorderStatusDTO;

public class GetWorkorderViewHandler extends
    AbstractGetHandler<GetWorkorderViewAction, EditWorkorderViewDTO> {

  private static String QUERY =
      "SELECT " +
        "w.id AS workorder_id, " +
        "w.job_scheduled_date as workorder_scheduled_date, " +
        "c.client_name as client_name, " +
        "c.address as client_address, " +
        "c.address_2 as client_address_2, " +
        "c.city as client_city, " +
        "c.state as client_state, " +
        "c.zip as client_zip, " +
        "c.phone as client_phone, " +
        "c.attn as client_primary_contact, " +
        "w.job_date as workorder_job_date, " +
        "w.job_start as workorder_start_time, " +
        "w.job_end as workorder_end_time, " +
        "w.remarks as workorder_remarks, " +
        "w.action_taken as workorder_action_taken, " +
        "w.job_status_id as workorder_status_id, " +
        "w.reason as workorder_reason_id, " +
        "w.tech as tech " +
      "FROM " +
        "workorder AS w, " +
        "client AS c " +
      "WHERE " +
        "w.client_id = c.id AND " +
        "w.id = ? ";

  @Override
  public Class<GetWorkorderViewAction> getType() {
    return GetWorkorderViewAction.class;
  }

  @Override
  protected PreparedStatement createQuery(Connection connection, GetWorkorderViewAction action)
      throws SQLException {

    String sql = QUERY;
    PreparedStatement query = connection.prepareStatement(sql);
    query.setInt(1, action.getWorkorderId());

    return query;
  }

  @Override
  protected EditWorkorderViewDTO prepareResult(ResultSet resultSet, GetWorkorderViewAction action)
      throws SQLException {

    EditWorkorderViewDTO result = new EditWorkorderViewDTO();
    result.setWorkorderId(resultSet.getInt("workorder_id"));
    result.setWorkorderScheduledDate(SqlUtil.parseDate(resultSet.getString("workorder_scheduled_date")));
    result.setClientName(resultSet.getString("client_name"));
    result.setClientAddress(resultSet.getString("client_address"));
    result.setClientAddress2(resultSet.getString("client_address_2"));
    result.setClientCity(resultSet.getString("client_city"));
    result.setClientState(resultSet.getString("client_state"));
    result.setClientZip(resultSet.getString("client_zip"));
    result.setClientPhone(resultSet.getString("client_phone"));
    result.setClientPrimaryContact(resultSet.getString("client_primary_contact"));
    result.setWorkorderJobDate(SqlUtil.parseDate(resultSet.getString("workorder_job_date")));
    result.setWorkorderStartTime(ScheduleTime.fromInteger(resultSet.getInt("workorder_start_time")));
    result.setWorkorderEndTime(ScheduleTime.fromInteger(resultSet.getInt("workorder_end_time")));
    result.setTechId(resultSet.getInt("tech"));
    result.setRemarks(resultSet.getString("workorder_remarks"));
    result.setActionTaken(resultSet.getString("workorder_action_taken"));
    result.setWorkorderStatus(WorkorderStatusDTO.getById(resultSet.getInt("workorder_status_id")));
    result.setWorkorderReason(WorkorderReasonDTO.getById(resultSet.getInt("workorder_reason_id")));
    return result;
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
