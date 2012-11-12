package com.biomed.client.place;

import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public final class ViewClientsPlace extends ClientsPlace {
  public static final ViewClientsPlace INSTANCE = new ViewClientsPlace();
  private ViewClientsPlace() { }

  @Prefix("clients")
  public static class Tokenizer implements PlaceTokenizer<ViewClientsPlace> {

    @Override
    public ViewClientsPlace getPlace(String token) {
      return ViewClientsPlace.INSTANCE;
    }

    @Override
    public String getToken(ViewClientsPlace place) {
      return "";
    }
  }
}
