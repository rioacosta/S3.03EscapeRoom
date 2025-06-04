package com.escapeRoom.exceptions;

public class InvalidRoomNameException extends RuntimeException {
    public InvalidRoomNameException(String message) {
        super(message);
    }
}
