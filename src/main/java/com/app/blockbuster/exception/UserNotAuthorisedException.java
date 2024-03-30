package com.app.blockbuster.exception;

public class UserNotAuthorisedException extends RuntimeException {
    public UserNotAuthorisedException(String message) {
        super(message);
    }
}
