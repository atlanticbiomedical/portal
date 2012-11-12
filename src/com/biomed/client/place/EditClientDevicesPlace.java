package com.biomed.client.place;

import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public final class EditClientDevicesPlace extends ClientsPlace implements HasClientId {

  public static EditClientDevicesPlace getPlace(int id) {
    return new EditClientDevicesPlace(id);
  }

  private final int id;

  private EditClientDevicesPlace(int id) {
    this.id = id;
  }

  @Override
  public int getId() {
    return id;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof EditClientDevicesPlace)) return false;

    EditClientDevicesPlace other = (EditClientDevicesPlace) obj;

    return other.id == this.id;
  }

  @Prefix("client/device")
  public static class Tokenizer implements PlaceTokenizer<EditClientDevicesPlace> {

    @Override
    public EditClientDevicesPlace getPlace(String token) {
      return EditClientDevicesPlace.getPlace(Integer.parseInt(token));
    }

    @Override
    public String getToken(EditClientDevicesPlace place) {
      return Integer.toString(place.id);
    }
  }
}
