package com.biomed.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.RootPanel;

public class BiomedEntryPoint implements EntryPoint {

  private final BiomedGinjector injector = GWT.create(BiomedGinjector.class);

  @Override
  public void onModuleLoad() {
    DOM.getElementById("loading").removeFromParent();
    RootPanel.get().add(injector.getAppWidget());
    injector.getPlaceHistoryHandler().handleCurrentHistory();
  }
}
