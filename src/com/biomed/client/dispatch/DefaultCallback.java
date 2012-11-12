package com.biomed.client.dispatch;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class DefaultCallback<T> implements AsyncCallback<T> {
  @Override
  public void onFailure(Throwable caught) {
    throw new RuntimeException(caught);
  }
}
