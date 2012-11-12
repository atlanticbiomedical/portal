package com.biomed.client.ui.messages;

import com.google.gwt.text.shared.AbstractRenderer;

public enum MessageFor {
  ChrisEndres(10, "Chris Endres"),
  RyanFoley(32, "Ryan Foley"),
  AllisonHartlove(51, "Allison Hartlove"),
  CarlEndres(11, "Carl Endres"),
  WillTurner(54, "Will Turner"),
  KelliKirk(59, "Kelli Kirk");

  private final int id;
  private final String label;

  MessageFor(int id, String label) {
    this.id = id;
    this.label = label;
  }

  public int getId() {
    return id;
  }

  public String getLabel() {
    return label;
  }

  public static class Renderer extends AbstractRenderer<MessageFor> {
    @Override
    public String render(MessageFor object) {
      if (object == null) {
        return "";
      } else {
        return object.getLabel();
      }
    }
  }
}
