package com.biomed.client.ui.messages;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.ImplementedBy;

public interface Messages {

  @ImplementedBy(MessagesPanel.class)
  public interface View extends IsWidget {

  }
}
