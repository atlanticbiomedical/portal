package com.biomed.shared.api;

import com.biomed.shared.api.dto.ClientDTO;
import com.biomed.shared.dispatch.DispatchAction;

public class SaveClientAction implements DispatchAction<EmptyResult> {
  private ClientDTO client;

  public ClientDTO getClient() {
    return client;
  }

  public void setClient(ClientDTO client) {
    this.client = client;
  }
}
