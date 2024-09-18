package com.example.meetingroomreservationapi.errHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserErrorResponse {

    private int status;

    private String message;

    private long timeStamp;
}