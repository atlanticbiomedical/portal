package com.biomed.server.dispatch;

import com.biomed.shared.dispatch.DispatchAction;
import com.biomed.shared.dispatch.DispatchResult;

public interface ActionHandler<A extends DispatchAction<R>, R extends DispatchResult> {

  Class<A> getType();

  R execute(A action, ExecutionContext context) throws Exception;
}