package com.biomed.shared.api;

import java.util.Date;

import com.biomed.shared.api.dto.ScheduleTime;
import com.biomed.shared.api.dto.WorkorderReasonDTO;
import com.biomed.shared.api.dto.WorkorderStatusDTO;
import com.biomed.shared.dispatch.DispatchAction;

public class SaveWorkorderAction implements DispatchAction<EmptyResult> {
  private int id;
  private WorkorderReasonDTO workorderReason;
  private WorkorderStatusDTO workorderStatus;
  private String actionTaken;
  private String remarks;
  private int techId;
  private ScheduleTime startTime;
  private ScheduleTime endTime;
  private Date jobDate;

  public WorkorderReasonDTO getWorkorderReason() {
    return workorderReason;
  }

  public void setWorkorderReason(WorkorderReasonDTO workorderReason) {
    this.workorderReason = workorderReason;
  }

  public WorkorderStatusDTO getWorkorderStatus() {
    return workorderStatus;
  }

  public void setWorkorderStatus(WorkorderStatusDTO workorderStatus) {
    this.workorderStatus = workorderStatus;
  }

  public String getActionTaken() {
    return actionTaken;
  }

  public void setActionTaken(String actionTaken) {
    this.actionTaken = actionTaken;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

  public Date getJobDate() {
    return jobDate;
  }

  public void setJobDate(Date jobDate) {
    this.jobDate = jobDate;
  }
}
