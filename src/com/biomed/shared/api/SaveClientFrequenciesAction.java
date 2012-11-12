package com.biomed.shared.api;

import com.biomed.shared.api.dto.FrequencyDTO;
import com.biomed.shared.dispatch.DispatchAction;

public class SaveClientFrequenciesAction implements DispatchAction<EmptyResult> {
  private int clientId;
  private FrequencyDTO frequencies;

  public int getClientId() {
    return clientId;
  }

  public void setClientId(int clientId) {
    this.clientId = clientId;
  }

  public FrequencyDTO getFrequencies() {
    return frequencies;
  }

  public void setFrequencies(FrequencyDTO frequencies) {
    this.frequencies = frequencies;
  }
}
