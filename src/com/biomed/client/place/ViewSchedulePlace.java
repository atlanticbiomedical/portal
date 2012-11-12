package com.biomed.client.place;

import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public final class ViewSchedulePlace extends SchedulePlace {
  public static final ViewSchedulePlace INSTANCE = new ViewSchedulePlace();
  private ViewSchedulePlace() { }

  @Prefix("schedule")
  public static class Tokenizer implements PlaceTokenizer<ViewSchedulePlace> {

    @Override
    public ViewSchedulePlace getPlace(String token) {
      return ViewSchedulePlace.INSTANCE;
    }

    @Override
    public String getToken(ViewSchedulePlace place) {
      return "";
    }
  }
}
