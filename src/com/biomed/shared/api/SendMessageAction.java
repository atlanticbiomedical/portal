package com.biomed.shared.api;

import com.biomed.shared.dispatch.DispatchAction;

public class SendMessageAction implements DispatchAction<EmptyResult> {
  private int userId;
  private String name;
  private String company;
  private String phone;
  private String phoneExtension;
  private String timeToReturnCall;
  private boolean hasTelephoned;
  private boolean hasCameToSeeYou;
  private boolean hasWantsToSeeYou;
  private boolean hasReturnedYourCall;
  private boolean hasPleaseCall;
  private boolean hasWillCallAgain;
  private boolean hasRush;
  private boolean hasSpecialAttention;
  private String notes;

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean hasName() {
    return name != null && !name.isEmpty();
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public boolean hasCompany() {
    return company != null && !company.isEmpty();
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public boolean hasPhone() {
    return phone != null && !phone.isEmpty();
  }

  public String getPhoneExtension() {
    return phoneExtension;
  }

  public boolean hasPhoneExtension() {
    return phoneExtension != null && !phoneExtension.isEmpty();
  }

  public void setPhoneExtension(String phoneExtension) {
    this.phoneExtension = phoneExtension;
  }

  public String getTimeToReturnCall() {
    return timeToReturnCall;
  }

  public void setTimeToReturnCall(String timeToReturnCall) {
    this.timeToReturnCall = timeToReturnCall;
  }

  public boolean hasTimeToReturnCall() {
    return timeToReturnCall != null && !timeToReturnCall.isEmpty();
  }

  public boolean hasTelephoned() {
    return hasTelephoned;
  }

  public void setTelephoned(boolean hasTelephoned) {
    this.hasTelephoned = hasTelephoned;
  }

  public boolean hasCameToSeeYou() {
    return hasCameToSeeYou;
  }

  public void setCameToSeeYou(boolean hasCameToSeeYou) {
    this.hasCameToSeeYou = hasCameToSeeYou;
  }

  public boolean hasWantsToSeeYou() {
    return hasWantsToSeeYou;
  }

  public void setWantsToSeeYou(boolean hasWantsToSeeYou) {
    this.hasWantsToSeeYou = hasWantsToSeeYou;
  }

  public boolean hasReturnedYourCall() {
    return hasReturnedYourCall;
  }

  public void setReturnedYourCall(boolean hasReturnedYourCall) {
    this.hasReturnedYourCall = hasReturnedYourCall;
  }

  public boolean hasPleaseCall() {
    return hasPleaseCall;
  }

  public void setPleaseCall(boolean hasPleaseCall) {
    this.hasPleaseCall = hasPleaseCall;
  }

  public boolean hasWillCallAgain() {
    return hasWillCallAgain;
  }

  public void setWillCallAgain(boolean hasWillCallAgain) {
    this.hasWillCallAgain = hasWillCallAgain;
  }

  public boolean hasRush() {
    return hasRush;
  }

  public void setRush(boolean hasRush) {
    this.hasRush = hasRush;
  }

  public boolean hasSpecialAttention() {
    return hasSpecialAttention;
  }

  public void setSpecialAttention(boolean hasSpecialAttention) {
    this.hasSpecialAttention = hasSpecialAttention;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public boolean hasNotes() {
    return notes != null && !notes.isEmpty();
  }
}
