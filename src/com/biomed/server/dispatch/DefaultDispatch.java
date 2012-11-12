package com.biomed.server.dispatch;

import javax.inject.Inject;

import com.biomed.shared.dispatch.DispatchAction;
import com.biomed.shared.dispatch.ActionException;
import com.biomed.shared.dispatch.DispatchResult;
import com.biomed.shared.dispatch.UnsupportedActionException;

public class DefaultDispatch implements Dispatch {

  private static class DefaultExecutionContext implements ExecutionContext {
    private final DefaultDispatch dispatch;

    private DefaultExecutionContext(DefaultDispatch dispatch) {
      this.dispatch = dispatch;
    }

    public <A extends DispatchAction<R>, R extends DispatchResult> R execute(A action)
        throws ActionException {
      return dispatch.doExecute(action, this);
    }

  };

  private final ActionHandlerRegistry handlerRegistry;

  @Inject
  public DefaultDispatch(ActionHandlerRegistry handlerRegistry) {
    this.handlerRegistry = handlerRegistry;
  }

  public <A extends DispatchAction<R>, R extends DispatchResult> R execute(A action)
      throws ActionException {
    DefaultExecutionContext ctx = new DefaultExecutionContext(this);
    return doExecute(action, ctx);
  }

  private <A extends DispatchAction<R>, R extends DispatchResult> R doExecute(A action,
      ExecutionContext ctx) throws ActionException {
    ActionHandler<A, R> handler = findHandler(action);
    try {
      return handler.execute(action, ctx);
    } catch (Throwable t) {
      t.printStackTrace();
      throw new ActionException(t);
    }
  }

  private <A extends DispatchAction<R>, R extends DispatchResult> ActionHandler<A, R> findHandler(
      A action) throws UnsupportedActionException {
    ActionHandler<A, R> handler = handlerRegistry.findHandler(action);
    if (handler == null)
      throw new UnsupportedActionException(action);

    return handler;
  }
}
