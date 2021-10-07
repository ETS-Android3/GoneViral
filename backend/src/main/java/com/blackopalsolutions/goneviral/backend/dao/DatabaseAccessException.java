package com.blackopalsolutions.goneviral.backend.dao;

public class DatabaseAccessException extends Exception {
    public DatabaseAccessException() {
        super("Error.");
    }

    public DatabaseAccessException(String message) {
        super("Error. " + message);
    }
}
