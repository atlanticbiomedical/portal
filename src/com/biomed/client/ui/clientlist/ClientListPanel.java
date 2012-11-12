package com.biomed.client.ui.clientlist;

import javax.inject.Inject;

import com.biomed.client.activity.ViewClientsActivity;
import com.biomed.client.components.Pager;
import com.biomed.client.resources.CellTableResource;
import com.biomed.shared.api.dto.ClientDTO;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.view.client.CellPreviewEvent;
import com.google.gwt.view.client.CellPreviewEvent.Handler;
import com.google.gwt.view.client.HasData;

public class ClientListPanel extends Composite {

  interface Binder extends UiBinder<HTMLPanel, ClientListPanel> {
  }

  @UiField(provided = true) CellTable<ClientDTO> table;
  @UiField(provided = true) Pager pager;

  public ViewClientsActivity activity;

  @Inject
  public ClientListPanel(Binder binder) {
    buildTable();
    buildPager();

    initWidget(binder.createAndBindUi(this));
  }

  public HasData<ClientDTO> getTable() {
    return table;
  }

  private void buildTable() {
    table = new CellTable<ClientDTO>(20, CellTableResource.INSTANCE);
    table.setWidth("100%", true);

    TextColumn<ClientDTO> idColumn = new TextColumn<ClientDTO>() {
      @Override
      public String getValue(ClientDTO client) {
        return client.getIdentifier();
      }
    };
    idColumn.setSortable(true);
    table.addColumn(idColumn, "ID");
    table.setColumnWidth(idColumn, 30.0, Unit.PCT);

    TextColumn<ClientDTO> nameColumn = new TextColumn<ClientDTO>() {
      @Override
      public String getValue(ClientDTO client) {
        return client.getName();
      }
    };
    nameColumn.setSortable(true);
    table.addColumn(nameColumn, "Client Name");
    table.setColumnWidth(nameColumn, 30.0, Unit.PCT);


    TextColumn<ClientDTO> contactColumn = new TextColumn<ClientDTO>() {
      @Override
      public String getValue(ClientDTO client) {
        return client.getPrimaryContactName();
      }
    };
    contactColumn.setSortable(true);
    table.addColumn(contactColumn, "Contact");
    table.setColumnWidth(contactColumn, 30.0, Unit.PCT);

    TextColumn<ClientDTO> phoneColumn = new TextColumn<ClientDTO>() {
      @Override
      public String getValue(ClientDTO client) {
        return client.getPrimaryPhone();
      }
    };
    phoneColumn.setSortable(true);
    table.addColumn(phoneColumn, "Phone");
    table.setColumnWidth(phoneColumn, 15.0, Unit.PCT);

    table.addCellPreviewHandler(new Handler<ClientDTO>() {

      @Override
      public void onCellPreview(CellPreviewEvent<ClientDTO> event) {
        boolean isClick = "click".equals(event.getNativeEvent().getType());

        if (isClick) {
          activity.onClientClicked(event.getValue().getId());
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
