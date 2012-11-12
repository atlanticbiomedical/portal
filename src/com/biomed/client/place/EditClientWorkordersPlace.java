package com.biomed.client.place;

import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public final class EditClientWorkordersPlace extends ClientsPlace implements HasClientId {

  public static EditClientWorkordersPlace getPlace(int id) {
    return new EditClientWorkordersPlace(id);
  }

  private final int id;

  private EditClientWorkordersPlace(int id) {
    this.id = id;
  }

  @Override
  public int getId() {
    return id;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof EditClientWorkordersPlace)) return false;

    EditClientWorkordersPlace other = (EditClientWorkordersPlace) obj;

    return other.id == this.id;
  }

  @Prefix("client/workorders")
  public static class Tokenizer implements PlaceTokenizer<EditClientWorkordersPlace> {

    @Override
    public EditClientWorkordersPlace getPlace(String token) {
      return EditClientWorkordersPlace.getPlace(Integer.parseInt(token));
    }

    @Override
    public String getToken(EditClientWorkordersPlace place) {
      return Integer.toString(place.id);
    }
  }
}
