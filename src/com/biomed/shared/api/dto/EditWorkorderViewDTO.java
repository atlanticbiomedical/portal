package com.biomed.shared.api.dto;

import java.util.Date;

public class EditWorkorderViewDTO implements Entity {
  private int workorderId;
  private int techId;
  private Date workorderScheduledDate;
  private String clientName;
  private String clientAddress;
  private String clientAddress2;
  private String clientCity;
  private String clientState;
  private String clientZip;
  private String clientPhone;
  private String clientPrimaryContact;
  private Date workorderJobDate;
  private ScheduleTime workorderStartTime;
  private ScheduleTime workorderEndTime;
  private WorkorderReasonDTO workorderReason;
  private WorkorderStatusDTO workorderStatus;
  private String actionTaken;
  private String remarks;

  public int getWorkorderId() {
    return workorderId;
  }

  public void setWorkorderId(int workorderId) {
    this.workorderId = workorderId;
  }

  public Date getWorkorderScheduledDate() {
    return workorderScheduledDate;
  }

  public void setWorkorderScheduledDate(Date workorderScheduledDate) {
    this.workorderScheduledDate = workorderScheduledDate;
  }

  public String getClientName() {
    return clientName;
  }

  public void setClientName(String clientName) {
    this.clientName = clientName;
  }

  public String getClientAddress() {
    return clientAddress;
  }

  public void setClientAddress(String clientAddress) {
    this.clientAddress = clientAddress;
  }

  public String getClientAddress2() {
    return clientAddress2;
  }

  public void setClientAddress2(String clientAddress2) {
    this.clientAddress2 = clientAddress2;
  }

  public String getClientCity() {
    return clientCity;
  }

  public void setClientCity(String clientCity) {
    this.clientCity = clientCity;
  }

  public String getClientState() {
    return clientState;
  }

  public void setClientState(String clientState) {
    this.clientState = clientState;
  }

  public String getClientZip() {
    return clientZip;
  }

  public void setClientZip(String clientZip) {
    this.clientZip = clientZip;
  }

  public String getClientPhone() {
    return clientPhone;
  }

  public void setClientPhone(String clientPhone) {
    this.clientPhone = clientPhone;
  }

  public String getClientPrimaryContact() {
    return clientPrimaryContact;
  }

  public void setClientPrimaryContact(String clientPrimaryContact) {
    this.clientPrimaryContact = clientPrimaryContact;
  }

  public Date getWorkorderJobDate() {
    return workorderJobDate;
  }

  public void setWorkorderJobDate(Date workorderJobDate) {
    this.workorderJobDate = workorderJobDate;
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

  public ScheduleTime getWorkorderStartTime() {
    return workorderStartTime;
  }

  public void setWorkorderStartTime(ScheduleTime workorderStartTime) {
    this.workorderStartTime = workorderStartTime;
  }

  public ScheduleTime getWorkorderEndTime() {
    return workorderEndTime;
  }

  public void setWorkorderEndTime(ScheduleTime workorderEndTime) {
    this.workorderEndTime = workorderEndTime;
  }

  public WorkorderReasonDTO getWorkorderReason() {
    return workorderReason;
  }

  public void setWorkorderReason(WorkorderReasonDTO workorderReason) {
    this.workorderReason = workorderReason;
  }

  public int getTechId() {
    return techId;
  }

  public void setTechId(int techId) {
    this.techId = techId;
  }
}
