package com.biomed.server.dispatch;

import com.biomed.shared.dispatch.DispatchAction;
import com.biomed.shared.dispatch.DispatchResult;

public interface ActionHandlerRegistry {
  void addHandler(ActionHandler<?, ?> handler);

  boolean removeHandler(ActionHandler<?, ?> handler);

  public <A extends DispatchAction<R>, R extends DispatchResult> ActionHandler<A, R> findHandler(A action);

  public void clearHandlers();
}