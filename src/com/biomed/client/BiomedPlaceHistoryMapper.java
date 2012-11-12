package com.biomed.client;

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
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;


@WithTokenizers({
  EditClientDevicesPlace.Tokenizer.class,
  EditClientFrequencyPlace.Tokenizer.class,
  EditClientOverviewPlace.Tokenizer.class,
  EditClientWorkordersPlace.Tokenizer.class,
  EditWorkorderPlace.Tokenizer.class,
  NewClientPlace.Tokenizer.class,
  NewMessagePlace.Tokenizer.class,
  ViewClientsPlace.Tokenizer.class,
  ViewSchedulePlace.Tokenizer.class,
  ViewWorkordersPlace.Tokenizer.class
})
public interface BiomedPlaceHistoryMapper extends PlaceHistoryMapper { }
