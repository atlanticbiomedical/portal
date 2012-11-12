package com.biomed.client.components;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.biomed.client.place.ClientsPlace;
import com.biomed.client.place.MessagesPlace;
import com.biomed.client.place.NewMessagePlace;
import com.biomed.client.place.SchedulePlace;
import com.biomed.client.place.ViewClientsPlace;
import com.biomed.client.place.ViewSchedulePlace;
import com.biomed.client.place.ViewWorkordersPlace;
import com.biomed.client.place.WorkordersPlace;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceChangeEvent;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.web.bindery.event.shared.EventBus;

@Singleton
public class NavigationPanel extends Composite implements PlaceChangeEvent.Handler {
  interface Binder extends UiBinder<HTMLPanel, NavigationPanel> {}

  private final PlaceController placeController;

  @UiField Anchor scheduleLink;
  @UiField Anchor clientsLink;
  @UiField Anchor messagesLink;
  @UiField Anchor workordersLink;

  Anchor activeLink;

  @Inject
  public NavigationPanel(PlaceController placeController, EventBus eventBus, Binder binder) {
    this.placeController = placeController;

    eventBus.addHandler(PlaceChangeEvent.TYPE, this);

    initWidget(binder.createAndBindUi(this));
  }

  @UiHandler("scheduleLink")
  void onScheduleLinkClicked(ClickEvent event) {
    placeController.goTo(ViewSchedulePlace.INSTANCE);
  }

  @UiHandler("clientsLink")
  void onClientsLinkClicked(ClickEvent event) {
    placeController.goTo(ViewClientsPlace.INSTANCE);
  }

  @UiHandler("messagesLink")
  void onMessagesLinkClicked(ClickEvent event) {
    placeController.goTo(NewMessagePlace.INSTANCE);
  }

  @UiHandler("workordersLink")
  void onWorkordersLinkClicked(ClickEvent event) {
    placeController.goTo(ViewWorkordersPlace.INSTANCE);
  }

  @Override
  public void onPlaceChange(PlaceChangeEvent event) {
    Place place = event.getNewPlace();

    if (place instanceof ClientsPlace) {
      setLinkActive(clientsLink);
    } else if (place instanceof MessagesPlace) {
      setLinkActive(messagesLink);
    } else if (place instanceof SchedulePlace) {
      setLinkActive(scheduleLink);
    } else if (place instanceof WorkordersPlace) {
      setLinkActive(workordersLink);
    } else {
      setLinkActive(null);
    }
  }

  private void setLinkActive(Anchor link) {
    if (activeLink != null) {
      activeLink.removeStyleName("active");
    }

    activeLink = link;
    activeLink.addStyleName("active");
  }
}
