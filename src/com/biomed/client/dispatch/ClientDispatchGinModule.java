package com.biomed.client.dispatch;

import javax.inject.Singleton;

import com.biomed.shared.dispatch.DispatchAsync;
import com.google.gwt.inject.client.AbstractGinModule;

public class ClientDispatchGinModule extends AbstractGinModule {

  @Override
  protected void configure() {
    bind(DispatchAsync.class).to(DefaultDispatchAsync.class).in(Singleton.class);
  }
}