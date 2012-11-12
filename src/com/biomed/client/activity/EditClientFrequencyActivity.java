package com.biomed.client.activity;

import com.biomed.client.BiomedActivityManager.SiteContainer;
import com.biomed.client.BiomedSite;
import com.biomed.client.dispatch.DefaultCallback;
import com.biomed.client.services.BiomedService;
import com.biomed.client.ui.clienteditor.ClientEditorSidebarPanel.Tabs;
import com.biomed.client.ui.clienteditor.ClientFrequencyEditorPanel;
import com.biomed.shared.api.EmptyResult;
import com.biomed.shared.api.GetClientFrequencyAction;
import com.biomed.shared.api.SaveClientFrequenciesAction;
import com.biomed.shared.api.SingleResult;
import com.biomed.shared.api.dto.FrequencyDTO;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;

public class EditClientFrequencyActivity extends BaseClientActivity {

  @Inject
  ClientFrequencyEditorPanel view;
  @Inject
  BiomedService biomedService;

  @Override
  protected boolean showNavigationPanel() {
    return true;
  }

  @Override
  protected Tabs getTab() {
    return Tabs.FREQUENCY;
  }

  @Override
  protected void createContentPanel(SiteContainer panel) {
    panel.setWidget(BiomedSite.CONTENT, view);

    GetClientFrequencyAction action = new GetClientFrequencyAction();
    action.setClientId(getClientId());

    biomedService.execute(action, new DefaultCallback<SingleResult<FrequencyDTO>>() {
      @Override
      public void onSuccess(SingleResult<FrequencyDTO> result) {
        view.setFrequencies(result.getResult());
      }
    });

    view.getSaveButton().addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        SaveClientFrequenciesAction action = new SaveClientFrequenciesAction();
        action.setClientId(getClientId());
        action.setFrequencies(view.getFrequencies());

        biomedService.execute(action, new DefaultCallback<EmptyResult>() {
          @Override
          public void onSuccess(EmptyResult result) {
            
          }
        });
      }
    });
  }
}
