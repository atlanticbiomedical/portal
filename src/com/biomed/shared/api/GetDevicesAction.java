package com.biomed.shared.api;

import com.biomed.shared.api.dto.DeviceDTO;
import com.biomed.shared.dispatch.DispatchAction;

public class GetDevicesAction implements DispatchAction<ListResult<DeviceDTO>> {

  private int clientId;

  public int getClientId() {
    return clientId;
  }

  public void setClientId(int clientId) {
    this.clientId = clientId;
  }

}
