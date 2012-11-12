package com.biomed.server.handlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.biomed.server.AbstractMutateHandler;
import com.biomed.shared.api.SaveClientAction;
import com.biomed.shared.api.dto.ClientDTO;
import com.biomed.shared.api.dto.State;

public class SaveClientHandler extends AbstractMutateHandler<SaveClientAction> {

  private static final String INSERT_QUERY =
    "INSERT INTO client (" +
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
    ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

  private static final String UPDATE_QUERY = 
    "UPDATE client SET " +
      "client_identification = ?, " +
      "client_name = ?, " +
      "address = ?, " +
      "address_2 = ?, " +
      "city = ?, " +
      "state = ?, " +
      "zip = ?, " +
      "attn = ?," +
      "phone = ?, " +
      "email = ?, " +
      "secondary_attn = ?," +
      "secondary_phone = ?, " +
      "secondary_email = ?, " +
      "notes = ? " +
    "WHERE id = ?";


  @Override
  public Class<SaveClientAction> getType() {
    return SaveClientAction.class;
  }

  @Override
  protected PreparedStatement createQuery(Connection connection, SaveClientAction action)
      throws SQLException {

    ClientDTO client = action.getClient();
 
    String sql;
    if (client.getId() > 0) {
      sql = UPDATE_QUERY;
    } else {
      sql = INSERT_QUERY;
    }

    PreparedStatement query = connection.prepareStatement(sql);

    query.setString(1, client.getIdentifier());
    query.setString(2, client.getName());
    query.setString(3, client.getPrimaryAddress());
    query.setString(4, client.getPrimaryAddress2());
    query.setString(5, client.getPrimaryCity());
    query.setString(6, State.toString(client.getPrimaryState()));
    query.setString(7, client.getPrimaryZip());
    query.setString(8, client.getPrimaryContactName());
    query.setString(9, client.getPrimaryPhone());
    query.setString(10, client.getPrimaryEmail());
    query.setString(11, client.getSecondaryContactName());
    query.setString(12, client.getSecondaryPhone());
    query.setString(13, client.getSecondaryEmail());
    query.setString(14, client.getNotes());

    if (client.getId() > 0) {
      query.setInt(15, client.getId());
    }

    return query;
  }
}
