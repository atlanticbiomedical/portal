package com.biomed.client.activity;

import javax.inject.Inject;

import com.biomed.client.AbstractBiomedActivity;
import com.biomed.client.BiomedActivityManager.SiteContainer;
import com.biomed.client.BiomedSite;
import com.biomed.client.dispatch.DefaultCallback;
import com.biomed.client.place.EditClientOverviewPlace;
import com.biomed.client.place.NewClientPlace;
import com.biomed.client.services.BiomedService;
import com.biomed.client.ui.clientlist.ClientListPanel;
import com.biomed.client.ui.clientlist.ClientListSidebarPanel;
import com.biomed.shared.api.GetClientsAction;
import com.biomed.shared.api.GetClientsAction.View;
import com.biomed.shared.api.ListResult;
import com.biomed.shared.api.dto.ClientDTO;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.view.client.ListDataProvider;
import com.google.web.bindery.event.shared.EventBus;

public class ViewClientsActivity extends AbstractBiomedActivity {

  @Inject ClientListPanel view;
  @Inject ClientListSidebarPanel sidebar;
  @Inject PlaceController placeController;
  @Inject BiomedService biomedService;

  private ListDataProvider<ClientDTO> dataProvider;
  private String filter;

  @Override
  public void start(SiteContainer container, EventBus eventBus) {
    view.activity = this;
    sidebar.activity = this;

    dataProvider = new ListDataProvider<ClientDTO>();
    dataProvider.addDataDisplay(view.getTable());

    container.setWidget(BiomedSite.CONTENT, view);
    container.setWidget(BiomedSite.SIDEBAR, sidebar);
    requestData();
  }

  public void onNewClientClicked() {
    placeController.goTo(NewClientPlace.INSTANCE);
  }

  public void onClientClicked(int id) {
    placeController.goTo(EditClientOverviewPlace.getPlace(id));
  }

  private void requestData() {
    view.showLoading();

    GetClientsAction action = new GetClientsAction();
    action.setView(View.CLIENT_PREVIEW);
    action.setFilter(filter);

    biomedService.execute(action, new DefaultCallback<ListResult<ClientDTO>>() {
      @Override
      public void onSuccess(ListResult<ClientDTO> result) {
        dataProvider.setList(result.getResult());
      }
    });
  }

  public void filterTable(String filter) {
    this.filter = filter;
    requestData();
  }
}
