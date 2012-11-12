package com.biomed.client.activity;

import javax.inject.Inject;

import com.biomed.client.dispatch.DefaultCallback;
import com.biomed.client.services.BiomedService;
import com.biomed.client.ui.clienteditor.ClientOverviewEditorPanel;
import com.biomed.shared.api.EmptyResult;
import com.biomed.shared.api.SaveClientAction;
import com.biomed.shared.api.dto.ClientDTO;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public abstract class BaseClientOverviewActivity extends BaseClientActivity  {

  @Inject
  ClientOverviewEditorPanel view;

  @Inject
  BiomedService biomedService;

  ClientDTO client;

  @SuppressWarnings("unchecked")
  protected void setupEvents() {
    view.getIdentifierField().addValueChangeHandler(this);
    view.getNameField().addValueChangeHandler(this);
    view.getPrimaryAddressField().addValueChangeHandler(this);
    view.getPrimaryAddress2Field().addValueChangeHandler(this);
    view.getPrimaryCityField().addValueChangeHandler(this);
    view.getPrimaryStateField().addValueChangeHandler(this);
    view.getPrimaryZipField().addValueChangeHandler(this);
    view.getPrimaryContactField().addValueChangeHandler(this);
    view.getPrimaryPhoneField().addValueChangeHandler(this);
    view.getPrimaryEmailField().addValueChangeHandler(this);
    view.getSecondaryContactField().addValueChangeHandler(this);
    view.getSecondaryPhoneField().addValueChangeHandler(this);
    view.getSecondaryEmailField().addValueChangeHandler(this);
    view.getNotesField().addValueChangeHandler(this);

    view.getSaveButton().addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        saveClient();
      }
    });
  }

  protected void setView(ClientDTO client) {
    view.getIdentifierField().setValue(client.getIdentifier());
    view.getNameField().setValue(client.getName());
    view.getPrimaryAddressField().setValue(client.getPrimaryAddress());
    view.getPrimaryAddress2Field().setValue(client.getPrimaryAddress2());
    view.getPrimaryCityField().setValue(client.getPrimaryCity());
    view.getPrimaryStateField().setValue(client.getPrimaryState());
    view.getPrimaryZipField().setValue(client.getPrimaryZip());
    view.getPrimaryContactField().setValue(client.getPrimaryContactName());
    view.getPrimaryPhoneField().setValue(client.getPrimaryPhone());
    view.getPrimaryEmailField().setValue(client.getPrimaryEmail());
    view.getSecondaryContactField().setValue(client.getSecondaryContactName());
    view.getSecondaryPhoneField().setValue(client.getSecondaryPhone());
    view.getSecondaryEmailField().setValue(client.getSecondaryEmail());
    view.getNotesField().setValue(client.getNotes());
  }

  protected void getView(ClientDTO client) {
    client.setIdentifier(view.getIdentifierField().getValue());
    client.setName(view.getNameField().getValue());
    client.setPrimaryAddress(view.getPrimaryAddressField().getValue());
    client.setPrimaryAddress2(view.getPrimaryAddress2Field().getValue());
    client.setPrimaryCity(view.getPrimaryCityField().getValue());
    client.setPrimaryState(view.getPrimaryStateField().getValue());
    client.setPrimaryZip(view.getPrimaryZipField().getValue());
    client.setPrimaryContactName(view.getPrimaryContactField().getValue());
    client.setPrimaryPhone(view.getPrimaryPhoneField().getValue());
    client.setPrimaryEmail(view.getPrimaryEmailField().getValue());
    client.setSecondaryContactName(view.getSecondaryContactField().getValue());
    client.setSecondaryPhone(view.getSecondaryPhoneField().getValue());
    client.setSecondaryEmail(view.getSecondaryEmailField().getValue());
    client.setNotes(view.getNotesField().getValue());
  }

  private void saveClient() {
    getView(client);

    SaveClientAction action = new SaveClientAction();
    action.setClient(client);

    biomedService.execute(action, new DefaultCallback<EmptyResult>() {

      @Override
      public void onSuccess(EmptyResult result) {
        resetDirtyFlag();
        onSaveComplete();
      }
    });
  }

  protected void onSaveComplete() {

  }
}
