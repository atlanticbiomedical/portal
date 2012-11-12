package com.biomed.shared.api;

import com.biomed.shared.api.dto.UserDTO;
import com.biomed.shared.dispatch.DispatchAction;

public class GetUsersAction implements DispatchAction<ListResult<UserDTO>> {
  private int userTypeId;

  public int getUserTypeId() {
    return userTypeId;
  }

  public void setUserTypeId(int userTypeId) {
    this.userTypeId = userTypeId;
  }
}
