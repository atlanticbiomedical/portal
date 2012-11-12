package com.biomed.shared.api;

import com.biomed.shared.dispatch.DispatchResult;

public class SqlResult implements DispatchResult {

  public PropertyBag result;

  public SqlResult() { }

  public SqlResult(PropertyBag result) {
    this.result = result;
  }
}
