package com.biomed.shared.api.dto;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;


public class ScheduleTime implements Serializable {
  private static final List<ScheduleTime> values = Lists.newArrayList();

  public static final ScheduleTime H6M00A = create(6, 0);
  public static final ScheduleTime H6M30A = create(6, 30);
  public static final ScheduleTime H7M00A = create(7, 0);
  public static final ScheduleTime H7M30A = create(7, 30);
  public static final ScheduleTime H8M00A = create(8, 0);
  public static final ScheduleTime H8M30A = create(8, 30);
  public static final ScheduleTime H9M00A = create(9, 0);
  public static final ScheduleTime H9M30A = create(9, 30);
  public static final ScheduleTime H10M00A = create(10, 0);
  public static final ScheduleTime H10M30A = create(10, 30);
  public static final ScheduleTime H11M00A = create(11, 0);
  public static final ScheduleTime H11M30A = create(11, 30);
  public static final ScheduleTime H12M00P = create(12, 0);
  public static final ScheduleTime H12M30P = create(12, 30);
  public static final ScheduleTime H1M00P = create(13, 0);
  public static final ScheduleTime H1M30P = create(13, 30);
  public static final ScheduleTime H2M00P = create(14, 0);
  public static final ScheduleTime H2M30P = create(14, 30);
  public static final ScheduleTime H3M00P = create(15, 0);
  public static final ScheduleTime H3M30P = create(15, 30);
  public static final ScheduleTime H4M00P = create(16, 0);
  public static final ScheduleTime H4M30P = create(16, 30);
  public static final ScheduleTime H5M00P = create(17, 0);
  public static final ScheduleTime H5M30P = create(17, 30);
  public static final ScheduleTime H6M00P = create(18, 0);
  public static final ScheduleTime H6M30P = create(18, 30);
  public static final ScheduleTime H7M00P = create(19, 0);
  public static final ScheduleTime H7M30P = create(19, 30);
  public static final ScheduleTime H8M00P = create(20, 0);
  public static final ScheduleTime H8M30P = create(20, 30);

  private static ScheduleTime create(int hour, int min) {
    ScheduleTime value = new ScheduleTime(hour, min);
    values.add(value);
    return value;
  }

  private int hour;
  private int min;

  public ScheduleTime() { }

  private ScheduleTime(int hour, int min) {
    this.hour = hour;
    this.min = min;
  }

  public static List<ScheduleTime> getValues() {
    return Collections.unmodifiableList(values);
  }

  public String getLabel() {
    return getHour() + ":" + getMin() + " " + getAmPm();
  }

  private String getMin() {
    if (min < 10) {
      return "0" + min;
    } else {
      return Integer.toString(min);
    }
  }

  private String getHour() {
    return Integer.toString(hour > 12 ? (hour - 12) : hour);
  }

  private String getAmPm() {
    return hour > 11 ? "PM" : "AM";
  }

  public int toInteger() {
    return (hour * 100) + min;
  }

  public static ScheduleTime fromInteger(int value) {
    return new ScheduleTime(value / 100, value % 100);
  }

  public Date toDate(Date day) {
    return new Date(day.getTime() + (((hour * 60) + min)  * 60000));
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof ScheduleTime)) {
      return false;
    }

    ScheduleTime other = (ScheduleTime) obj;

    return this.hour == other.hour && this.min == other.min;
  }

  @Override
  public int hashCode() {
    return toInteger();
  }
}
