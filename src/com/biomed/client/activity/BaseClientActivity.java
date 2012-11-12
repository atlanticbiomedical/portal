package com.biomed.client.activity;

import javax.inject.Inject;

import com.biomed.client.AbstractBiomedActivity;
import com.biomed.client.BiomedActivityManager.SiteContainer;
import com.biomed.client.BiomedSite;
import com.biomed.client.place.EditClientDevicesPlace;
import com.biomed.client.place.EditClientFrequencyPlace;
import com.biomed.client.place.EditClientOverviewPlace;
import com.biomed.client.place.EditClientWorkordersPlace;
import com.biomed.client.place.HasClientId;
import com.biomed.client.ui.clienteditor.ClientEditorSidebarPanel;
import com.biomed.client.ui.clienteditor.ClientEditorSidebarPanel.Tabs;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Provider;
import com.google.web.bindery.event.shared.EventBus;

@SuppressWarnings("rawtypes")
public abstract class BaseClientActivity extends AbstractBiomedActivity implements
    ValueChangeHandler {

  @Inject
  Provider<ClientEditorSidebarPanel> sidebarProvider;

  @Inject
  PlaceController placeController;

  private boolean isDirty;

  protected abstract boolean showNavigationPanel();

  protected Tabs getTab() {
    return null;
  }

  protected abstract void createContentPanel(SiteContainer panel);

  @Override
  public final void start(SiteContainer panel, EventBus eventBus) {
    if (showNavigationPanel()) {
      ClientEditorSidebarPanel sidebar = sidebarProvider.get();
      sidebar.setActive(getTab());
      addSidebarEventHandlers(sidebar);
      panel.setWidget(BiomedSite.SIDEBAR, sidebar);
    }

    createContentPanel(panel);
  }

  private void addSidebarEventHandlers(ClientEditorSidebarPanel sidebar) {
    Tabs tab = getTab();

    if (tab != Tabs.OVERVIEW) {
      sidebar.getOverviewTab().addClickHandler(new ClickHandler() {
        @Override
        public void onClick(ClickEvent event) {
          placeController.goTo(EditClientOverviewPlace.getPlace(getClientId()));
        }
      });
    }

    if (tab != Tabs.FREQUENCY) {
      sidebar.getFrequencyTab().addClickHandler(new ClickHandler() {
        @Override
        public void onClick(ClickEvent event) {
          placeController.goTo(EditClientFrequencyPlace.getPlace(getClientId()));
        }
      });
    }

    if (tab != Tabs.DEVICES) {
      sidebar.getDevicesTab().addClickHandler(new ClickHandler() {
        @Override
        public void onClick(ClickEvent event) {
          placeController.goTo(EditClientDevicesPlace.getPlace(getClientId()));
        }
      });
    }

    if (tab != Tabs.WORKORDERS) {
      sidebar.getWorkordersTab().addClickHandler(new ClickHandler() {
        @Override
        public void onClick(ClickEvent event) {
          placeController.goTo(EditClientWorkordersPlace.getPlace(getClientId()));
        }
      });
    }
  }

  protected int getClientId() {
    return ((HasClientId) place).getId();
  }

  protected void resetDirtyFlag() {
    isDirty = false;
  }

  @Override
  public String mayStop() {
    if (isDirty) {
      return "You have unsaved changed, are you sure?";
    } else {
      return null;
    }
  }

  @Override
  public void onValueChange(ValueChangeEvent event) {
    isDirty = true;
  }
}
