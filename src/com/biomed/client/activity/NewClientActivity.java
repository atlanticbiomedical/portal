package com.biomed.client.activity;

import javax.inject.Inject;

import com.biomed.client.BiomedActivityManager.SiteContainer;
import com.biomed.client.BiomedSite;
import com.biomed.client.place.ViewClientsPlace;
import com.biomed.client.services.BiomedService;
import com.biomed.shared.api.dto.ClientDTO;
import com.google.gwt.place.shared.PlaceController;

public class NewClientActivity extends BaseClientOverviewActivity {

  @Inject PlaceController placeController;
  @Inject BiomedService biomedService;

  @Override
  protected boolean showNavigationPanel() {
    return false;
  }

  @Override
  protected void createContentPanel(final SiteContainer panel) {
    setupEvents();

    client = new ClientDTO();
    setView(client);

    panel.setWidget(BiomedSite.CONTENT, view);
  }

  @Override
  protected void onSaveComplete() {
    placeController.goTo(ViewClientsPlace.INSTANCE);
  }
}
