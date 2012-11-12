package com.biomed.shared.api;

import java.util.ArrayList;

import com.biomed.shared.api.dto.Entity;
import com.biomed.shared.dispatch.DispatchResult;

public class ListResult<T extends Entity> implements DispatchResult {

  private ArrayList<T> result;

  public ListResult() { }

  public ListResult(ArrayList<T> result) {
    this.result = result;
  }

  public ArrayList<T> getResult() {
    return result;
  }

  public void setResult(ArrayList<T> result) {
    this.result = result;
  }
}
