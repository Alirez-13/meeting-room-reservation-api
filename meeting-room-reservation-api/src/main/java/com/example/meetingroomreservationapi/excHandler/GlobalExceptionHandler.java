package com.example.meetingroomreservationapi.excHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<UserErrorResponse> handleUserNotFoundException(NotFoundException ex) {
        UserErrorResponse err = new UserErrorResponse();

        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setMessage(ex.getMessage());
        err.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> handleUserGenericException(Exception ex) {
        UserErrorResponse err = new UserErrorResponse();

        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setMessage(ex.getMessage());
        err.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnathorizeException.class)
    public ResponseEntity<UserErrorResponse> unauthorizedUser(Exception ex) {
        UserErrorResponse err = new UserErrorResponse();

        err.setStatus(HttpStatus.UNAUTHORIZED.value());
        err.setMessage(ex.getMessage());
        err.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(err, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(RoomOccupiedException.class)
    public ResponseEntity<UserErrorResponse> occupiedRoom(Exception ex){
        UserErrorResponse err = new UserErrorResponse();

        err.setStatus(HttpStatus.CONFLICT.value());
        err.setMessage(ex.getMessage());
        err.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(err, HttpStatus.CONFLICT);
    }
}