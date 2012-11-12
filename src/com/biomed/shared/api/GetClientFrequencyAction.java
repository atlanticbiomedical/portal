package com.biomed.shared.api;

import com.biomed.shared.api.dto.FrequencyDTO;
import com.biomed.shared.dispatch.DispatchAction;

public class GetClientFrequencyAction  implements DispatchAction<SingleResult<FrequencyDTO>> {

  private int clientId;

  public int getClientId() {
    return clientId;
  }

  public void setClientId(int clientId) {
    this.clientId = clientId;
  }
}
