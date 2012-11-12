package com.biomed.shared.api;

import java.util.Date;

import com.biomed.shared.api.dto.ScheduleTime;
import com.biomed.shared.api.dto.WorkorderReasonDTO;
import com.biomed.shared.api.dto.WorkorderStatusDTO;
import com.biomed.shared.dispatch.DispatchAction;

public class ScheduleAction implements DispatchAction<EmptyResult> {
  private Date jobDate;
  private int clientId;
  private int techId;
  private ScheduleTime startTime;
  private ScheduleTime endTime;
  private WorkorderReasonDTO reason;
  private String requestedBy;
  private String remarks;
  private WorkorderStatusDTO status;

  public Date getJobDate() {
    return jobDate;
  }

  public void setJobDate(Date jobDate) {
    this.jobDate = jobDate;
  }

  public int getClientId() {
    return clientId;
  }

  public void setClientId(int clientId) {
    this.clientId = clientId;
  }

  public int getTechId() {
    return techId;
  }

  public void setTechId(int techId) {
    this.techId = techId;
  }

  public ScheduleTime getStartTime() {
    return startTime;
  }

  public void setStartTime(ScheduleTime startTime) {
    this.startTime = startTime;
  }

  public ScheduleTime getEndTime() {
    return endTime;
  }

  public void setEndTime(ScheduleTime endTime) {
    this.endTime = endTime;
  }

  public WorkorderReasonDTO getReason() {
    return reason;
  }

  public void setReason(WorkorderReasonDTO reason) {
    this.reason = reason;
  }

  public String getRequestedBy() {
    return requestedBy;
  }

  public void setRequestedBy(String requestedBy) {
    this.requestedBy = requestedBy;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  public WorkorderStatusDTO getStatus() {
    return status;
  }

  public void setStatus(WorkorderStatusDTO status) {
    this.status = status;
  }
}
