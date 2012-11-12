package com.biomed.shared.api;

import java.util.List;

import com.biomed.shared.dispatch.DispatchResult;

public class SqlMutateResult implements DispatchResult {

  public List<Integer> ids;

  public SqlMutateResult() { }

  public SqlMutateResult(List<Integer> ids) {
    this.ids = ids;
  }
}
