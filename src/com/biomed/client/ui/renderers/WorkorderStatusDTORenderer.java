package com.biomed.client.ui.renderers;

import com.biomed.shared.api.dto.WorkorderStatusDTO;
import com.google.gwt.text.shared.AbstractRenderer;

public class WorkorderStatusDTORenderer extends AbstractRenderer<WorkorderStatusDTO> {

  private final String defaultValue;

  public WorkorderStatusDTORenderer(String defaultValue) {
    this.defaultValue = defaultValue;
  }

  @Override
  public String render(WorkorderStatusDTO object) {
    if (object != null) {
      return object.getLabel();
    } else {
      return defaultValue;
    }
  }
}
