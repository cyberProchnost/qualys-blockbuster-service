package com.app.blockbuster.exception;

public class UserAlreadyPresentException extends RuntimeException {
    public UserAlreadyPresentException(String message) {
        super(message);
    }
}
