package com.biomed.server.dispatch;

import javax.inject.Singleton;

import com.google.inject.AbstractModule;
import com.google.inject.internal.UniqueAnnotations;

public abstract class ActionHandlerModule extends AbstractModule {

  @Override
  protected final void configure() {
    // This will only get installed once due to equals/hashCode override.
    install(new ServerDispatchModule());

    configureHandlers();
  }

  /**
   * Override this method to configure handlers.
   */
  protected abstract void configureHandlers();

  /**
   * Binds the specified {@link ActionHandler} instance class.
   * 
   * @param handlerClass
   */
  protected void bindHandler(Class<? extends ActionHandler<?, ?>> handlerClass) {
    bind(ActionHandler.class).annotatedWith(UniqueAnnotations.create()).to(handlerClass)
        .in(Singleton.class);

  }

}