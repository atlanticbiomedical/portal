package com.biomed.shared.api;

import java.util.Date;

import com.biomed.shared.dispatch.DispatchAction;

public class SendTechScheduleEmailAction implements DispatchAction<EmptyResult> {
  private int userId;
  private Date date;

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }
}
