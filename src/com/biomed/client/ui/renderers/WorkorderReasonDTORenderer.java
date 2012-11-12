package com.biomed.client.ui.renderers;

import com.biomed.shared.api.dto.WorkorderReasonDTO;
import com.google.gwt.text.shared.AbstractRenderer;

public class WorkorderReasonDTORenderer extends AbstractRenderer<WorkorderReasonDTO> {

  private final String defaultValue;

  public WorkorderReasonDTORenderer(String defaultValue) {
    this.defaultValue = defaultValue;
  }

  @Override
  public String render(WorkorderReasonDTO object) {
    if (object != null) {
      return object.getLabel();
    } else {
      return defaultValue;
    }
  }
}
