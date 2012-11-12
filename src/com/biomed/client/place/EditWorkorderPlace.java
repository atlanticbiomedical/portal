package com.biomed.client.place;

import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class EditWorkorderPlace extends WorkordersPlace {

  public static EditWorkorderPlace getPlace(int id) {
    return new EditWorkorderPlace(id);
  }

  private final int id;

  private EditWorkorderPlace(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof EditWorkorderPlace)) return false;

    EditWorkorderPlace other = (EditWorkorderPlace) obj;

    return other.id == this.id;
  }

  @Prefix("workorder/edit")
  public static class Tokenizer implements PlaceTokenizer<EditWorkorderPlace> {

    @Override
    public EditWorkorderPlace getPlace(String token) {
      return EditWorkorderPlace.getPlace(Integer.parseInt(token));
    }

    @Override
    public String getToken(EditWorkorderPlace place) {
      return Integer.toString(place.id);
    }
  }
}
