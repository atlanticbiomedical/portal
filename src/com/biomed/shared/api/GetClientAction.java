package com.biomed.shared.api;

import com.biomed.shared.api.dto.ClientDTO;
import com.biomed.shared.dispatch.DispatchAction;

public class GetClientAction implements DispatchAction<SingleResult<ClientDTO>> { 

  private int clientId;

  public int getClientId() {
    return clientId;
  }

  public void setClientId(int clientId) {
    this.clientId = clientId;
  }
}
