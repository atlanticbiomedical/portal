package com.biomed.client.ui.workorders;


public interface Workorders {
  public interface Presenter {
    void onWorkorderClicked();
    void filterTable(String filter);
  }

}
