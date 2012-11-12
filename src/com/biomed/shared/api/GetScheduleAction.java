package com.biomed.shared.api;

import com.biomed.shared.dispatch.DispatchAction;

public class GetScheduleAction implements DispatchAction<SqlResultSet> {
  public String startDate;
  public String endDate;
  public int techId;
}
