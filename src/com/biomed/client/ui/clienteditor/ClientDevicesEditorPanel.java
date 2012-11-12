package com.biomed.client.ui.clienteditor;

import javax.inject.Inject;

import com.biomed.client.components.Pager;
import com.biomed.client.resources.CellTableResource;
import com.biomed.shared.api.dto.DeviceDTO;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.view.client.HasData;

public class ClientDevicesEditorPanel  extends Composite {

  interface Binder extends UiBinder<HTMLPanel, ClientDevicesEditorPanel> {
  }

  private static final DateTimeFormat format = DateTimeFormat.getShortDateFormat();

  @UiField(provided = true) CellTable<DeviceDTO> table;
  @UiField(provided = true) Pager pager;

  @Inject
  public ClientDevicesEditorPanel(Binder binder) {
    buildTable();
    buildPager();

    initWidget(binder.createAndBindUi(this));
  }

  public HasData<DeviceDTO> getTable() {
    return table;
  }

  private void buildTable() {
    table = new CellTable<DeviceDTO>(20, CellTableResource.INSTANCE);
    table.setWidth("100%", true);

    TextColumn<DeviceDTO> identifierColumn = new TextColumn<DeviceDTO>() {
      @Override
      public String getValue(DeviceDTO device) {
        return device.getIdentifier();
      }
    };

    TextColumn<DeviceDTO> nameColumn = new TextColumn<DeviceDTO>() {
      @Override
      public String getValue(DeviceDTO device) {
        return device.getName();
      }
    };

    TextColumn<DeviceDTO> manufacturerColumn = new TextColumn<DeviceDTO>() {
      @Override
      public String getValue(DeviceDTO device) {
        return device.getManufacturer();
      }
    };

    TextColumn<DeviceDTO> modelColumn = new TextColumn<DeviceDTO>() {
      @Override
      public String getValue(DeviceDTO device) {
        return device.getModel();
      }
    };

    TextColumn<DeviceDTO> serialColumn = new TextColumn<DeviceDTO>() {
      @Override
      public String getValue(DeviceDTO device) {
        return device.getSerial();
      }
    };

    TextColumn<DeviceDTO> statusColumn = new TextColumn<DeviceDTO>() {
      @Override
      public String getValue(DeviceDTO device) {
        return device.getStatus();
      }
    };

    TextColumn<DeviceDTO> lastPmColumn = new TextColumn<DeviceDTO>() {
      @Override
      public String getValue(DeviceDTO device) {
        if (device.getLastPm() != null) {
          return format.format(device.getLastPm());
        } else {
          return null;
        }
      }
    };

    table.addColumn(identifierColumn, "Device ID");
    table.addColumn(nameColumn, "Device");
    table.addColumn(manufacturerColumn, "Manufacturer");
    table.addColumn(modelColumn, "Model #");
    table.addColumn(serialColumn, "Serial #");
    table.addColumn(statusColumn, "Status");
    table.addColumn(lastPmColumn, "Last Pm");
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