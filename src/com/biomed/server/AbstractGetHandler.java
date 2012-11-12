package com.biomed.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.inject.Inject;

import com.biomed.server.dispatch.ActionHandler;
import com.biomed.server.dispatch.ExecutionContext;
import com.biomed.shared.api.SingleResult;
import com.biomed.shared.api.dto.Entity;
import com.biomed.shared.dispatch.DispatchAction;
import com.google.inject.Provider;

public abstract class AbstractGetHandler<A extends DispatchAction<SingleResult<T>>, T extends Entity>
    implements ActionHandler<A, SingleResult<T>> {

  @Inject
  Provider<Connection> connectionProvider;

  @Override
  public SingleResult<T> execute(A action, ExecutionContext context) throws Exception {
    Connection connection = connectionProvider.get();

    try {
      PreparedStatement query = createQuery(connection, action);
      ResultSet resultSet = query.executeQuery();
      if (resultSet.first()) {
        return new SingleResult<T>(prepareResult(resultSet, action));
      } else {
        return new SingleResult<T>(null);
      }
    } finally {
      connection.close();
    }
  }

  protected abstract PreparedStatement createQuery(Connection connection, A action)
      throws SQLException;

  protected abstract T prepareResult(ResultSet resultSet, A action) throws SQLException;

}
