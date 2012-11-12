package com.biomed.client.activity;

import com.biomed.client.BiomedActivityManager.SiteContainer;
import com.biomed.client.BiomedSite;
import com.biomed.client.dispatch.DefaultCallback;
import com.biomed.client.ui.clienteditor.ClientEditorSidebarPanel.Tabs;
import com.biomed.shared.api.GetClientAction;
import com.biomed.shared.api.SingleResult;
import com.biomed.shared.api.dto.ClientDTO;

public class EditClientOverviewActivity extends BaseClientOverviewActivity {

  @Override
  protected boolean showNavigationPanel() {
    return true;
  }

  @Override
  protected Tabs getTab() {
    return Tabs.OVERVIEW;
  }

  @Override
  protected void createContentPanel(final SiteContainer panel) {
    setupEvents();
    fetchClient(panel, getClientId());
  }

  private void fetchClient(final SiteContainer panel, int id) {
    GetClientAction action = new GetClientAction();
    action.setClientId(id);

    biomedService.execute(action, new DefaultCallback<SingleResult<ClientDTO>>() {

      @Override
      public void onSuccess(SingleResult<ClientDTO> result) {
        panel.setWidget(BiomedSite.CONTENT, view);
        client = result.getResult();
        setView(client);
      }
    });
  }
}
