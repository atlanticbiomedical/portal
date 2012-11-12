package com.biomed.client.place;

import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public final class EditClientFrequencyPlace extends ClientsPlace implements HasClientId {

  public static EditClientFrequencyPlace getPlace(int id) {
    return new EditClientFrequencyPlace(id);
  }

  private final int id;

  private EditClientFrequencyPlace(int id) {
    this.id = id;
  }

  @Override
  public int getId() {
    return id;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof EditClientFrequencyPlace)) return false;

    EditClientFrequencyPlace other = (EditClientFrequencyPlace) obj;

    return other.id == this.id;
  }

  @Prefix("client/frequency")
  public static class Tokenizer implements PlaceTokenizer<EditClientFrequencyPlace> {

    @Override
    public EditClientFrequencyPlace getPlace(String token) {
      return EditClientFrequencyPlace.getPlace(Integer.parseInt(token));
    }

    @Override
    public String getToken(EditClientFrequencyPlace place) {
      return Integer.toString(place.id);
    }
  }
}
