package com.example.meetingroomreservationapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor @NoArgsConstructor
@Setter @Getter
public class ReservationDTO {
    private long reservationId;
    private long UserId;
    private long roomId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String status;
}