package com.biomed.server;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class BiomedGuiceServletContextListener extends GuiceServletContextListener {

  @Override
  protected Injector getInjector() {
    return Guice.createInjector(new BiomedModule());
  }

}
