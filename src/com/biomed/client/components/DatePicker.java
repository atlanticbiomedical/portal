package com.biomed.client.components;

import java.util.Date;

import com.github.gwtbootstrap.datepicker.client.ui.base.DateBoxBase;
import com.google.gwt.user.client.ui.SimplePanel;

public class DatePicker extends DateBoxBase {

  private final SimplePanel panel;

  public DatePicker() {
    this.panel = new SimplePanel();
    setElement(panel.getElement());
    setValue(new Date());
  }
}
