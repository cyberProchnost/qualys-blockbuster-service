package com.app.blockbuster.exception;

public class MovieAlreadyPresentException extends RuntimeException {
    public MovieAlreadyPresentException(String message) {
        super(message);
    }
}
