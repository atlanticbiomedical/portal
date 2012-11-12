package com.biomed.client;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.biomed.client.activity.EditClientDevicesActivity;
import com.biomed.client.activity.EditClientFrequencyActivity;
import com.biomed.client.activity.EditClientOverviewActivity;
import com.biomed.client.activity.EditClientWorkordersActivity;
import com.biomed.client.activity.EditWorkorderActivity;
import com.biomed.client.activity.NewClientActivity;
import com.biomed.client.activity.NewMessageActivity;
import com.biomed.client.activity.ViewClientsActivity;
import com.biomed.client.activity.ViewScheduleActivity;
import com.biomed.client.activity.ViewWorkordersActivity;
import com.biomed.client.place.BiomedPlace;
import com.biomed.client.place.EditClientDevicesPlace;
import com.biomed.client.place.EditClientFrequencyPlace;
import com.biomed.client.place.EditClientOverviewPlace;
import com.biomed.client.place.EditClientWorkordersPlace;
import com.biomed.client.place.EditWorkorderPlace;
import com.biomed.client.place.NewClientPlace;
import com.biomed.client.place.NewMessagePlace;
import com.biomed.client.place.ViewClientsPlace;
import com.biomed.client.place.ViewSchedulePlace;
import com.biomed.client.place.ViewWorkordersPlace;
import com.google.inject.Provider;

@Singleton
public class BiomedActivityMapper {

  @Inject Provider<EditClientOverviewActivity> editClientOverviewActivityProvider;
  @Inject Provider<EditClientDevicesActivity> editClientDevicesActivityProvider;
  @Inject Provider<EditClientFrequencyActivity> editClientFrequencyActivityProvider;
  @Inject Provider<EditClientWorkordersActivity> editClientWorkordersActivityProvider;
  @Inject Provider<EditWorkorderActivity> editWorkorderActivityProvider;
  @Inject Provider<NewClientActivity> newClientActivityProvider;
  @Inject Provider<NewMessageActivity> newMessageActivityProvider;
  @Inject Provider<ViewClientsActivity> viewClientsActivityProvider;
  @Inject Provider<ViewScheduleActivity> viewScheduleActivityProvider;
  @Inject Provider<ViewWorkordersActivity> viewWorkordersActivityProvider;


  public BiomedActivity getActivity(BiomedPlace place) {
    BiomedActivity activity = getActivityInternal(place);
    if (activity != null) {
      activity.setPlace(place);
    }

    return activity;
  }


  private BiomedActivity getActivityInternal(BiomedPlace place) {
    if (place instanceof EditClientOverviewPlace) {
      return editClientOverviewActivityProvider.get();
    }

    if (place instanceof EditClientDevicesPlace) {
      return editClientDevicesActivityProvider.get();
    }

    if (place instanceof EditClientFrequencyPlace) {
      return editClientFrequencyActivityProvider.get();
    }

    if (place instanceof EditClientWorkordersPlace) {
      return editClientWorkordersActivityProvider.get();
    }

    if (place instanceof EditWorkorderPlace) {
      return editWorkorderActivityProvider.get();
    }

    if (place instanceof NewClientPlace) {
      return newClientActivityProvider.get();
    }

    if (place instanceof NewMessagePlace) {
      return newMessageActivityProvider.get();
    }

    if (place instanceof ViewClientsPlace) {
      return viewClientsActivityProvider.get();
    }

    if (place instanceof ViewSchedulePlace) {
      return viewScheduleActivityProvider.get();
    }

    if (place instanceof ViewWorkordersPlace) {
      return viewWorkordersActivityProvider.get();
    }

    return null;
  }
}
