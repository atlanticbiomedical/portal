package com.biomed.client.ui.schedule;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.ImplementedBy;

public interface Schedule {

  @ImplementedBy(SchedulePanel.class)
  public interface View extends IsWidget {

  }

  @ImplementedBy(ScheduleSidebarPanel.class)
  public interface Sidebar extends IsWidget {

  }
}
