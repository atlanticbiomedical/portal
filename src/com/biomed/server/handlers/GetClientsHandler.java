package com.biomed.server.handlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.biomed.server.AbstractSelectHandler;
import com.biomed.shared.api.GetClientsAction;
import com.biomed.shared.api.GetClientsAction.View;
import com.biomed.shared.api.dto.ClientDTO;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

public class GetClientsHandler extends AbstractSelectHandler<GetClientsAction, ClientDTO> {

  private static Map<View, List<String>> views = ImmutableMap.<View, List<String>>builder()
      .put(View.NAMES_ONLY, ImmutableList.<String>builder()
          .add("id")
          .add("client_identification")
          .add("client_name")
          .build())
      .put(View.CLIENT_PREVIEW, ImmutableList.<String>builder()
          .add("id")
          .add("client_identification")
          .add("client_name")
          .add("attn")
          .add("phone")
          .build())
      .build();

  @Override
  public Class<GetClientsAction> getType() {
    return GetClientsAction.class;
  }

  @Override
  protected PreparedStatement createQuery(Connection connection, GetClientsAction action)
      throws SQLException {

    String sql = "SELECT " + Joiner.on(",").join(views.get(action.getView())) + " FROM client";

    String filter = action.getFilter();
    boolean hasFilter = filter != null && !filter.isEmpty();

    if (hasFilter) {
      sql += " WHERE client_identification LIKE ? OR client_name LIKE ? OR attn LIKE ? OR phone LIKE ?";
    }

    PreparedStatement query = connection.prepareStatement(sql);

    if (hasFilter) {
      filter = "%" + filter + "%";
      query.setString(1, filter);
      query.setString(2, filter);
      query.setString(3, filter);
      query.setString(4, filter);
    }

    return query;
  }

  @Override
  protected ArrayList<ClientDTO> prepareResults(ResultSet resultSet, GetClientsAction action)
      throws SQLException {
    ArrayList<ClientDTO> results = Lists.newArrayList();

    List<String> columns = views.get(action.getView());

    while (resultSet.next()) {
      ClientDTO client = new ClientDTO();

      if (columns.contains("id")) {
        client.setId(resultSet.getInt("id"));
      }

      if (columns.contains("client_identification")) {
        client.setIdentifier(resultSet.getString("client_identification"));
      }

      if (columns.contains("client_name")) {
        client.setName(resultSet.getString("client_name"));
      }

      if (columns.contains("attn")) {
        client.setPrimaryContactName(resultSet.getString("attn"));
      }

      if (columns.contains("phone")) {
        client.setPrimaryPhone(resultSet.getString("phone"));
      }

      results.add(client);
    }

    return results;
  }
}
