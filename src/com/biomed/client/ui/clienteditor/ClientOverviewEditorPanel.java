package com.biomed.client.ui.clienteditor;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import com.biomed.shared.api.dto.State;
import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.TextArea;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.github.gwtbootstrap.client.ui.ValueListBox;
import com.google.common.collect.Lists;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasValue;

public class ClientOverviewEditorPanel extends Composite {

  interface Binder extends UiBinder<HTMLPanel, ClientOverviewEditorPanel> {
  }

  @UiField
  TextBox identifierField;

  @UiField
  TextBox nameField;

  @UiField
  TextBox primaryAddressField;

  @UiField
  TextBox primaryAddress2Field;

  @UiField
  TextBox primaryCityField;

  @UiField(provided = true)
  ValueListBox<State> primaryStateField;

  @UiField
  TextBox primaryZipField;

  @UiField
  TextBox primaryContactField;

  @UiField
  TextBox primaryPhoneField;

  @UiField
  TextBox primaryEmailField;

  @UiField
  TextBox secondaryContactField;

  @UiField
  TextBox secondaryPhoneField;

  @UiField
  TextBox secondaryEmailField;

  @UiField
  TextArea notesField;

  @UiField
  Button saveButton;

  @Inject
  public ClientOverviewEditorPanel(Binder binder) {
    List<State> values = Lists.newArrayList(State.values());

    primaryStateField = new ValueListBox<State>(new StateRenderer());
    primaryStateField.setAcceptableValues(values);

    initWidget(binder.createAndBindUi(this));
  }

  public HasValue<String> getIdentifierField() {
    return identifierField;
  }

  public HasValue<String> getNameField() {
    return nameField;
  }

  public HasValue<String> getPrimaryAddressField() {
    return primaryAddressField;
  }

  public HasValue<String> getPrimaryAddress2Field() {
    return primaryAddress2Field;
  }

  public HasValue<String> getPrimaryCityField() {
    return primaryCityField;
  }

  public HasValue<State> getPrimaryStateField() {
    return primaryStateField;
  }

  public HasValue<String> getPrimaryZipField() {
    return primaryZipField;
  }

  public HasValue<String> getNotesField() {
    return notesField;
  }

  public HasClickHandlers getSaveButton() {
    return saveButton;
  }

  public HasValue<String> getPrimaryPhoneField() {
    return primaryPhoneField;
  }

  public HasValue<String> getPrimaryEmailField() {
    return primaryEmailField;
  }

  public HasValue<String> getSecondaryPhoneField() {
    return secondaryPhoneField;
  }

  public HasValue<String> getSecondaryEmailField() {
    return secondaryEmailField;
  }

  public HasValue<String> getPrimaryContactField() {
    return primaryContactField;
  }

  public HasValue<String> getSecondaryContactField() {
    return secondaryContactField;
  }

  private class StateRenderer implements Renderer<State> {

    @Override
    public String render(State object) {
      if (object == null) {
        return "";
      }

      return object.toString();
    }

    @Override
    public void render(State object, Appendable appendable) throws IOException {
      if (object != null) {
        appendable.append(object.toString());
      }
    }
  }
}
