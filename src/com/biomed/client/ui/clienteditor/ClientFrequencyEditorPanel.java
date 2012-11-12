package com.biomed.client.ui.clienteditor;

import javax.inject.Inject;

import com.biomed.shared.api.dto.FrequencyDTO;
import com.biomed.shared.api.dto.FrequencyType;
import com.biomed.shared.api.dto.Month;
import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.constants.ButtonType;
import com.google.gwt.dom.builder.shared.HtmlBuilderFactory;
import com.google.gwt.dom.builder.shared.TableBuilder;
import com.google.gwt.dom.builder.shared.TableRowBuilder;
import com.google.gwt.dom.builder.shared.TableSectionBuilder;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

public class ClientFrequencyEditorPanel extends Composite {

  interface Binder extends UiBinder<HTMLPanel, ClientFrequencyEditorPanel> {
  }

  @UiField
  HTMLPanel rootPanel;

  Button saveButton;

  FrequencyDTO frequencies;

  @Inject
  public ClientFrequencyEditorPanel(Binder binder) {
    saveButton = new Button("Save");
    saveButton.setType(ButtonType.PRIMARY);
    initWidget(binder.createAndBindUi(this));
  }

  public void setFrequencies(FrequencyDTO frequencies) {
    this.frequencies = frequencies;

    buildTable();
  }

  public FrequencyDTO getFrequencies() {
    return frequencies;
  }

  public HasClickHandlers getSaveButton() {
    return saveButton;
  }

  private void buildTable() {

    HtmlBuilderFactory builder = HtmlBuilderFactory.get();

    TableBuilder table = builder.createTableBuilder();
    table.className("table table-bordered frequency");
    TableSectionBuilder thead = table.startTHead();
    TableRowBuilder theadRow = thead.startTR();
    theadRow.startTH().end();
    theadRow.startTH().text("JAN").end();
    theadRow.startTH().text("FEB").end();
    theadRow.startTH().text("MAR").end();
    theadRow.startTH().text("APR").end();
    theadRow.startTH().text("MAY").end();
    theadRow.startTH().text("JUN").end();
    theadRow.startTH().text("JUL").end();
    theadRow.startTH().text("AUG").end();
    theadRow.startTH().text("SEP").end();
    theadRow.startTH().text("OCT").end();
    theadRow.startTH().text("NOV").end();
    theadRow.startTH().text("DEC").end();

    theadRow.end();
    thead.end();

    TableSectionBuilder tbody = table.startTBody();
    for (FrequencyType type : FrequencyType.values()) {
      TableRowBuilder tr = tbody.startTR();

      tr.startTD().className("name").text(type.getLabel()).end();

      for (Month month : Month.values()) {
        tr.startTD()
            .className("item " + (frequencies.getFrequency(type, month) ? "enabled" : "disabled"))
            .attribute("data-month", month.toString()).attribute("data-frequency", type.toString())
            .html(SafeHtmlUtils.fromSafeConstant("<i class=\"icon\" />")).end();
      }
      tr.end();
    }

    TableRowBuilder tr = tbody.startTR();
    tr.startTD().colSpan(13).className("controls").id("controls").end();
    tr.end();

    tbody.end();
    table.end();
    rootPanel.getElement().appendChild(table.finish());
    rootPanel.add(saveButton, "controls");

    NodeList<Element> elements = rootPanel.getElement().getElementsByTagName("td");
    for (int i = 0; i < elements.getLength(); i++) {
      final com.google.gwt.user.client.Element cell = elements.getItem(i).cast();
      if (cell.getClassName().contains("item")) {
        DOM.setEventListener(cell, new EventListener() {
          @Override
          public void onBrowserEvent(Event event) {
            Element target = event.getCurrentTarget();
            FrequencyType type = FrequencyType.valueOf(target.getAttribute("data-frequency"));
            Month month = Month.valueOf(target.getAttribute("data-month"));
            boolean enabled = "enabled".equals(target.getClassName());
            frequencies.setFrequency(type, month, !enabled);
            target.setClassName(enabled ? "disabled" : "enabled");
          }
        });
        DOM.sinkEvents(cell, Event.ONCLICK);
      }
    }
  }

}
