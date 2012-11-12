package com.biomed.client.ui.clientlist;

import javax.inject.Inject;

import com.biomed.client.activity.ViewClientsActivity;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

public class ClientListSidebarPanel extends Composite {

  interface Binder extends UiBinder<HTMLPanel, ClientListSidebarPanel> {}

  public ViewClientsActivity activity;

  @UiField Anchor addClient;
  @UiField Anchor search;
  @UiField TextBox searchBox;

  @Inject
  public ClientListSidebarPanel(Binder binder) {
    initWidget(binder.createAndBindUi(this));

    searchBox.addKeyPressHandler(new KeyPressHandler() {
      @Override
      public void onKeyPress(KeyPressEvent event) {
        if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
          activity.filterTable(searchBox.getText());
        }
      }
    });
  }

  @UiHandler("addClient")
  void onAddClientClicked(ClickEvent event) {
    activity.onNewClientClicked();
  }

  @UiHandler("search")
  void onSearchClicked(ClickEvent event) {
    activity.filterTable(searchBox.getText());
  }
}
