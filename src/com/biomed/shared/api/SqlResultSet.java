package com.biomed.shared.api;

import java.util.List;

import com.biomed.shared.dispatch.DispatchResult;

public class SqlResultSet implements DispatchResult  {

  public List<PropertyBag> results;

  public SqlResultSet() { }

  public SqlResultSet(List<PropertyBag> results) {
    this.results = results;
  }
}
