package com.biomed.shared.api;

import com.biomed.shared.api.dto.ClientDTO;
import com.biomed.shared.dispatch.DispatchAction;

public class GetClientsAction implements DispatchAction<ListResult<ClientDTO>> {

  public enum View {
    CLIENT_PREVIEW,
    NAMES_ONLY,
  }

  private View view;
  private String filter;

  public View getView() {
    return view;
  }

  public void setView(View view) {
    this.view = view;
  }

  public String getFilter() {
    return filter;
  }

  public void setFilter(String filter) {
    this.filter = filter;
  }
}
