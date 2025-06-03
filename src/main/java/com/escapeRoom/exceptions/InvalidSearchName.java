package com.escapeRoom.exceptions;

public class InvalidSearchName extends RuntimeException {
    public InvalidSearchName(String message) {
        super(message);
    }
}
