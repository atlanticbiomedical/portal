package com.biomed.shared.api;

import com.biomed.shared.api.dto.EditWorkorderViewDTO;
import com.biomed.shared.dispatch.DispatchAction;

public class GetWorkorderViewAction implements DispatchAction<SingleResult<EditWorkorderViewDTO>> {

  private int workorderId;

  public int getWorkorderId() {
    return workorderId;
  }

  public void setWorkorderId(int workorderId) {
    this.workorderId = workorderId;
  }

}
