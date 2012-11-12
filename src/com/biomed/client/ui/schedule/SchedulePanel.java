package com.biomed.client.ui.schedule;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import com.biomed.client.resources.BiomedAppointmentTheme;
import com.bradrydzewski.gwt.calendar.client.Appointment;
import com.bradrydzewski.gwt.calendar.client.Calendar;
import com.bradrydzewski.gwt.calendar.client.CalendarSettings;
import com.bradrydzewski.gwt.calendar.client.CalendarViews;
import com.google.common.collect.Lists;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.maps.gwt.client.Geocoder;
import com.google.maps.gwt.client.Geocoder.Callback;
import com.google.maps.gwt.client.GeocoderRequest;
import com.google.maps.gwt.client.GeocoderResult;
import com.google.maps.gwt.client.GeocoderStatus;
import com.google.maps.gwt.client.GoogleMap;
import com.google.maps.gwt.client.LatLng;
import com.google.maps.gwt.client.MapOptions;
import com.google.maps.gwt.client.MapTypeId;
import com.google.maps.gwt.client.Marker;
import com.google.maps.gwt.client.MarkerOptions;

public class SchedulePanel extends Composite implements Schedule.View {

  interface Binder extends UiBinder<HTMLPanel, SchedulePanel> {
  }

  @UiField
  HTMLPanel rootPanel;
  @UiField
  FlowPanel calContainer;
  @UiField
  FlowPanel mapContainer;

  private static final LatLng center = LatLng.create(39.257147, -76.685686);

  private Calendar calendar;
  private GoogleMap map;
  private Geocoder geocoder;

  private List<Marker> markers = Lists.newArrayList();
  private List<Appointment> appointments = Lists.newArrayList();

  @Inject
  public SchedulePanel(Binder binder) {
    initWidget(binder.createAndBindUi(this));

    buildCalendar();
    buildMap();

    Window.addResizeHandler(new ResizeHandler() {
      @Override
      public void onResize(ResizeEvent event) {
        handleResize(event.getWidth(), event.getHeight());
      }
    });
  }

  @Override
  protected void onLoad() {
    super.onLoad();

    handleResize(Window.getClientWidth(), Window.getClientHeight());
  }

  private void handleResize(int width, int height) {
    width = width - 312;
    height = height - 49;

    mapContainer.setWidth(width + "px");
    mapContainer.setHeight((height * 0.40) + "px");
    calContainer.setWidth(width + "px");
    calContainer.setHeight((height * 0.60) + "px");

    if (calendar != null) {
      calendar.doSizing();
    }

    map.triggerResize();
    map.panTo(center);
  }

  private void buildCalendar() {
    CalendarSettings settings = new CalendarSettings();
    settings.setEnableDragDrop(false);
    settings.setEnableDragDropCreation(false);

    calendar = new Calendar();
    calendar.setSettings(settings);
    calendar.setView(CalendarViews.DAY);
    calendar.setWidth("100%");
    calendar.setHeight("100%");
    calContainer.add(calendar);
  }

  public void addWorkorders(List<Workorder> workorders) {
    calendar.clearAppointments();
    appointments.clear();
    calendar.suspendLayout();

    for (Marker m : markers) {
      m.setMap((GoogleMap) null);
    }
    markers.clear();

    for (Workorder w : workorders) {
      String address = null;
      if (w.clientAddress != null) {
        address = w.clientAddress + "\n" + w.clientCity + ", " + w.clientState + ". " + w.clientZip;
      }

      String description = "<b>" + w.clientName + "</b><br />(" + w.clientIdentifier + ")<br />";
      if (address != null) {
        description += "<br />" + address;
      }
      BiomedAppointment apt = new BiomedAppointment();
      apt.setTitle(w.techName);
      apt.setDescription(description);
      apt.setLocation(address);
      apt.setStart(w.jobStart);
      apt.setEnd(w.jobEnd);
      apt.setTheme(BiomedAppointmentTheme.STYLES.get(w.color));

      calendar.addAppointment(apt);
      appointments.add(apt);

      if (address != null) {
        GeocoderRequest request = GeocoderRequest.create();
        request.setAddress(address);
        geocoder.geocode(request, new Callback() {
          @Override
          public void handle(JsArray<GeocoderResult> a, GeocoderStatus b) {
            if (b == GeocoderStatus.OK) {
              LatLng location = a.get(0).getGeometry().getLocation();
              MarkerOptions options = MarkerOptions.create();
              options.setPosition(location);
              options.setMap(map);
              markers.add(Marker.create(options));
            }
          }
        });
      }
    }

    calendar.resumeLayout();
    calendar.doLayout();
  }

  public void setView(Date date, int days) {
    calendar.setDate(date, days);
  }

  private void buildMap() {
    MapOptions myOptions = MapOptions.create();
    myOptions.setZoom(8.0);
    myOptions.setCenter(center);
    myOptions.setMapTypeId(MapTypeId.ROADMAP);
    mapContainer.setWidth("500px");
    mapContainer.setHeight("500px");
    map = GoogleMap.create(mapContainer.getElement(), myOptions);

    MarkerOptions options = MarkerOptions.create();
    options.setPosition(center);
    options.setMap(map);
    Marker.create(options);

    geocoder = Geocoder.create();
  }
}
