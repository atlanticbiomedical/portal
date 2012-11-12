package com.biomed.server.handlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.biomed.server.AbstractGetHandler;
import com.biomed.shared.api.GetClientAction;
import com.biomed.shared.api.dto.ClientDTO;
import com.biomed.shared.api.dto.State;

public class GetClientHandler extends AbstractGetHandler<GetClientAction, ClientDTO> {

  private static final String QUERY =
    "SELECT " +
      "id, " +
      "client_identification, " +
      "client_name, " +
      "address, " +
      "address_2, " +
      "city, " +
      "state, " +
      "zip, " +
      "attn, " +
      "phone, " +
      "email, " +
      "secondary_attn, " +
      "secondary_phone, " +
      "secondary_email, " +
      "notes " +
    "FROM " +
      "client " +
    "WHERE " +
      "id = ?";

  @Override
  public Class<GetClientAction> getType() {
    return GetClientAction.class;
  }

  @Override
  protected PreparedStatement createQuery(Connection connection, GetClientAction action)
      throws SQLException {

    String sql = QUERY;
    PreparedStatement query = connection.prepareStatement(sql);
    query.setInt(1, action.getClientId());

    return query;
  }

  @Override
  protected ClientDTO prepareResult(ResultSet resultSet, GetClientAction action)
      throws SQLException {

    ClientDTO client = new ClientDTO();
    client.setId(resultSet.getInt("id"));
    client.setIdentifier(resultSet.getString("client_identification"));
    client.setName(resultSet.getString("client_name"));
    client.setPrimaryAddress(resultSet.getString("address"));
    client.setPrimaryAddress2(resultSet.getString("address_2"));
    client.setPrimaryCity(resultSet.getString("city"));
    client.setPrimaryState(State.fromString(resultSet.getString("state")));
    client.setPrimaryZip(resultSet.getString("zip"));
    client.setPrimaryContactName(resultSet.getString("attn"));
    client.setPrimaryPhone(resultSet.getString("phone"));
    client.setPrimaryEmail(resultSet.getString("email"));
    client.setSecondaryContactName(resultSet.getString("secondary_attn"));
    client.setSecondaryPhone(resultSet.getString("secondary_phone"));
    client.setSecondaryEmail(resultSet.getString("secondary_email"));
    client.setNotes(resultSet.getString("notes"));
    return client;
  }
}
