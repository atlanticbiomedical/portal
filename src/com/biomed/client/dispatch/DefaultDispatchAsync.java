package com.biomed.client.dispatch;

import com.biomed.shared.dispatch.DispatchAction;
import com.biomed.shared.dispatch.DispatchAsync;
import com.biomed.shared.dispatch.DispatchService;
import com.biomed.shared.dispatch.DispatchServiceAsync;
import com.biomed.shared.dispatch.DispatchResult;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class DefaultDispatchAsync implements DispatchAsync {

  private static final DispatchServiceAsync realService = GWT.create(DispatchService.class);

  public DefaultDispatchAsync() {
  }

  public <A extends DispatchAction<R>, R extends DispatchResult> void execute(final A action,
      final AsyncCallback<R> callback) {
    realService.execute(action, new AsyncCallback<DispatchResult>() {
      public void onFailure(Throwable caught) {
        callback.onFailure(caught);
      }

      @SuppressWarnings("unchecked")
      public void onSuccess(DispatchResult result) {
        callback.onSuccess((R) result);
      }
    });
  }
}