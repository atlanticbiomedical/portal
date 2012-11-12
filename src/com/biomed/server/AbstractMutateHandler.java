package com.biomed.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.inject.Inject;

import com.biomed.server.dispatch.ActionHandler;
import com.biomed.server.dispatch.ExecutionContext;
import com.biomed.shared.api.EmptyResult;
import com.biomed.shared.dispatch.DispatchAction;
import com.google.inject.Provider;

public abstract class AbstractMutateHandler<A extends DispatchAction<EmptyResult>> implements
    ActionHandler<A, EmptyResult> {

  @Inject
  Provider<Connection> connectionProvider;

  @Override
  public EmptyResult execute(A action, ExecutionContext context) throws Exception {
    Connection connection = connectionProvider.get();

    try {
      PreparedStatement query = createQuery(connection, action);
      query.execute();
      return new EmptyResult();
    } finally {
      connection.close();
    }
  }

  protected abstract PreparedStatement createQuery(Connection connection, A action)
      throws SQLException;
}
