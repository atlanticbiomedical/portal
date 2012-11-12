package com.biomed.client.place;

import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public final class NewClientPlace extends ClientsPlace {
  public static final NewClientPlace INSTANCE = new NewClientPlace();
  private NewClientPlace() { }

  @Prefix("client/new")
  public static class Tokenizer implements PlaceTokenizer<NewClientPlace> {

    @Override
    public NewClientPlace getPlace(String token) {
      return NewClientPlace.INSTANCE;
    }

    @Override
    public String getToken(NewClientPlace place) {
      return "";
    }
  }
}
