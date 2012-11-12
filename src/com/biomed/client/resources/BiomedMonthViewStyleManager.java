package com.biomed.client.resources;

import com.biomed.client.ui.schedule.BiomedAppointment;
import com.bradrydzewski.gwt.calendar.client.Appointment;
import com.bradrydzewski.gwt.calendar.client.ThemeAppointmentStyle;
import com.bradrydzewski.gwt.calendar.client.monthview.MonthViewStyleManager;

public class BiomedMonthViewStyleManager extends MonthViewStyleManager {

  @Override
  protected ThemeAppointmentStyle getDefaultViewAppointmentStyleForTheme() {
    return BiomedAppointmentTheme.GRAY;
  }

  @Override
  protected ThemeAppointmentStyle getViewAppointmentStyleForTheme(Appointment appointment) {
    return ((BiomedAppointment) appointment).getTheme();
  }
}
