package com.biomed.server;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.inject.Inject;

import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.auth.oauth2.TokenResponseException;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleRefreshTokenRequest;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.CalendarList;
import com.google.api.services.calendar.model.CalendarListEntry;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class CalendarApi {
  private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
  private static final JsonFactory JSON_FACTORY = new JacksonFactory();
  private static final String CLIENT_ID = "333768673996-904fuljpb428q9r37m2uujislhal5kt9.apps.googleusercontent.com";
  private static final String CLIENT_SECRET = "PAmHruqVbAXzEnnTwelx-Ll7";
  private static final String REFRESH_TOKEN = "1/fWvZebnDAhY0MlgK1IImcrGIDm4ZlILiRM8_47HsUFc";

  @Inject
  public CalendarApi() {
  }

  public String scheduleEvent(String email, Date startDate, Date endDate, String summary, String description, String location) {
    EventAttendee attendee = new EventAttendee();
    attendee.setEmail(email);

    Event event = new Event();
    event.setSummary(summary);
    event.setLocation(location);
    event.setDescription(description);

    List<EventAttendee> attendees = Lists.newArrayList();
    attendees.add(attendee);
    event.setAttendees(attendees);

    DateTime start = new DateTime(startDate, TimeZone.getTimeZone("UTC"));
    event.setStart(new EventDateTime().setDateTime(start));
    DateTime end = new DateTime(endDate, TimeZone.getTimeZone("UTC"));
    event.setEnd(new EventDateTime().setDateTime(end));

    Calendar calendar = buildCalendar();
    try {
      Event resultEvent = calendar.events().insert("api@atlanticbiomedical.com", event).execute();
      return resultEvent.getId();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void rescheduleEvent(String eventId, String email, Date startDate, Date endDate) {
    Calendar calendar = buildCalendar();

    try {
      Event event = calendar.events().get("api@atlanticbiomedical.com", eventId).execute();

      EventAttendee attendee = new EventAttendee();
      attendee.setEmail(email);
      List<EventAttendee> attendees = event.getAttendees();
      attendees.clear();
      attendees.add(attendee);

      DateTime start = new DateTime(startDate, TimeZone.getTimeZone("UTC"));
      event.setStart(new EventDateTime().setDateTime(start));
      DateTime end = new DateTime(endDate, TimeZone.getTimeZone("UTC"));
      event.setEnd(new EventDateTime().setDateTime(end));

      calendar.events().update("api@atlanticbiomedical.com", eventId, event).execute();
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void deleteEvent(String eventId) {
    Calendar calendar = buildCalendar();

    try {
      calendar.events().delete("api@atlanticbiomedical.com", eventId).execute();
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static Map<String, String> buildCalendarMap() {
    Map<String, String> result = Maps.newHashMap();

    try {
      Calendar calendar = buildCalendar();
      CalendarList feed = calendar.calendarList().list().execute();
      for (CalendarListEntry entry : feed.getItems()) {
        String summary = entry.getSummary();
        if (summary != null && summary.endsWith("atlanticbiomedical.com")) {
          result.put(summary, entry.getId());
        }

        System.out.println("Found Calendar: " + summary + " -> " + entry.getId());
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return result;
  }

  public void test() throws IOException {
    Calendar calendar = buildCalendar();
    CalendarList feed = calendar.calendarList().list().execute();
    for (CalendarListEntry entry : feed.getItems()) {
      System.out.println(entry.getId());
    }
  }

  private static Calendar buildCalendar() {
    try {
      GoogleCredential credential = new GoogleCredential().setAccessToken(getAccessToken());
      Calendar calendar = Calendar.builder(HTTP_TRANSPORT, JSON_FACTORY)
          .setApplicationName("BiomedPortal/1.0").setHttpRequestInitializer(credential).build();
      return calendar;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private static String getAccessToken() throws TokenResponseException, IOException {
    TokenResponse response = new GoogleRefreshTokenRequest(HTTP_TRANSPORT, JSON_FACTORY,
        REFRESH_TOKEN, CLIENT_ID, CLIENT_SECRET).execute();
    return response.getAccessToken();
  }
}
