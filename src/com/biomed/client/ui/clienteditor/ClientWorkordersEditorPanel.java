package com.biomed.client.ui.clienteditor;

import javax.inject.Inject;

import com.biomed.client.activity.EditClientWorkordersActivity;
import com.biomed.client.components.Pager;
import com.biomed.client.resources.CellTableResource;
import com.biomed.shared.api.dto.WorkorderDTO;
import com.biomed.shared.api.dto.WorkorderStatusDTO;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.view.client.CellPreviewEvent;
import com.google.gwt.view.client.CellPreviewEvent.Handler;
import com.google.gwt.view.client.HasData;

public class ClientWorkordersEditorPanel  extends Composite {

  interface Binder extends UiBinder<HTMLPanel, ClientWorkordersEditorPanel> {
  }

  private static final DateTimeFormat format = DateTimeFormat.getShortDateFormat();

  @UiField(provided = true) CellTable<WorkorderDTO> table;
  @UiField(provided = true) Pager pager;

  public EditClientWorkordersActivity activity;

  @Inject
  public ClientWorkordersEditorPanel(Binder binder) {
    buildTable();
    buildPager();

    initWidget(binder.createAndBindUi(this));

  }

  public HasData<WorkorderDTO> getTable() {
    return table;
  }

  private void buildTable() {
    table = new CellTable<WorkorderDTO>(20, CellTableResource.INSTANCE);
    table.setWidth("100%", true);

    TextColumn<WorkorderDTO> technicianColumn = new TextColumn<WorkorderDTO>() {
      @Override
      public String getValue(WorkorderDTO workorder) {
        return workorder.getTechFirstName() + " " + workorder.getTechLastName();
      }
    };

    TextColumn<WorkorderDTO> jobDateColumn = new TextColumn<WorkorderDTO>() {
      @Override
      public String getValue(WorkorderDTO workorder) {
        if (workorder.getJobDate() != null) {
          return format.format(workorder.getJobDate());
        } else {
          return null;
        }
      }
    };

    TextColumn<WorkorderDTO> remarksColumn = new TextColumn<WorkorderDTO>() {
      @Override
      public String getValue(WorkorderDTO workorder) {
        return workorder.getRemarks();
      }
    };

    TextColumn<WorkorderDTO> reasonColumn = new TextColumn<WorkorderDTO>() {
      @Override
      public String getValue(WorkorderDTO workorder) {
        return workorder.getReason();
      }
    };

    TextColumn<WorkorderDTO> jobStatusColumn = new TextColumn<WorkorderDTO>() {
      @Override
      public String getValue(WorkorderDTO workorder) {
        WorkorderStatusDTO status = WorkorderStatusDTO.getById(workorder.getJobStatusId());
        if (status != null) {
          return status.getLabel();
        } else {
          return "(Unknown)";
        }
      }
    };

    table.addColumn(technicianColumn, "Technician");
    table.addColumn(jobDateColumn, "Job Date");
    table.addColumn(remarksColumn, "Remarks");
    table.addColumn(reasonColumn, "Reason");
    table.addColumn(jobStatusColumn, "Job Status");

    table.addCellPreviewHandler(new Handler<WorkorderDTO>() {

      @Override
      public void onCellPreview(CellPreviewEvent<WorkorderDTO> event) {
        boolean isClick = "click".equals(event.getNativeEvent().getType());

        if (isClick) {
          activity.onWorkorderClicked(event.getValue().getWorkorderId());
        }
      }
    });
  }

  private void buildPager() {
    pager = new Pager();
    pager.setDisplay(table);
    pager.setPageSize(20);
  }

  public void showLoading() {
    pager.startLoading();
  }
}