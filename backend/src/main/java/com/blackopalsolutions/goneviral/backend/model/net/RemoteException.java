package com.blackopalsolutions.goneviral.backend.model.net;

import java.util.List;

public class RemoteException extends Exception {

    private final String remoteExceptionType;
    private final List<String> remoteStackTrace;

    public RemoteException(String message, String remoteExceptionType, List<String> remoteStackTrace) {
        super(message);
        this.remoteExceptionType = remoteExceptionType;
        this.remoteStackTrace = remoteStackTrace;
    }

    public String getRemoteExceptionType() {
        return remoteExceptionType;
    }

    public List<String> getRemoteStackTrace() {
        return remoteStackTrace;
    }
}
