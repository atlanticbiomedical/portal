package com.biomed.client.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.biomed.client.AbstractBiomedActivity;
import com.biomed.client.BiomedActivityManager.SiteContainer;
import com.biomed.client.BiomedSite;
import com.biomed.client.dispatch.DefaultCallback;
import com.biomed.client.services.BiomedService;
import com.biomed.client.ui.schedule.DateUtil;
import com.biomed.client.ui.schedule.SchedulePanel;
import com.biomed.client.ui.schedule.ScheduleSidebarPanel;
import com.biomed.client.ui.schedule.Workorder;
import com.biomed.shared.api.EmptyResult;
import com.biomed.shared.api.GetClientsAction;
import com.biomed.shared.api.GetClientsAction.View;
import com.biomed.shared.api.GetUsersAction;
import com.biomed.shared.api.ListResult;
import com.biomed.shared.api.PropertyBag;
import com.biomed.shared.api.ScheduleAction;
import com.biomed.shared.api.SendTechScheduleEmailAction;
import com.biomed.shared.api.SqlResultSet;
import com.biomed.shared.api.dto.ClientDTO;
import com.biomed.shared.api.dto.ScheduleTime;
import com.biomed.shared.api.dto.UserDTO;
import com.biomed.shared.api.dto.WorkorderReasonDTO;
import com.biomed.shared.api.dto.WorkorderStatusDTO;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.web.bindery.event.shared.EventBus;

public class ViewScheduleActivity extends AbstractBiomedActivity {

  private static final DateTimeFormat format = DateTimeFormat.getFormat("yyyy-MM-dd");

  @Inject
  SchedulePanel view;
  @Inject
  ScheduleSidebarPanel sidebar;
  @Inject
  BiomedService biomedService;

  int colorIndex = 0;
  private Map<Integer, Integer> techColorMap;

  private Date currentDate = new Date();
  private int techId = 0;

  @Override
  public void start(SiteContainer container, EventBus eventBus) {
    sidebar.activity = this;

    container.setWidget(BiomedSite.CONTENT, view);
    container.setWidget(BiomedSite.SIDEBAR, sidebar);

    loadTechList();
    loadClients();

    sidebar.getWorkorderStatusField().setValue(WorkorderStatusDTO.PM_DUE);
    sidebar.getWorkorderStatusField().setAcceptableValues(WorkorderStatusDTO.VALUES);

    sidebar.getStartTimeField().setValue(ScheduleTime.H8M00A);
    sidebar.getStartTimeField().setAcceptableValues(ScheduleTime.getValues());

    sidebar.getEndTimeField().setValue(ScheduleTime.H5M00P);
    sidebar.getEndTimeField().setAcceptableValues(ScheduleTime.getValues());

    sidebar.getReasonField().setValue(WorkorderReasonDTO.PM_RESCHEDULE);
    sidebar.getReasonField().setAcceptableValues(WorkorderReasonDTO.VALUES);

    sidebar.getSaveButton().addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        saveWorkorder();
      }
    });
  }

  public void setTechId(int techId) {
    this.techId = techId;
    updateView();
  }

  public void setDate(Date currentDate) {
    this.currentDate = currentDate;
    updateView();
  }

  private void saveWorkorder() {
    ScheduleAction action = new ScheduleAction();
    action.setJobDate(sidebar.getJobDateField().getValue());
    action.setClientId(sidebar.getClientField().getValue().getId());
    action.setTechId(sidebar.getTechField().getValue().getId());
    action.setStartTime(sidebar.getStartTimeField().getValue());
    action.setEndTime(sidebar.getEndTimeField().getValue());
    action.setReason(sidebar.getReasonField().getValue());
    action.setRequestedBy(sidebar.getRequestedByField().getValue());
    action.setRemarks(sidebar.getRemarksField().getValue());
    action.setStatus(sidebar.getWorkorderStatusField().getValue());

    biomedService.execute(action, new DefaultCallback<EmptyResult>() {

      @Override
      public void onSuccess(EmptyResult result) {
        updateView();
      }
    });
  }

  public void sendReport() {
    SendTechScheduleEmailAction action = new SendTechScheduleEmailAction();
    action.setUserId(sidebar.getReportTechField().getValue().getId());
    action.setDate(sidebar.getReportDateField().getValue());

    biomedService.execute(action, new DefaultCallback<EmptyResult>() {

      @Override
      public void onSuccess(EmptyResult result) {
      }
    });
  }

  private void updateView() {
    if (techId != 0) {
      loadScheduleForTech(currentDate, techId);
    } else {
      loadScheduleForDay(currentDate);
    }
  }

  private void loadTechList() {
    GetUsersAction action = new GetUsersAction();
    action.setUserTypeId(1);

    biomedService.execute(action, new DefaultCallback<ListResult<UserDTO>>() {

      @Override
      public void onSuccess(ListResult<UserDTO> result) {
        colorIndex = 0;
        techColorMap = Maps.newHashMap();

        for (UserDTO user : result.getResult()) {
          techColorMap.put(user.getId(), colorIndex);
          colorIndex = (colorIndex + 1) % 12;
        }

        sidebar.setTechList(result.getResult());
        loadScheduleForDay(DateUtil.getNextWorkDay(new Date()));
      }
    });
  }

  private void loadScheduleForDay(final Date day) {
    biomedService.getSchedule(format.format(day), format.format(day), 0,
        new DefaultCallback<SqlResultSet>() {
          @Override
          public void onSuccess(SqlResultSet result) {
            List<Workorder> workorders = Lists.newArrayList();
            for (PropertyBag bag : result.results) {
              Workorder workorder = new Workorder(bag);
              workorder.color = techColorMap.get(workorder.techId);
              workorders.add(workorder);
            }
            view.setView(day, 1);
            view.addWorkorders(workorders);
          }
        });
  }

  private void loadScheduleForTech(final Date date, final int techId) {
    final Date startDate = DateUtil.getWorkFirstDay(date);
    final Date endDate = DateUtil.addDays(date, 5);

    biomedService.getSchedule(format.format(startDate), format.format(endDate), techId,
        new DefaultCallback<SqlResultSet>() {
          @Override
          public void onSuccess(SqlResultSet result) {
            List<Workorder> workorders = Lists.newArrayList();
            for (PropertyBag bag : result.results) {
              Workorder workorder = new Workorder(bag);
              workorder.color = techColorMap.get(workorder.techId);
              workorders.add(workorder);
            }
            view.setView(startDate, 5);
            view.addWorkorders(workorders);
          }
        });
  }

  private void loadClients() {
    GetClientsAction action = new GetClientsAction();
    action.setView(View.NAMES_ONLY);

    biomedService.execute(action, new DefaultCallback<ListResult<ClientDTO>>() {

      @Override
      public void onSuccess(ListResult<ClientDTO> result) {
        ArrayList<ClientDTO> results = result.getResult();
        Collections.sort(results, new ClientDTO.IdentifierComparator());

        sidebar.getClientField().setAcceptableValues(results);
      }
    });
  }
}
