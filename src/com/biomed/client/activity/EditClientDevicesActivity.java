package com.biomed.client.activity;

import com.biomed.client.BiomedActivityManager.SiteContainer;
import com.biomed.client.BiomedSite;
import com.biomed.client.dispatch.DefaultCallback;
import com.biomed.client.services.BiomedService;
import com.biomed.client.ui.clienteditor.ClientDevicesEditorPanel;
import com.biomed.client.ui.clienteditor.ClientEditorSidebarPanel.Tabs;
import com.biomed.shared.api.GetDevicesAction;
import com.biomed.shared.api.ListResult;
import com.biomed.shared.api.dto.DeviceDTO;
import com.google.gwt.view.client.ListDataProvider;
import com.google.inject.Inject;

public class EditClientDevicesActivity extends BaseClientActivity {

  @Inject
  ClientDevicesEditorPanel view;
  @Inject
  BiomedService biomedService;

  private ListDataProvider<DeviceDTO> dataProvider;

  @Override
  protected boolean showNavigationPanel() {
    return true;
  }

  @Override
  protected Tabs getTab() {
    return Tabs.DEVICES;
  }

  @Override
  protected void createContentPanel(SiteContainer panel) {
    dataProvider = new ListDataProvider<DeviceDTO>();
    dataProvider.addDataDisplay(view.getTable());

    panel.setWidget(BiomedSite.CONTENT, view);

    view.showLoading();

    GetDevicesAction action = new GetDevicesAction();
    action.setClientId(getClientId());

    biomedService.execute(action, new DefaultCallback<ListResult<DeviceDTO>>() {
      @Override
      public void onSuccess(ListResult<DeviceDTO> result) {
        dataProvider.setList(result.getResult());
      }
    });
  }
}
