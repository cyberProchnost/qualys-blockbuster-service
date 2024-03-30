package com.app.blockbuster.exception;

public class InventoryUnavailableException extends RuntimeException {
    public InventoryUnavailableException(String message) {
        super(message);
    }
}
