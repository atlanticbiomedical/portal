package com.biomed.client.services;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.biomed.client.ui.MainPanel;
import com.biomed.shared.api.GetScheduleAction;
import com.biomed.shared.api.GetTechListAction;
import com.biomed.shared.api.SqlResultSet;
import com.biomed.shared.dispatch.DispatchAction;
import com.biomed.shared.dispatch.DispatchAsync;
import com.biomed.shared.dispatch.DispatchResult;
import com.google.gwt.user.client.rpc.AsyncCallback;

@Singleton
public class BiomedService {

  private static final String LOADING = "Loading...";
  private static final String SAVING = "Saving...";

  private final MainPanel mainPanel;
  private final DispatchAsync dispatcher;

  @Inject
  public BiomedService(MainPanel mainPanel, DispatchAsync dispatcher) {
    this.mainPanel = mainPanel;
    this.dispatcher = dispatcher;
  }

  public <A extends DispatchAction<R>, R extends DispatchResult> void execute(A action,
      AsyncCallback<R> callback) {
    dispatcher.execute(action, createCallback(LOADING, callback));
  }

  public void getSchedule(String startDate, String endDate, int techId,
      AsyncCallback<SqlResultSet> callback) {
    GetScheduleAction action = new GetScheduleAction();
    action.startDate = startDate;
    action.endDate = endDate;
    action.techId = techId;

    dispatcher.execute(action, createCallback(LOADING, callback));
  }

  public void getTechList(AsyncCallback<SqlResultSet> callback) {
    GetTechListAction action = new GetTechListAction();
    dispatcher.execute(action, createCallback(LOADING, callback));
  }

  private <T extends DispatchResult> AsyncCallback<T> createCallback(final String message,
      final AsyncCallback<T> callback) {

    if (message != null) {
      mainPanel.showMessage(message);
    }

    return new AsyncCallback<T>() {

      @Override
      public void onFailure(Throwable caught) {
        callback.onFailure(caught);
      }

      @Override
      public void onSuccess(T result) {
        callback.onSuccess(result);
        mainPanel.hideMessage();
      }
    };
  }

}
