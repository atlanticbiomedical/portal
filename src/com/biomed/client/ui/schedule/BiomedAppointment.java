package com.biomed.client.ui.schedule;

import com.bradrydzewski.gwt.calendar.client.Appointment;
import com.bradrydzewski.gwt.calendar.client.ThemeAppointmentStyle;

public class BiomedAppointment extends Appointment {
  private ThemeAppointmentStyle theme;

  public ThemeAppointmentStyle getTheme() {
    return theme;
  }

  public void setTheme(ThemeAppointmentStyle theme) {
    this.theme = theme;
  }
}
