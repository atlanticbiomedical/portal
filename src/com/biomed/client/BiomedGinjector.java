package com.biomed.client;

import com.biomed.client.ui.MainPanel;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.place.shared.PlaceHistoryHandler;

@GinModules(BiomedGinModule.class)
public interface BiomedGinjector extends Ginjector {

  PlaceHistoryHandler getPlaceHistoryHandler();

  MainPanel getAppWidget();
}
