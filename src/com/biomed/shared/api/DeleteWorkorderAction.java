package com.biomed.shared.api;

import com.biomed.shared.dispatch.DispatchAction;

public class DeleteWorkorderAction implements DispatchAction<EmptyResult> {
  private int workorderId;

  public int getWorkorderId() {
    return workorderId;
  }

  public void setWorkorderId(int workorderId) {
    this.workorderId = workorderId;
  }
}
