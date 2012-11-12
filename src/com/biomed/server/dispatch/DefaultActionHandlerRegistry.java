package com.biomed.server.dispatch;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

import com.biomed.shared.dispatch.DispatchAction;
import com.biomed.shared.dispatch.DispatchResult;

@Singleton
public class DefaultActionHandlerRegistry implements ActionHandlerRegistry {

  private final Map<Class<? extends DispatchAction<?>>, ActionHandler<?, ?>> handlers;

  public DefaultActionHandlerRegistry() {
    handlers = new HashMap<Class<? extends DispatchAction<?>>, ActionHandler<?, ?>>(100);
  }

  public void addHandler(ActionHandler<?, ?> handler) {
    handlers.put(handler.getType(), handler);
  }

  public boolean removeHandler(ActionHandler<?, ?> handler) {
    return handlers.remove(handler.getType()) != null;
  }

  @SuppressWarnings("unchecked")
  public <A extends DispatchAction<R>, R extends DispatchResult> ActionHandler<A, R> findHandler(A action) {
    return (ActionHandler<A, R>) handlers.get(action.getClass());
  }

  public void clearHandlers() {
    handlers.clear();
  }

}
