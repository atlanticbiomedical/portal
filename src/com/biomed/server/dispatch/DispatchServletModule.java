package com.biomed.server.dispatch;

import com.google.inject.servlet.ServletModule;

public class DispatchServletModule extends ServletModule {

  @Override
  protected void configureServlets() {
    serve("/biomed/rpc").with(DispatchServiceServlet.class);
  }
}
