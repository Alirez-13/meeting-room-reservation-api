package com.example.meetingroomreservationapi.excHandler;

public class UnathorizeException extends RuntimeException {
    public UnathorizeException(String message) {
        super(message);
    }

    public UnathorizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnathorizeException(Throwable cause) {
        super(cause);
    }

}