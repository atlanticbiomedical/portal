package com.biomed.client.ui.messages;

import javax.inject.Inject;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.CheckBox;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.github.gwtbootstrap.client.ui.ValueListBox;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasConstrainedValue;
import com.google.gwt.user.client.ui.HasValue;

public class MessagesPanel extends Composite implements Messages.View {

  interface Binder extends UiBinder<HTMLPanel, MessagesPanel> {
  }

  @UiField(provided = true)
  ValueListBox<MessageFor> messageForField;
  @UiField
  TextBox nameField;
  @UiField
  TextBox companyField;
  @UiField
  TextBox phoneField;
  @UiField
  TextBox phoneExtensionField;
  @UiField
  TextBox timeToReturnCallField;
  @UiField
  CheckBox telephonedField;
  @UiField
  CheckBox cameToSeeYouField;
  @UiField
  CheckBox wantsToSeeYouField;
  @UiField
  CheckBox returnedYourCallField;
  @UiField
  CheckBox pleaseCallField;
  @UiField
  CheckBox willCallAgainField;
  @UiField
  CheckBox rushField;
  @UiField
  CheckBox specialAttentionField;
  @UiField
  Button sendButton;

  @Inject
  public MessagesPanel(Binder binder) {
    messageForField = new ValueListBox<MessageFor>(new MessageFor.Renderer());
    initWidget(binder.createAndBindUi(this));
  }

  public HasConstrainedValue<MessageFor> getMessageForField() {
    return messageForField;
  }

  public HasValue<String> getNameField() {
    return nameField;
  }

  public HasValue<String> getCompanyField() {
    return companyField;
  }

  public HasValue<String> getPhoneField() {
    return phoneField;
  }

  public HasValue<String> getPhoneExtensionField() {
    return phoneExtensionField;
  }

  public HasValue<String> getTimeToReturnCallField() {
    return timeToReturnCallField;
  }

  public HasValue<Boolean> getTelephonedField() {
    return telephonedField;
  }

  public HasValue<Boolean> getCameToSeeYouField() {
    return cameToSeeYouField;
  }

  public HasValue<Boolean> getWantsToSeeYouField() {
    return wantsToSeeYouField;
  }

  public HasValue<Boolean> getReturnedYourCallField() {
    return returnedYourCallField;
  }

  public HasValue<Boolean> getPleaseCallField() {
    return pleaseCallField;
  }

  public HasValue<Boolean> getWillCallAgainField() {
    return willCallAgainField;
  }

  public HasValue<Boolean> getRushField() {
    return rushField;
  }

  public HasValue<Boolean> getSpecialAttentionField() {
    return specialAttentionField;
  }

  public HasClickHandlers getSendButton() {
    return sendButton;
  }
}
