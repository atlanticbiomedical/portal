package com.biomed.client.ui.renderers;

import com.biomed.shared.api.dto.ScheduleTime;
import com.google.gwt.text.shared.AbstractRenderer;

public class ScheduleTimeRenderer extends AbstractRenderer<ScheduleTime> {

  private final String defaultValue;

  public ScheduleTimeRenderer(String defaultValue) {
    this.defaultValue = defaultValue;
  }

  @Override
  public String render(ScheduleTime object) {
    if (object != null) {
      return object.getLabel();
    } else {
      return defaultValue;
    }
  }
}
