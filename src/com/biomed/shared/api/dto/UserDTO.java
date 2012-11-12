package com.biomed.shared.api.dto;

import java.util.Comparator;

public class UserDTO implements Entity {
  private int id;
  private String firstName;
  private String lastName;
  private int userTypeId;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getDisplayName() {
    return firstName + " " + lastName;
  }

  public int getUserTypeId() {
    return userTypeId;
  }

  public void setUserTypeId(int userTypeId) {
    this.userTypeId = userTypeId;
  }

  public static class DisplayNameComparator implements Comparator<UserDTO> {
    @Override
    public int compare(UserDTO left, UserDTO right) {
      return left.getDisplayName().compareTo(right.getDisplayName());
    }
  }
}
