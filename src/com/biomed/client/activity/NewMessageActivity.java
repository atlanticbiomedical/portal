package com.biomed.client.activity;

import java.util.Arrays;

import javax.inject.Inject;

import com.biomed.client.AbstractBiomedActivity;
import com.biomed.client.BiomedActivityManager.SiteContainer;
import com.biomed.client.BiomedSite;
import com.biomed.client.dispatch.DefaultCallback;
import com.biomed.client.services.BiomedService;
import com.biomed.client.ui.messages.MessageFor;
import com.biomed.client.ui.messages.MessagesPanel;
import com.biomed.shared.api.EmptyResult;
import com.biomed.shared.api.SendMessageAction;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.web.bindery.event.shared.EventBus;

public class NewMessageActivity extends AbstractBiomedActivity {

  @Inject BiomedService biomedService;
  @Inject MessagesPanel view;

  @Override
  public void start(SiteContainer container, EventBus eventBus) {
    container.setWidget(BiomedSite.CONTENT, view);

    view.getMessageForField().setValue(MessageFor.ChrisEndres);
    view.getMessageForField().setAcceptableValues(Arrays.asList(MessageFor.values()));

    view.getSendButton().addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        SendMessageAction action = new SendMessageAction();
        action.setUserId(view.getMessageForField().getValue().getId());
        action.setName(view.getNameField().getValue());
        action.setCompany(view.getCompanyField().getValue());
        action.setPhone(view.getPhoneField().getValue());
        action.setPhoneExtension(view.getPhoneExtensionField().getValue());
        action.setTimeToReturnCall(view.getTimeToReturnCallField().getValue());
        action.setTelephoned(view.getTelephonedField().getValue());
        action.setCameToSeeYou(view.getCameToSeeYouField().getValue());
        action.setWantsToSeeYou(view.getWantsToSeeYouField().getValue());
        action.setReturnedYourCall(view.getReturnedYourCallField().getValue());
        action.setPleaseCall(view.getPleaseCallField().getValue());
        action.setWillCallAgain(view.getWillCallAgainField().getValue());
        action.setRush(view.getRushField().getValue());
        action.setSpecialAttention(view.getSpecialAttentionField().getValue());

        biomedService.execute(action, new DefaultCallback<EmptyResult>() {

          @Override
          public void onSuccess(EmptyResult result) {
            History.back();
          }
        });
      }
    });
  }
}
