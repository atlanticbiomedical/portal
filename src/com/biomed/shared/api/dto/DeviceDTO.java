package com.biomed.shared.api.dto;

import java.util.Date;

public class DeviceDTO implements Entity {
  private int deviceId;
  private int clientId;
  private String identifier;
  private String name;
  private String manufacturer;
  private String model;
  private String serial;
  private String status;
  private Date lastPm;

  public int getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(int deviceId) {
    this.deviceId = deviceId;
  }

  public int getClientId() {
    return clientId;
  }

  public void setClientId(int clientId) {
    this.clientId = clientId;
  }


  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getSerial() {
    return serial;
  }

  public void setSerial(String serial) {
    this.serial = serial;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Date getLastPm() {
    return lastPm;
  }

  public void setLastPm(Date lastPm) {
    this.lastPm = lastPm;
  }
}
