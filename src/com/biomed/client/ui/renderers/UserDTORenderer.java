package com.biomed.client.ui.renderers;

import com.biomed.shared.api.dto.UserDTO;
import com.google.gwt.text.shared.AbstractRenderer;

public class UserDTORenderer extends AbstractRenderer<UserDTO> {

  private final String defaultValue;

  public UserDTORenderer(String defaultValue) {
    this.defaultValue = defaultValue;
  }

  @Override
  public String render(UserDTO object) {
    if (object != null) {
      return object.getDisplayName();
    } else {
      return defaultValue;
    }
  }
}
