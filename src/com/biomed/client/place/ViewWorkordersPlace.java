package com.biomed.client.place;

import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public final class ViewWorkordersPlace extends WorkordersPlace {
  public static final ViewWorkordersPlace INSTANCE = new ViewWorkordersPlace();
  private ViewWorkordersPlace() { }

  @Prefix("workorders")
  public static class Tokenizer implements PlaceTokenizer<ViewWorkordersPlace> {

    @Override
    public ViewWorkordersPlace getPlace(String token) {
      return ViewWorkordersPlace.INSTANCE;
    }

    @Override
    public String getToken(ViewWorkordersPlace place) {
      return "";
    }
  }
}
