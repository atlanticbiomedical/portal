package com.biomed.server.dispatch;

import com.biomed.shared.dispatch.DispatchAction;
import com.biomed.shared.dispatch.ActionException;
import com.biomed.shared.dispatch.DispatchResult;

public interface Dispatch {
  <A extends DispatchAction<R>, R extends DispatchResult> R execute(A action) throws ActionException;
}
