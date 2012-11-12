package com.biomed.server.dispatch;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.biomed.shared.dispatch.DispatchAction;
import com.biomed.shared.dispatch.ActionException;
import com.biomed.shared.dispatch.DispatchService;
import com.biomed.shared.dispatch.DispatchResult;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@Singleton
public class DispatchServiceServlet extends RemoteServiceServlet implements DispatchService {
  private final Dispatch dispatch;

  @Inject
  public DispatchServiceServlet(Dispatch dispatch) {
    this.dispatch = dispatch;
  }

  public DispatchResult execute(DispatchAction<?> action) throws ActionException {
    try {
      return dispatch.execute(action);
    } catch (RuntimeException e) {
      log("Exception while executing " + action.getClass().getName() + ": " + e.getMessage(), e);
      throw e;
    }
  }
}