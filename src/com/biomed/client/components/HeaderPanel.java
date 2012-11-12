package com.biomed.client.components;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;

public class HeaderPanel extends Widget {
  interface Binder extends UiBinder<DivElement, HeaderPanel> {}

  private static Binder binder = GWT.create(Binder.class);

  public HeaderPanel() {
    setElement(binder.createAndBindUi(this));
  }
}
