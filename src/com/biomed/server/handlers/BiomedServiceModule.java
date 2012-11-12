package com.biomed.server.handlers;

import com.biomed.server.dispatch.ActionHandlerModule;

public class BiomedServiceModule extends ActionHandlerModule {

  @Override
  protected void configureHandlers() {
    bindHandler(GetClientHandler.class);
    bindHandler(SaveClientHandler.class);
    bindHandler(GetScheduleHandler.class);
    bindHandler(GetUsersHandler.class);
    bindHandler(GetClientsHandler.class);
    bindHandler(GetWorkordersHandler.class);
    bindHandler(GetDevicesHandler.class);
    bindHandler(GetClientFrequencyHandler.class);
    bindHandler(ScheduleHandler.class);
    bindHandler(SaveClientFrequenciesHandler.class);
    bindHandler(GetWorkorderViewHandler.class);
    bindHandler(SaveWorkorderHandler.class);
    bindHandler(SendMessageHandler.class);
    bindHandler(SendTechScheduleEmailHandler.class);
    bindHandler(DeleteWorkorderHandler.class);
  }
}
