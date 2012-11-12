package com.biomed.client.ui.workorders;

import java.util.Date;

import javax.inject.Inject;

import com.biomed.client.ui.renderers.ScheduleTimeRenderer;
import com.biomed.client.ui.renderers.UserDTORenderer;
import com.biomed.client.ui.renderers.WorkorderReasonDTORenderer;
import com.biomed.client.ui.renderers.WorkorderStatusDTORenderer;
import com.biomed.shared.api.dto.EditWorkorderViewDTO;
import com.biomed.shared.api.dto.ScheduleTime;
import com.biomed.shared.api.dto.UserDTO;
import com.biomed.shared.api.dto.WorkorderReasonDTO;
import com.biomed.shared.api.dto.WorkorderStatusDTO;
import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.TextArea;
import com.github.gwtbootstrap.client.ui.ValueListBox;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasConstrainedValue;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DateBox.Format;

public class EditWorkorderPanel extends Composite {
  interface Binder extends UiBinder<HTMLPanel, EditWorkorderPanel> {
  }

  private static final DateTimeFormat format = DateTimeFormat
      .getFormat(PredefinedFormat.DATE_MEDIUM);

  @UiField
  Button saveButton;
  @UiField
  Button deleteButton;
  @UiField
  Label workorderId;
  @UiField
  Label workorderScheduledDate;
  @UiField
  Label clientName;
  @UiField
  Label clientAddress;
  @UiField
  Label clientAddress2;
  @UiField
  Label clientCity;
  @UiField
  Label clientState;
  @UiField
  Label clientZip;
  @UiField
  Label clientPhone;
  @UiField
  Label clientPrimaryContact;
  @UiField
  Label workorderStartTime;
  @UiField
  Label workorderEndTime;
  @UiField
  Label workorderTechName;
  @UiField(provided = true)
  ValueListBox<ScheduleTime> startTimePicker;
  @UiField(provided = true)
  ValueListBox<ScheduleTime> endTimePicker;
  @UiField(provided = true)
  ValueListBox<UserDTO> techPicker;
  @UiField(provided = true)
  ValueListBox<WorkorderReasonDTO> workorderReasonField;
  @UiField(provided = true)
  ValueListBox<WorkorderStatusDTO> workorderStatusField;
  @UiField
  TextArea actionTakenField;
  @UiField
  TextArea remarksField;
  @UiField
  DateBox workorderJobDateField;

  @Inject
  public EditWorkorderPanel(Binder binder) {
    startTimePicker = new ValueListBox<ScheduleTime>(new ScheduleTimeRenderer(null));
    endTimePicker = new ValueListBox<ScheduleTime>(new ScheduleTimeRenderer(null));
    techPicker = new ValueListBox<UserDTO>(new UserDTORenderer(null));

    workorderReasonField = new ValueListBox<WorkorderReasonDTO>(
        new WorkorderReasonDTORenderer(null));
    workorderReasonField.setValue(WorkorderReasonDTO.ADD_NEW_EQUIPMENT);
    workorderReasonField.setAcceptableValues(WorkorderReasonDTO.VALUES);

    workorderStatusField = new ValueListBox<WorkorderStatusDTO>(
        new WorkorderStatusDTORenderer(null));
    workorderStatusField.setValue(WorkorderStatusDTO.COMPLETE);
    workorderStatusField.setAcceptableValues(WorkorderStatusDTO.VALUES);

    initWidget(binder.createAndBindUi(this));

    Format format = new DateBox.DefaultFormat(DateTimeFormat.getShortDateFormat());
    workorderJobDateField.setFormat(format);
    workorderJobDateField.setValue(new Date());
  }

  public void setStaticValues(EditWorkorderViewDTO values) {
    workorderId.setText(Integer.toString(values.getWorkorderId()));
    if (values.getWorkorderScheduledDate() != null) {
      workorderScheduledDate.setText(format.format(values.getWorkorderScheduledDate()));
    }
    clientName.setText(values.getClientName());
    clientAddress.setText(values.getClientAddress());
    clientAddress2.setText(values.getClientAddress2());
    clientCity.setText(values.getClientCity());
    clientState.setText(values.getClientState());
    clientZip.setText(values.getClientZip());
    clientPhone.setText(values.getClientPhone());
    clientPrimaryContact.setText(values.getClientPrimaryContact());
    workorderReasonField.setValue(values.getWorkorderReason());
    workorderStatusField.setValue(values.getWorkorderStatus());
    actionTakenField.setValue(values.getActionTaken());
    remarksField.setValue(values.getRemarks());
  }

  public HasClickHandlers getSaveButton() {
    return saveButton;
  }

  public HasClickHandlers getDeleteButton() {
    return deleteButton;
  }

  public HasConstrainedValue<WorkorderReasonDTO> getWorkorderReasonField() {
    return workorderReasonField;
  }

  public HasConstrainedValue<WorkorderStatusDTO> getWorkorderStatusField() {
    return workorderStatusField;
  }

  public HasValue<String> getActionTakenField() {
    return actionTakenField;
  }

  public HasValue<String> getRemarksField() {
    return remarksField;
  }

  public HasConstrainedValue<ScheduleTime> getStartTimeField() {
    return startTimePicker;
  }

  public HasConstrainedValue<ScheduleTime> getEndTimeField() {
    return endTimePicker;
  }

  public HasConstrainedValue<UserDTO> getTechField() {
    return techPicker;
  }

  public HasValue<Date> getWorkorderJobDateField() {
    return workorderJobDateField;
  }
}
