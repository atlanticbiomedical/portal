package com.biomed.shared.api;

import com.biomed.shared.api.dto.Entity;
import com.biomed.shared.dispatch.DispatchResult;

public class SingleResult<T extends Entity> implements DispatchResult {

  private T result;

  public SingleResult() { }

  public SingleResult(T result) {
    this.result = result;
  }

  public T getResult() {
    return result;
  }

  public void setResult(T result) {
    this.result = result;
  }
}
