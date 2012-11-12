package com.biomed.client.components;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.cellview.client.AbstractPager;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.view.client.HasRows;
import com.google.gwt.view.client.Range;

public class Pager extends AbstractPager {

  private final Label label;
  private final Image loading;

  private final Anchor firstPage;
  private final Anchor prevPage;
  private final Anchor nextPage;
  private final Anchor lastPage;

  public Pager() {

    label = new Label();
    label.setStyleName("dataTables_info");

    loading = new Image();
    loading.setStyleName("loading");
    loading.setUrl("resources/images/elements/loaders/9.gif");
    loading.setVisible(false);

    firstPage = new Anchor("First");
    firstPage.setStyleName("first paginate_button");
    firstPage.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        firstPage();
      }
    });

    prevPage = new Anchor("Previous");
    prevPage.setStyleName("previous paginate_button");
    prevPage.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        previousPage();
      }
    });

    nextPage = new Anchor("Next");
    nextPage.setStyleName("next paginate_button");
    nextPage.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        nextPage();
      }
    });

    lastPage = new Anchor("Last");
    lastPage.setStyleName("last paginate_button");
    lastPage.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        lastPage();
      }
    });

    FlowPanel buttonsPanel = new FlowPanel();
    buttonsPanel.setStyleName("dataTables_paginate paging_full_numbers");
    buttonsPanel.add(firstPage);
    buttonsPanel.add(prevPage);
    buttonsPanel.add(nextPage);
    buttonsPanel.add(lastPage);

    FlowPanel layout = new FlowPanel();
    layout.setStyleName("tableFooter");

    layout.add(loading);
    layout.add(label);
    layout.add(buttonsPanel);

    initWidget(layout);

    setDisplay(null);
  }

  @Override
  public void setDisplay(HasRows display) {
    boolean disableButtons = (display == null);
    setNextPageButtonsDisabled(disableButtons);
    setPrevPageButtonsDisabled(disableButtons);

    super.setDisplay(display);
  }

  public void startLoading() {
    getDisplay().setRowCount(0, true);
    loading.setVisible(true);
    label.setText("Loading...");
  }

  protected String createText() {
    // Default text is 1 based.
    NumberFormat formatter = NumberFormat.getFormat("#,###");
    HasRows display = getDisplay();
    Range range = display.getVisibleRange();
    int pageStart = range.getStart() + 1;
    int pageSize = range.getLength();
    int dataSize = display.getRowCount();
    int endIndex = Math.min(dataSize, pageStart + pageSize - 1);
    endIndex = Math.max(pageStart, endIndex);
    boolean exact = display.isRowCountExact();
    return "Showing " + formatter.format(pageStart) + " to " + formatter.format(endIndex)
        + (exact ? " of " : " of over ") + formatter.format(dataSize) + " entries";
  }

  @Override
  protected void onRangeOrRowCountChanged() {
    HasRows display = getDisplay();
    label.setText(createText());
    loading.setVisible(false);

    // Update the prev and first buttons.
    setPrevPageButtonsDisabled(!hasPreviousPage());

    // Update the next and last buttons.
    if (isRangeLimited() || !display.isRowCountExact()) {
      setNextPageButtonsDisabled(!hasNextPage());
//      setFastForwardDisabled(!hasNextPages(getFastForwardPages()));
    }
  }

  /**
   * Enable or disable the next page buttons.
   *
   * @param disabled true to disable, false to enable
   */
  private void setNextPageButtonsDisabled(boolean disabled) {
    if (disabled) {
      lastPage.addStyleName("paginate_button_disabled");
      nextPage.addStyleName("paginate_button_disabled");
    } else {
      lastPage.removeStyleName("paginate_button_disabled");
      nextPage.removeStyleName("paginate_button_disabled");
    }

    lastPage.setEnabled(!disabled);
    nextPage.setEnabled(!disabled);
  }

  /**
   * Enable or disable the previous page buttons.
   *
   * @param disabled true to disable, false to enable
   */
  private void setPrevPageButtonsDisabled(boolean disabled) {
    if (disabled) {
      firstPage.addStyleName("paginate_button_disabled");
      prevPage.addStyleName("paginate_button_disabled");
    } else {
      firstPage.removeStyleName("paginate_button_disabled");
      prevPage.removeStyleName("paginate_button_disabled");
    }

    firstPage.setEnabled(!disabled);
    prevPage.setEnabled(!disabled);
  }


  @Override
  public void firstPage() {
    super.firstPage();
  }

  @Override
  public int getPage() {
    return super.getPage();
  }

  @Override
  public int getPageCount() {
    return super.getPageCount();
  }

  @Override
  public boolean hasNextPage() {
    return super.hasNextPage();
  }

  @Override
  public boolean hasNextPages(int pages) {
    return super.hasNextPages(pages);
  }

  @Override
  public boolean hasPage(int index) {
    return super.hasPage(index);
  }

  @Override
  public boolean hasPreviousPage() {
    return super.hasPreviousPage();
  }

  @Override
  public boolean hasPreviousPages(int pages) {
    return super.hasPreviousPages(pages);
  }

  @Override
  public void lastPage() {
    super.lastPage();
  }

  @Override
  public void lastPageStart() {
    super.lastPageStart();
  }

  @Override
  public void nextPage() {
    super.nextPage();
  }

  @Override
  public void previousPage() {
    super.previousPage();
  }


  @Override
  public void setPage(int index) {
    super.setPage(index);
  }

  @Override
  public void setPageSize(int pageSize) {
    super.setPageSize(pageSize);
  }

  @Override
  public void setPageStart(int index) {
    super.setPageStart(index);
  }
}
