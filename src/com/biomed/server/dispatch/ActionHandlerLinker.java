package com.biomed.server.dispatch;

import java.util.List;

import javax.inject.Inject;

import com.google.inject.Binding;
import com.google.inject.Injector;
import com.google.inject.TypeLiteral;

public final class ActionHandlerLinker {

  private ActionHandlerLinker() {
  }

  @Inject
  @SuppressWarnings("rawtypes")
  public static void linkHandlers(Injector injector, ActionHandlerRegistry actionHandlerRegistry) {
    List<Binding<ActionHandler>> bindings = injector.findBindingsByType(TypeLiteral
        .get(ActionHandler.class));

    for (Binding<ActionHandler> binding : bindings) {
      actionHandlerRegistry.addHandler(binding.getProvider().get());
    }
  }
}