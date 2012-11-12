package com.biomed.resources;

import com.github.gwtbootstrap.client.ui.resources.Resources;
import com.google.gwt.resources.client.TextResource;

public interface BiomedResources extends Resources {
  @Source("js/bootstrap.min.js")
  TextResource bootstrapJs();

  @Source("css/bootstrap.min.css")
  TextResource bootstrapCss();

  @Source("css/bootstrap-responsive.css")
  TextResource bootstrapResponsiveCss();

  @Source("css/gwt-bootstrap.css")
  TextResource gwtBootstrapCss();
}
