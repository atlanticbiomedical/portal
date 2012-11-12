package com.biomed.shared.dispatch;

public class UnsupportedActionException extends ActionException {

  UnsupportedActionException() {
  }

  @SuppressWarnings("unchecked")
  public UnsupportedActionException(DispatchAction<? extends DispatchResult> action) {
    this((Class<? extends DispatchAction<? extends DispatchResult>>) action.getClass());
  }

  public UnsupportedActionException(Class<? extends DispatchAction<? extends DispatchResult>> actionClass) {
    super("No handler is registered for " + actionClass.getName());
  }
}
