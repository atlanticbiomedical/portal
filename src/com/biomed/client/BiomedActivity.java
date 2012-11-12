package com.biomed.client;

import com.biomed.client.BiomedActivityManager.SiteContainer;
import com.biomed.client.place.BiomedPlace;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;

public interface BiomedActivity {
  /**
   * Called when the user is trying to navigate away from this activity.
   *
   * @return A message to display to the user, e.g. to warn of unsaved work, or
   *         null to say nothing
   */
  String mayStop();

  /**
   * Called when {@link #start} has not yet replied to its callback, but the
   * user has lost interest.
   */
  void onCancel();

  /**
   * Called when the Activity's widget has been removed from view. All event
   * handlers it registered will have been removed before this method is called.
   */
  void onStop();

  /**
   * Called when the Activity should ready its widget for the user. When the
   * widget is ready (typically after an RPC response has been received),
   * receiver should present it by calling
   * {@link AcceptsOneWidget#setWidget} on the given panel.
   * <p>
   * Any handlers attached to the provided event bus will be de-registered when
   * the activity is stopped, so activities will rarely need to hold on to the
   * {@link com.google.gwt.event.shared.HandlerRegistration HandlerRegistration}
   * instances returned by {@link EventBus#addHandler}.
   *
   * @param panel the panel to display this activity's widget when it is ready
   * @param eventBus the event bus
   */
  void start(SiteContainer panel, EventBus eventBus);

  void setPlace(BiomedPlace place);
}
