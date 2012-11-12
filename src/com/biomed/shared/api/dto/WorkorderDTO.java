package com.biomed.shared.api.dto;

import java.util.Date;

public class WorkorderDTO implements Entity {
  public int workorderId;
  public int clientId;
  public String clientIdentifier;
  public String clientName;
  public Date jobDate;
  public String remarks;
  public String reason;
  public int jobStatusId;
  public String techFirstName;
  public String techLastName;
  public int techId;

  public int getWorkorderId() {
    return workorderId;
  }

  public void setWorkorderId(int workorderId) {
    this.workorderId = workorderId;
  }

  public int getClientId() {
    return clientId;
  }

  public void setClientId(int clientId) {
    this.clientId = clientId;
  }

  public String getClientIdentifier() {
    return clientIdentifier;
  }

  public void setClientIdentifier(String clientIdentifier) {
    this.clientIdentifier = clientIdentifier;
  }

  public String getClientName() {
    return clientName;
  }

  public void setClientName(String clientName) {
    this.clientName = clientName;
  }

  public Date getJobDate() {
    return jobDate;
  }

  public void setJobDate(Date jobDate) {
    this.jobDate = jobDate;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  public int getJobStatusId() {
    return jobStatusId;
  }

  public void setJobStatusId(int jobStatusId) {
    this.jobStatusId = jobStatusId;
  }

  public String getTechFirstName() {
    return techFirstName;
  }

  public void setTechFirstName(String techFirstName) {
    this.techFirstName = techFirstName;
  }

  public String getTechLastName() {
    return techLastName;
  }

  public void setTechLastName(String techLastName) {
    this.techLastName = techLastName;
  }

  public int getTechId() {
    return techId;
  }

  public void setTechId(int techId) {
    this.techId = techId;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }
}
