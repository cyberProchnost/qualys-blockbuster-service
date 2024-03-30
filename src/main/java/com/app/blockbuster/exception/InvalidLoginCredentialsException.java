package com.app.blockbuster.exception;

public class InvalidLoginCredentialsException extends RuntimeException {
    public InvalidLoginCredentialsException(String message) {
        super(message);
    }
}
