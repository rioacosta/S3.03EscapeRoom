package com.escapeRoom.exceptions;

public class NullOrEmptyException extends RuntimeException {
    public NullOrEmptyException(String message) {
        super(message);
    }
}
