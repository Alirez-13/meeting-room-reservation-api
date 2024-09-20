package com.example.meetingroomreservationapi.excHandler;

public class RoomOccupiedException extends RuntimeException{
    public RoomOccupiedException(String message) {
        super(message);
    }

    public RoomOccupiedException(String message, Throwable cause) {
        super(message, cause);
    }

    public RoomOccupiedException(Throwable cause) {
        super(cause);
    }
}