package com.biomed.server.handlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.biomed.server.AbstractSelectHandler;
import com.biomed.shared.api.GetUsersAction;
import com.biomed.shared.api.dto.UserDTO;
import com.google.common.collect.Lists;

public class GetUsersHandler extends AbstractSelectHandler<GetUsersAction, UserDTO> {

  private static final String QUERY =
      "SELECT " + 
        "id, " + 
        "first_name, " + 
        "last_name, " +
        "user_type_id " +
      "FROM " +
        "user ";

  @Override
  public Class<GetUsersAction> getType() {
    return GetUsersAction.class;
  }

  @Override
  protected PreparedStatement createQuery(Connection connection, GetUsersAction action)
      throws SQLException {

    String sql = QUERY;
    if (action.getUserTypeId() != 0) {
      sql += "WHERE user_type_id = ?";
    }

    PreparedStatement query = connection.prepareStatement(sql);

    if (action.getUserTypeId() != 0) {
      query.setInt(1, action.getUserTypeId());
    }

    return query;
  }

  @Override
  protected ArrayList<UserDTO> prepareResults(ResultSet resultSet, GetUsersAction action)
      throws SQLException {
    ArrayList<UserDTO> results = Lists.newArrayList();

    while (resultSet.next()) {
      UserDTO user = new UserDTO();

      user.setId(resultSet.getInt("id"));
      user.setFirstName(resultSet.getString("first_name"));
      user.setLastName(resultSet.getString("last_name"));
      user.setUserTypeId(resultSet.getInt("user_type_id"));

      results.add(user);
    }

    return results;
  }

}
