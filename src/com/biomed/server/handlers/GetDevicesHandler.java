package com.biomed.server.handlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.biomed.server.AbstractSelectHandler;
import com.biomed.shared.api.GetDevicesAction;
import com.biomed.shared.api.dto.DeviceDTO;
import com.google.common.collect.Lists;

public class GetDevicesHandler extends AbstractSelectHandler<GetDevicesAction, DeviceDTO> {

  private static final String QUERY =
    "SELECT " +
      "d.id as device_id, " +
      "d.client_id as client_id, " +
      "d.identification as identifier, " + 
      "s.device_name as device_name, " +
      "s.manufacturer as manufacturer, " +
      "s.model_number as model_number, " +
      "d.serial_number as serial_number, " +
      "d.status as status, " +
      "d.last_pm_date as last_pm_date " +
    "FROM " +
      "device AS d, " +
      "specification AS s " +
    "WHERE " + 
      "d.specification_id = s.id AND " +
      "d.client_id = ?";

  @Override
  public Class<GetDevicesAction> getType() {
    return GetDevicesAction.class;
  }

  @Override
  protected PreparedStatement createQuery(Connection connection, GetDevicesAction action)
      throws SQLException {

    String sql = QUERY;
    PreparedStatement query = connection.prepareStatement(sql);
    query.setInt(1, action.getClientId());

    return query;
  }

  @Override
  protected ArrayList<DeviceDTO> prepareResults(ResultSet resultSet, GetDevicesAction action)
      throws SQLException {
    ArrayList<DeviceDTO> results = Lists.newArrayList();

    while (resultSet.next()) {
      DeviceDTO device = new DeviceDTO();

      device.setDeviceId(resultSet.getInt("device_id"));
      device.setClientId(resultSet.getInt("client_id"));
      device.setIdentifier(resultSet.getString("identifier"));
      device.setName(resultSet.getString("device_name"));
      device.setManufacturer(resultSet.getString("manufacturer"));
      device.setModel(resultSet.getString("model_number"));
      device.setSerial(resultSet.getString("serial_number"));
      device.setStatus(resultSet.getString("status"));
      device.setLastPm(SqlUtil.parseDate(resultSet.getString("last_pm_date")));

      results.add(device);
    }

    return results;
  }

}
