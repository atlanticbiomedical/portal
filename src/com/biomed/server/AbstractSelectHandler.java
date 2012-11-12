package com.biomed.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.inject.Inject;

import com.biomed.server.dispatch.ActionHandler;
import com.biomed.server.dispatch.ExecutionContext;
import com.biomed.shared.api.ListResult;
import com.biomed.shared.api.dto.Entity;
import com.biomed.shared.dispatch.DispatchAction;
import com.google.inject.Provider;

public abstract class AbstractSelectHandler<A extends DispatchAction<ListResult<T>>, T extends Entity>
    implements ActionHandler<A, ListResult<T>> {

  @Inject
  Provider<Connection> connectionProvider;

  @Override
  public ListResult<T> execute(A action, ExecutionContext context) throws Exception {
    Connection connection = connectionProvider.get();

    try {
      PreparedStatement query = createQuery(connection, action);
      ResultSet resultSet = query.executeQuery();
      return new ListResult<T>(prepareResults(resultSet, action));
    } finally {
      connection.close();
    }
  }

  protected abstract PreparedStatement createQuery(Connection connection, A action)
      throws SQLException;

  protected abstract ArrayList<T> prepareResults(ResultSet resultSet, A action) throws SQLException;
}
