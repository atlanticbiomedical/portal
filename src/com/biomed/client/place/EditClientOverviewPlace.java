package com.biomed.client.place;

import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public final class EditClientOverviewPlace extends ClientsPlace implements HasClientId {

  public static EditClientOverviewPlace getPlace(int id) {
    return new EditClientOverviewPlace(id);
  }

  private final int id;

  private EditClientOverviewPlace(int id) {
    this.id = id;
  }

  @Override
  public int getId() {
    return id;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof EditClientOverviewPlace)) return false;

    EditClientOverviewPlace other = (EditClientOverviewPlace) obj;

    return other.id == this.id;
  }

  @Prefix("client/overview")
  public static class Tokenizer implements PlaceTokenizer<EditClientOverviewPlace> {

    @Override
    public EditClientOverviewPlace getPlace(String token) {
      return EditClientOverviewPlace.getPlace(Integer.parseInt(token));
    }

    @Override
    public String getToken(EditClientOverviewPlace place) {
      return Integer.toString(place.id);
    }
  }
}
