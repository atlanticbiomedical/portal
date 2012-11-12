package com.biomed.client.ui.schedule;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import com.biomed.client.activity.ViewScheduleActivity;
import com.biomed.client.ui.renderers.ClientDTORenderer;
import com.biomed.client.ui.renderers.ScheduleTimeRenderer;
import com.biomed.client.ui.renderers.UserDTORenderer;
import com.biomed.client.ui.renderers.WorkorderReasonDTORenderer;
import com.biomed.client.ui.renderers.WorkorderStatusDTORenderer;
import com.biomed.shared.api.dto.ClientDTO;
import com.biomed.shared.api.dto.ScheduleTime;
import com.biomed.shared.api.dto.UserDTO;
import com.biomed.shared.api.dto.WorkorderReasonDTO;
import com.biomed.shared.api.dto.WorkorderStatusDTO;
import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.github.gwtbootstrap.client.ui.ValueListBox;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.LIElement;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.dom.client.UListElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasConstrainedValue;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DateBox.Format;
import com.google.gwt.user.datepicker.client.DatePicker;

public class ScheduleSidebarPanel extends Composite implements Schedule.Sidebar {

  public interface Templates extends SafeHtmlTemplates {

    @Template("<li><a data=\"{1}\"><span class=\"contactName\">{0}</span><span class=\"style_away\"></span><span class=\"clear\"></span></a></li>")
    SafeHtml techItem(String name, int techId);
  }

  interface Binder extends UiBinder<HTMLPanel, ScheduleSidebarPanel> {
  }

  @UiField
  LIElement scheduleItem;
  @UiField
  LIElement techsItem;
  @UiField
  LIElement reportsItem;
  @UiField
  Anchor techsButton;
  @UiField
  Anchor scheduleButton;

  @UiField
  HTMLPanel techs;
  @UiField
  HTMLPanel schedule;
  @UiField
  HTMLPanel reports;
  @UiField
  UListElement userListPanel;
  @UiField
  DatePicker datePicker;

  @UiField(provided = true)
  ValueListBox<ScheduleTime> startTimePicker;

  @UiField(provided = true)
  ValueListBox<ScheduleTime> endTimePicker;

  @UiField(provided = true)
  ValueListBox<UserDTO> techPicker;

  @UiField(provided = true)
  ValueListBox<UserDTO> reportTechPicker;

  @UiField(provided = true)
  ValueListBox<ClientDTO> clientPicker;

  @UiField(provided = true)
  ValueListBox<WorkorderStatusDTO> workorderStatusField;

  @UiField(provided = true)
  ValueListBox<WorkorderReasonDTO> reasonPicker;

  @UiField
  Button saveButton;

  @UiField
  Button sendReport;

  @UiField DateBox jobDateField;
  @UiField DateBox reportDateField;
  @UiField TextBox requestedByField;
  @UiField TextBox remarksField;


  private final Templates templates;

  public ViewScheduleActivity activity;

  @Inject
  public ScheduleSidebarPanel(Binder binder, Templates templates) {
    this.templates = templates;

    startTimePicker = new ValueListBox<ScheduleTime>(new ScheduleTimeRenderer(null));
    endTimePicker = new ValueListBox<ScheduleTime>(new ScheduleTimeRenderer(null));
    techPicker = new ValueListBox<UserDTO>(new UserDTORenderer("Please select ..."));
    reportTechPicker = new ValueListBox<UserDTO>(new UserDTORenderer("Please select ..."));
    clientPicker = new ValueListBox<ClientDTO>(new ClientDTORenderer("Please select ..."));
    workorderStatusField = new ValueListBox<WorkorderStatusDTO>(new WorkorderStatusDTORenderer(null));
    reasonPicker = new ValueListBox<WorkorderReasonDTO>(new WorkorderReasonDTORenderer(null));

    initWidget(binder.createAndBindUi(this));
    schedule.setVisible(false);
    reports.setVisible(false);

    Format format = new DateBox.DefaultFormat(DateTimeFormat.getShortDateFormat());
    jobDateField.setFormat(format);
    jobDateField.setValue(new Date());

    reportDateField.setFormat(format);
    reportDateField.setValue(new Date());
    
    datePicker.setValue(new Date(), false);
  }

