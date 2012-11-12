package com.biomed.shared.dispatch;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("rpc")
public interface DispatchService extends RemoteService {
    DispatchResult execute( DispatchAction<?> action ) throws Exception;
}