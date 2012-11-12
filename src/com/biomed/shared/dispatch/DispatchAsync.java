package com.biomed.shared.dispatch;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DispatchAsync {
  <A extends DispatchAction<R>, R extends DispatchResult> void execute( A action, AsyncCallback<R> callback );
}