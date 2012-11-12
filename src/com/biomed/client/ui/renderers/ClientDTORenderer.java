package com.biomed.client.ui.renderers;

import com.biomed.shared.api.dto.ClientDTO;
import com.google.gwt.text.shared.AbstractRenderer;

public class ClientDTORenderer extends AbstractRenderer<ClientDTO> {

  private final String defaultValue;

  public ClientDTORenderer(String defaultValue) {
    this.defaultValue = defaultValue;
  }

  @Override
  public String render(ClientDTO object) {
    if (object != null) {
      return object.getIdentifier();
    } else {
      return defaultValue;
    }
  }
}
