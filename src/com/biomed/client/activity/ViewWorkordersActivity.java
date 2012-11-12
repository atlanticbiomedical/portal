package com.biomed.client.activity;

import java.util.ArrayList;
import java.util.Collections;

import javax.inject.Inject;

import com.biomed.client.AbstractBiomedActivity;
import com.biomed.client.BiomedActivityManager.SiteContainer;
import com.biomed.client.BiomedSite;
import com.biomed.client.dispatch.DefaultCallback;
import com.biomed.client.place.EditWorkorderPlace;
import com.biomed.client.services.BiomedService;
import com.biomed.client.ui.workorders.WorkordersPanel;
import com.biomed.client.ui.workorders.WorkordersSidebarPanel;
import com.biomed.shared.api.GetClientsAction;
import com.biomed.shared.api.GetClientsAction.View;
import com.biomed.shared.api.GetUsersAction;
import com.biomed.shared.api.GetWorkordersAction;
import com.biomed.shared.api.ListResult;
import com.biomed.shared.api.dto.ClientDTO;
import com.biomed.shared.api.dto.UserDTO;
import com.biomed.shared.api.dto.WorkorderDTO;
import com.biomed.shared.api.dto.WorkorderStatusDTO;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.view.client.ListDataProvider;
import com.google.web.bindery.event.shared.EventBus;

public class ViewWorkordersActivity extends AbstractBiomedActivity {

  @Inject
  WorkordersPanel view;
  @Inject
  WorkordersSidebarPanel sidebar;
  @Inject
  BiomedService biomedService;
  @Inject
  PlaceController placeController;

  private ListDataProvider<WorkorderDTO> dataProvider;

  @Override
  public void start(SiteContainer container, EventBus eventBus) {

    view.activity = this;

    dataProvider = new ListDataProvider<WorkorderDTO>();
    dataProvider.addDataDisplay(view.getTable());

    container.setWidget(BiomedSite.CONTENT, view);
    container.setWidget(BiomedSite.SIDEBAR, sidebar);

    sidebar.getSearchButton().addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        requestData();
      }
    });

    sidebar.getWorkorderStatusField().setAcceptableValues(WorkorderStatusDTO.VALUES);

    loadUsers();
    loadClients();
  }

  public void onWorkorderClicked(int id) {
    placeController.goTo(EditWorkorderPlace.getPlace(id));
  }

  private void loadUsers() {
    GetUsersAction action = new GetUsersAction();
    action.setUserTypeId(1);

    biomedService.execute(action, new DefaultCallback<ListResult<UserDTO>>() {

      @Override
      public void onSuccess(ListResult<UserDTO> result) {
        ArrayList<UserDTO> results = result.getResult();
        Collections.sort(results, new UserDTO.DisplayNameComparator());

        sidebar.getTechField().setAcceptableValues(results);
      }
    });
  }

  private void loadClients() {
    GetClientsAction action = new GetClientsAction();
    action.setView(View.NAMES_ONLY);

    biomedService.execute(action, new DefaultCallback<ListResult<ClientDTO>>() {

      @Override
      public void onSuccess(ListResult<ClientDTO> result) {
        ArrayList<ClientDTO> results = result.getResult();
        Collections.sort(results, new ClientDTO.IdentifierComparator());

        sidebar.getClientField().setAcceptableValues(results);
      }
    });
  }

  private void requestData() {
    view.showLoading();

    GetWorkordersAction action = new GetWorkordersAction();
    action.setStartDate(sidebar.getStartDateField().getValue());
    action.setEndDate(sidebar.getEndDateField().getValue());

    UserDTO tech = sidebar.getTechField().getValue();
    if (tech != null) {
      action.setTechId(tech.getId());
    }

    WorkorderStatusDTO status = sidebar.getWorkorderStatusField().getValue();
    if (status != null) {
      action.setWorkorderStatusId(status.getId());
    }

    ClientDTO client = sidebar.getClientField().getValue();
    if (client != null) {
      action.setClientId(client.getId());
    }

    biomedService.execute(action, new DefaultCallback<ListResult<WorkorderDTO>>() {
      @Override
      public void onSuccess(ListResult<WorkorderDTO> result) {
        dataProvider.setList(result.getResult());
      }
    });
  }
}
