package com.biomed.client.activity;

import java.util.List;

import javax.inject.Inject;

import com.biomed.client.AbstractBiomedActivity;
import com.biomed.client.BiomedActivityManager.SiteContainer;
import com.biomed.client.BiomedSite;
import com.biomed.client.dispatch.DefaultCallback;
import com.biomed.client.place.EditWorkorderPlace;
import com.biomed.client.services.BiomedService;
import com.biomed.client.ui.workorders.EditWorkorderPanel;
import com.biomed.shared.api.DeleteWorkorderAction;
import com.biomed.shared.api.EmptyResult;
import com.biomed.shared.api.GetUsersAction;
import com.biomed.shared.api.GetWorkorderViewAction;
import com.biomed.shared.api.ListResult;
import com.biomed.shared.api.SaveWorkorderAction;
import com.biomed.shared.api.SingleResult;
import com.biomed.shared.api.dto.EditWorkorderViewDTO;
import com.biomed.shared.api.dto.ScheduleTime;
import com.biomed.shared.api.dto.UserDTO;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;

public class EditWorkorderActivity extends AbstractBiomedActivity {

  @Inject
  BiomedService biomedService;

  @Inject
  EditWorkorderPanel view;

  private List<UserDTO> techs;

  @Override
  public void start(SiteContainer container, EventBus eventBus) {
    container.setWidget(BiomedSite.CONTENT, view);

    view.getStartTimeField().setValue(ScheduleTime.H8M00A);
    view.getStartTimeField().setAcceptableValues(ScheduleTime.getValues());

    view.getEndTimeField().setValue(ScheduleTime.H5M00P);
    view.getEndTimeField().setAcceptableValues(ScheduleTime.getValues());

    view.getSaveButton().addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        SaveWorkorderAction action = new SaveWorkorderAction();
        action.setWorkorderReason(view.getWorkorderReasonField().getValue());
        action.setWorkorderStatus(view.getWorkorderStatusField().getValue());
        action.setActionTaken(view.getActionTakenField().getValue());
        action.setRemarks(view.getRemarksField().getValue());
        action.setId(getWorkorderId());
        action.setTechId(view.getTechField().getValue().getId());
        action.setJobDate(view.getWorkorderJobDateField().getValue());
        action.setStartTime(view.getStartTimeField().getValue());
        action.setEndTime(view.getEndTimeField().getValue());

        biomedService.execute(action, new DefaultCallback<EmptyResult>() {
          @Override
          public void onSuccess(EmptyResult result) {
          }
        });
      }

    });

    view.getDeleteButton().addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        if (Window.confirm("Are you sure you wish to delete this workorder?")) {
          DeleteWorkorderAction action = new DeleteWorkorderAction();
          action.setWorkorderId(getWorkorderId());

          biomedService.execute(action, new DefaultCallback<EmptyResult>() {
            @Override
            public void onSuccess(EmptyResult result) {
              History.back();
            }
          });
        }
      }
    });

    loadTechList();
  }

  private void loadTechList() {
    GetUsersAction action = new GetUsersAction();
    action.setUserTypeId(1);

    biomedService.execute(action, new DefaultCallback<ListResult<UserDTO>>() {
      @Override
      public void onSuccess(ListResult<UserDTO> result) {
        techs = result.getResult();
        view.getTechField().setAcceptableValues(techs);
        loadWorkorder();
      }
    });
  }

  private void loadWorkorder() {
    GetWorkorderViewAction action = new GetWorkorderViewAction();
    action.setWorkorderId(getWorkorderId());

    biomedService.execute(action, new DefaultCallback<SingleResult<EditWorkorderViewDTO>>() {
      @Override
      public void onSuccess(SingleResult<EditWorkorderViewDTO> result) {
        EditWorkorderViewDTO data = result.getResult();
        view.setStaticValues(data);
        view.getStartTimeField().setValue(data.getWorkorderStartTime());
        view.getEndTimeField().setValue(data.getWorkorderEndTime());
        view.getWorkorderJobDateField().setValue(data.getWorkorderJobDate());

        for (UserDTO tech : techs) {
          if (tech.getId() == data.getTechId()) {
            view.getTechField().setValue(tech);
            break;
          }
        }
      }
    });
  }

  protected int getWorkorderId() {
    return ((EditWorkorderPlace) place).getId();
  }

}
