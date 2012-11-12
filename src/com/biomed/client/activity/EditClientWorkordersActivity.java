package com.biomed.client.activity;

import com.biomed.client.BiomedActivityManager.SiteContainer;
import com.biomed.client.BiomedSite;
import com.biomed.client.dispatch.DefaultCallback;
import com.biomed.client.place.EditWorkorderPlace;
import com.biomed.client.services.BiomedService;
import com.biomed.client.ui.clienteditor.ClientEditorSidebarPanel.Tabs;
import com.biomed.client.ui.clienteditor.ClientWorkordersEditorPanel;
import com.biomed.shared.api.GetWorkordersAction;
import com.biomed.shared.api.ListResult;
import com.biomed.shared.api.dto.WorkorderDTO;
import com.google.gwt.view.client.ListDataProvider;
import com.google.inject.Inject;

public class EditClientWorkordersActivity extends BaseClientActivity {

  @Inject
  ClientWorkordersEditorPanel view;
  @Inject
  BiomedService biomedService;

  private ListDataProvider<WorkorderDTO> dataProvider;

  @Override
  protected boolean showNavigationPanel() {
    return true;
  }

  @Override
  protected Tabs getTab() {
    return Tabs.WORKORDERS;
  }

  @Override
  protected void createContentPanel(SiteContainer panel) {
    dataProvider = new ListDataProvider<WorkorderDTO>();
    dataProvider.addDataDisplay(view.getTable());

    view.activity = this;

    panel.setWidget(BiomedSite.CONTENT, view);

    view.showLoading();

    GetWorkordersAction action = new GetWorkordersAction();
    action.setClientId(getClientId());

    biomedService.execute(action, new DefaultCallback<ListResult<WorkorderDTO>>() {
      @Override
      public void onSuccess(ListResult<WorkorderDTO> result) {
        dataProvider.setList(result.getResult());
      }
    });
  }

  public void onWorkorderClicked(int id) {
    placeController.goTo(EditWorkorderPlace.getPlace(id));
  }
}
