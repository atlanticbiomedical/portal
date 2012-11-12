package com.biomed.client.ui.clienteditor;

import javax.inject.Inject;

import com.github.gwtbootstrap.client.ui.NavLink;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

public class ClientEditorSidebarPanel extends Composite {

  interface Binder extends UiBinder<HTMLPanel, ClientEditorSidebarPanel> { }

  @UiField NavLink overviewTab;
  @UiField NavLink frequencyTab;
  @UiField NavLink devicesTab;
  @UiField NavLink workordersTab;

  @Inject
  public ClientEditorSidebarPanel(Binder binder) {
    initWidget(binder.createAndBindUi(this));
  }

  public void setActive(Tabs tab) {
    overviewTab.setActive(tab == Tabs.OVERVIEW);
    frequencyTab.setActive(tab == Tabs.FREQUENCY);
    devicesTab.setActive(tab == Tabs.DEVICES);
    workordersTab.setActive(tab == Tabs.WORKORDERS);
  }

  public HasClickHandlers getOverviewTab() {
    return overviewTab;
  }

  public HasClickHandlers getFrequencyTab() {
    return frequencyTab;
  }

  public HasClickHandlers getDevicesTab() {
    return devicesTab;
  }

  public HasClickHandlers getWorkordersTab() {
    return workordersTab;
  }

  public enum Tabs {
    OVERVIEW,
    FREQUENCY,
    DEVICES,
    WORKORDERS
  }
}
