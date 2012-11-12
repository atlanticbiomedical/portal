package com.biomed.resources;

import com.github.gwtbootstrap.client.ui.config.Configurator;
import com.github.gwtbootstrap.client.ui.resources.Resources;
import com.google.gwt.core.client.GWT;

public class BiomedConfigurator implements Configurator {

  @Override
  public Resources getResources() {
    return GWT.create(BiomedResources.class);
  }

  @Override
  public boolean hasResponsiveDesign() {
    return true;
  }

}
