package com.biomed.shared.api;

import java.util.Date;

import com.biomed.shared.api.dto.WorkorderDTO;
import com.biomed.shared.dispatch.DispatchAction;

public class GetWorkordersAction implements DispatchAction<ListResult<WorkorderDTO>> {

  private Date startDate;
  private Date endDate;
  private int techId;
  private int clientId;
  private int workorderStatusId;

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public boolean hasStartDate() {
    return startDate != null;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public boolean hasEndDate() {
    return endDate != null;
  }

  public int getTechId() {
    return techId;
  }

  public void setTechId(int techId) {
    this.techId = techId;
  }

  public boolean hasTechId() {
    return techId != 0;
  }

  public int getClientId() {
    return clientId;
  }

  public void setClientId(int clientId) {
    this.clientId = clientId;
  }

  public boolean hasClientId() {
    return clientId != 0;
  }

  public int getWorkorderStatusId() {
    return workorderStatusId;
  }

  public void setWorkorderStatusId(int workorderStatusId) {
    this.workorderStatusId = workorderStatusId;
  }

  public boolean hasWorkorderStatusId() {
    return workorderStatusId != 0;
  }

}
