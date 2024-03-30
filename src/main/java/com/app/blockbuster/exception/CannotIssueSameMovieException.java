package com.app.blockbuster.exception;

public class CannotIssueSameMovieException extends RuntimeException {
    public CannotIssueSameMovieException(String message) {
        super(message);
    }
}
