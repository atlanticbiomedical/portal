package com.biomed.client.place;

import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public final class NewMessagePlace extends MessagesPlace {
  public static final NewMessagePlace INSTANCE = new NewMessagePlace();
  private NewMessagePlace() { }

  @Prefix("message/new")
  public static class Tokenizer implements PlaceTokenizer<NewMessagePlace> {

    @Override
    public NewMessagePlace getPlace(String token) {
      return NewMessagePlace.INSTANCE;
    }

    @Override
    public String getToken(NewMessagePlace place) {
      return "";
    }
  }
}
