package com.biomed.client.ui.schedule;

import java.util.Date;

public class DateUtil {

  public static Date addDays(Date date, int days) {
    return new Date(date.getTime() + (86400000 * days));
  }

  @SuppressWarnings("deprecation")
  public static Date getWorkFirstDay(Date date) {
    Date current = date;
    while (current.getDay() != 1) {
      current = new Date(current.getTime() - 86400000);
    }

    return current;
  }

  @SuppressWarnings("deprecation")
  public static Date getNextWorkDay(Date date) {
    Date current = date;

    while (current.getDay() == 0 || current.getDay() == 6) {
      current = new Date(current.getTime() + 86400000);
    }

    return current;
  }
}
