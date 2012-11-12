package com.biomed.client.ui;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.biomed.client.BiomedActivityManager;
import com.biomed.client.BiomedActivityMapper;
import com.biomed.client.BiomedSite;
import com.biomed.client.components.NavigationPanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.UIObject;
import com.google.web.bindery.event.shared.EventBus;

@Singleton
public class MainPanel extends Composite {

  interface Binder extends UiBinder<HTMLPanel, MainPanel> {
  }

  private Logger logger = Logger.getLogger("");

  @UiField(provided = true)
  NavigationPanel navigationPanel;

  @UiField
  SimplePanel sidebar;
  @UiField
  SimplePanel content;
  @UiField
  DivElement sidebarContainer;

  @UiField
  DivElement loadingPanel;
  @UiField
  SpanElement message;

  @UiField
  DivElement errorPanel;

  @Inject
  public MainPanel(NavigationPanel navigationPanel, BiomedActivityMapper mapper, EventBus eventBus,
      Binder binder) {

    this.navigationPanel = navigationPanel;
    initWidget(binder.createAndBindUi(this));

    UIObject.setVisible(loadingPanel, false);
    UIObject.setVisible(errorPanel, false);

    BiomedActivityManager activityManager = new BiomedActivityManager(mapper, eventBus);
    activityManager.setSiteDisplay(BiomedSite.CONTENT, content);
    activityManager.setSiteDisplay(BiomedSite.SIDEBAR, new SidebarPanel());

    GWT.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {

      @Override
      public void onUncaughtException(Throwable e) {
        hideMessage();
        UIObject.setVisible(errorPanel, true);
        logger.log(Level.SEVERE, "Unhandled Exception: " + e.getMessage(), e);
      }
    });
  }

  private class SidebarPanel implements AcceptsOneWidget {
    @Override
    public void setWidget(IsWidget w) {
      boolean hasSidebar = w != null;

      sidebar.setWidget(w);
      UIObject.setVisible(sidebarContainer, hasSidebar);
      Document.get().getDocumentElement().setClassName(hasSidebar ? "wide" : "thin");
    }
  }

  public void beginSave() {
  }

  public void endSave() {
  }

  public void beginLoading() {
    showMessage("Loading...");
  }

  public void endLoading() {
    hideMessage();
  }

  public void showMessage(String message) {
    this.message.setInnerText(message);
    UIObject.setVisible(loadingPanel, true);
  }

  public void hideMessage() {
    UIObject.setVisible(loadingPanel, false);
  }
}