  public void setTechList(final List<UserDTO> techs) {

    techPicker.setAcceptableValues(techs);
    reportTechPicker.setAcceptableValues(techs);

    SafeHtmlBuilder builder = new SafeHtmlBuilder();
    builder.append(templates.techItem("- All Techs -", 0));

    for (final UserDTO t : techs) {
      builder.append(templates.techItem(t.getDisplayName(), t.getId()));
    }
    userListPanel.setInnerSafeHtml(builder.toSafeHtml());

    NodeList<Element> elements = userListPanel.getElementsByTagName("a");
    for (int i = 0; i < elements.getLength(); i++) {
      final com.google.gwt.user.client.Element anchor = elements.getItem(i).cast();

      DOM.setEventListener(anchor, new EventListener() {
        @Override
        public void onBrowserEvent(Event event) {
          Element target = event.getCurrentTarget();

          int techId = Integer.parseInt(target.getAttribute("data"));
          activity.setTechId(techId);

          if (techId == 0) {
            techPicker.setValue(null);
          }
          for (UserDTO tech : techs) {
            if (tech.getId() == techId) {
              techPicker.setValue(tech);
              break;
            }
          }
        }
      });
      DOM.sinkEvents(anchor, Event.ONCLICK);
    }
  }

  @UiHandler("datePicker")
  void onDatePickerValueChanged(ValueChangeEvent<Date> event) {
    activity.setDate(event.getValue());
    jobDateField.setValue(event.getValue(), false);
  }

  @UiHandler("jobDateField")
  void onJobDateFieldValueChanged(ValueChangeEvent<Date> event) {
    activity.setDate(event.getValue());
    datePicker.setValue(event.getValue(), false);
  }

  @UiHandler("techsButton")
  void onTechsButtonClicked(ClickEvent event) {
    techsItem.setClassName("clicked");
    scheduleItem.setClassName("");
    reportsItem.setClassName("");
    techs.setVisible(true);
    schedule.setVisible(false);
    reports.setVisible(false);
  }

  @UiHandler("scheduleButton")
  void onScheduleButtonClicked(ClickEvent event) {
    techsItem.setClassName("");
    scheduleItem.setClassName("clicked");
    reportsItem.setClassName("");
    techs.setVisible(false);
    schedule.setVisible(true);
    reports.setVisible(false);
  }

  @UiHandler("reportButton")
  void onReportButtonClicked(ClickEvent event) {
    techsItem.setClassName("");
    scheduleItem.setClassName("");
    reportsItem.setClassName("clicked");
    techs.setVisible(false);
    schedule.setVisible(false);
    reports.setVisible(true);
  }

  @UiHandler("techPicker")
  void onTechPickerValueChanged(ValueChangeEvent<UserDTO> event) {
    if (event.getValue() != null) {
      activity.setTechId(event.getValue().getId());
    } else {
      activity.setTechId(0);
    }
  }

  @UiHandler("sendReport")
  void onSendReportButtonClicked(ClickEvent event) {
    activity.sendReport();
  }

  public HasValue<Date> getJobDateField() {
    return jobDateField;
  }

  public HasValue<Date> getReportDateField() {
    return reportDateField;
  }

  public HasConstrainedValue<ScheduleTime> getStartTimeField() {
    return startTimePicker;
  }

  public HasConstrainedValue<ScheduleTime> getEndTimeField() {
    return endTimePicker;
  }

  public HasConstrainedValue<ClientDTO> getClientField() {
    return clientPicker;
  }

  public HasConstrainedValue<UserDTO> getTechField() {
    return techPicker;
  }

  public HasConstrainedValue<UserDTO> getReportTechField() {
    return reportTechPicker;
  }

  public HasConstrainedValue<WorkorderStatusDTO> getWorkorderStatusField() {
    return workorderStatusField;
  }

  public HasConstrainedValue<WorkorderReasonDTO> getReasonField() {
    return reasonPicker;
  }

  public HasValue<String> getRequestedByField() {
    return requestedByField;
  }

  public HasValue<String> getRemarksField() {
    return remarksField;
  }

  public HasClickHandlers getSaveButton() {
    return saveButton;
  }
}
