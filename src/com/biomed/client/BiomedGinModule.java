package com.biomed.client;

import javax.inject.Singleton;

import com.biomed.client.dispatch.ClientDispatchGinModule;
import com.biomed.client.place.ViewSchedulePlace;
import com.biomed.client.services.ServicesGinModule;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.inject.Provides;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

public class BiomedGinModule extends AbstractGinModule {

  @Override
  protected void configure() {
    install(new ClientDispatchGinModule());
    install(new ServicesGinModule());

    bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
    bind(PlaceHistoryMapper.class).to(BiomedPlaceHistoryMapper.class).in(Singleton.class);
  }

  @Provides
  @Singleton
  public PlaceHistoryHandler getHistoryHandler(PlaceController placeController,
      PlaceHistoryMapper historyMapper, EventBus eventBus) {

    PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
    historyHandler.register(placeController, eventBus, ViewSchedulePlace.INSTANCE);

    return historyHandler;
  }

  @Provides
  @Singleton
  public PlaceController providesPlaceController(EventBus eventBus) {
    return new PlaceController(eventBus);
  }

}
