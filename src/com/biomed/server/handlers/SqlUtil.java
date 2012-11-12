package com.biomed.server.handlers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SqlUtil {
  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

  public static String formatDate(Date date) {
    return dateFormat.format(date);
  }

  public static Date parseDate(String date) {
    if (date == null) {
      return null;
    }

    try {
      return dateFormat.parse(date);
    } catch (ParseException e) {
      return null;
    }
  }
}
