package com.biomed.shared.api.dto;

import java.util.Comparator;

public class ClientDTO implements Entity {
  private int id;
  private String name;
  private String identifier;
  private String primaryAddress;
  private String primaryAddress2;
  private String primaryCity;
  private State primaryState;
  private String primaryZip;
  private String primaryContactName;
  private String primaryPhone;
  private String primaryEmail;
  private String secondaryContactName;
  private String secondaryPhone;
  private String secondaryEmail;
  private String notes;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public String getPrimaryContactName() {
    return primaryContactName;
  }

  public void setPrimaryContactName(String primaryContactName) {
    this.primaryContactName = primaryContactName;
  }

  public String getPrimaryAddress() {
    return primaryAddress;
  }

  public void setPrimaryAddress(String primaryAddress) {
    this.primaryAddress = primaryAddress;
  }

  public String getPrimaryAddress2() {
    return primaryAddress2;
  }

  public void setPrimaryAddress2(String primaryAddress2) {
    this.primaryAddress2 = primaryAddress2;
  }

  public String getPrimaryCity() {
    return primaryCity;
  }

  public void setPrimaryCity(String primaryCity) {
    this.primaryCity = primaryCity;
  }

  public State getPrimaryState() {
    return primaryState;
  }

  public void setPrimaryState(State primaryState) {
    this.primaryState = primaryState;
  }

  public String getPrimaryZip() {
    return primaryZip;
  }

  public void setPrimaryZip(String primaryZip) {
    this.primaryZip = primaryZip;
  }

  public String getPrimaryPhone() {
    return primaryPhone;
  }

  public void setPrimaryPhone(String primaryPhone) {
    this.primaryPhone = primaryPhone;
  }

  public String getPrimaryEmail() {
    return primaryEmail;
  }

  public void setPrimaryEmail(String primaryEmail) {
    this.primaryEmail = primaryEmail;
  }

  public String getSecondaryPhone() {
    return secondaryPhone;
  }

  public void setSecondaryPhone(String secondaryPhone) {
    this.secondaryPhone = secondaryPhone;
  }

  public String getSecondaryEmail() {
    return secondaryEmail;
  }

  public void setSecondaryEmail(String secondaryEmail) {
    this.secondaryEmail = secondaryEmail;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public static class IdentifierComparator implements Comparator<ClientDTO> {
    @Override
    public int compare(ClientDTO left, ClientDTO right) {
      return left.identifier.compareTo(right.identifier);
    }
  }

  public String getSecondaryContactName() {
    return secondaryContactName;
  }

  public void setSecondaryContactName(String secondaryContactName) {
    this.secondaryContactName = secondaryContactName;
  }
}
