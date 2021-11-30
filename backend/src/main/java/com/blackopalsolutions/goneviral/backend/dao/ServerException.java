package com.blackopalsolutions.goneviral.backend.dao;

public class ServerException extends Exception {
    public ServerException() {
        super("Bad Request.");
    }

    public ServerException(String message) {
        super("Bad Request. " + message);
    }
}
