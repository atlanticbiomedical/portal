package com.biomed.shared.dispatch;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DispatchServiceAsync {
  void execute( DispatchAction<?> action, AsyncCallback<DispatchResult> callback );
}