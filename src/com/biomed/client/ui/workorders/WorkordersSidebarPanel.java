package com.biomed.client.ui.workorders;

import java.util.Date;

import javax.inject.Inject;

import com.biomed.client.ui.renderers.ClientDTORenderer;
import com.biomed.client.ui.renderers.UserDTORenderer;
import com.biomed.client.ui.renderers.WorkorderStatusDTORenderer;
import com.biomed.shared.api.dto.ClientDTO;
import com.biomed.shared.api.dto.UserDTO;
import com.biomed.shared.api.dto.WorkorderStatusDTO;
import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.ValueListBox;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasConstrainedValue;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DateBox.Format;

public class WorkordersSidebarPanel extends Composite {

  interface Binder extends UiBinder<HTMLPanel, WorkordersSidebarPanel> {
  }

  private static final String ALL = "- ALL -";
  
  @UiField
  Button searchButton;
  @UiField
  DateBox startDateField;
  @UiField
  DateBox endDateField;
  @UiField(provided = true)
  ValueListBox<ClientDTO> clientField;
  @UiField(provided = true)
  ValueListBox<WorkorderStatusDTO> workorderStatusField;
  @UiField(provided = true)
  ValueListBox<UserDTO> techField;

  @Inject
  public WorkordersSidebarPanel(Binder binder) {
    clientField = new ValueListBox<ClientDTO>(new ClientDTORenderer(ALL));
    workorderStatusField = new ValueListBox<WorkorderStatusDTO>(new WorkorderStatusDTORenderer(ALL));
    techField = new ValueListBox<UserDTO>(new UserDTORenderer(ALL));

    initWidget(binder.createAndBindUi(this));

    Format format = new DateBox.DefaultFormat(DateTimeFormat.getShortDateFormat());

    startDateField.setFormat(format);
    startDateField.setValue(new Date());
    endDateField.setFormat(format);
    endDateField.setValue(new Date());
  }

  public HasClickHandlers getSearchButton() {
    return searchButton;
  }

  public HasValue<Date> getStartDateField() {
    return startDateField;
  }

  public HasValue<Date> getEndDateField() {
    return endDateField;
  }

  public HasConstrainedValue<ClientDTO> getClientField() {
    return clientField;
  }

  public HasConstrainedValue<WorkorderStatusDTO> getWorkorderStatusField() {
    return workorderStatusField;
  }

  public HasConstrainedValue<UserDTO> getTechField() {
    return techField;
  }
}
