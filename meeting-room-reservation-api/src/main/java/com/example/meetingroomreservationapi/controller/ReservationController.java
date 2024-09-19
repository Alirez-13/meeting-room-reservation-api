package com.example.meetingroomreservationapi.controller;

import com.example.meetingroomreservationapi.entity.Reservation;
import com.example.meetingroomreservationapi.entity.Room;
import com.example.meetingroomreservationapi.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reserve")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @GetMapping()
    public ResponseEntity<List<Reservation>> getAllReservations() {
        return new ResponseEntity<>(reservationService.getAllReservation(), HttpStatus.OK);
    }

    // for users to see which room is available
    @GetMapping("/available")
    public ResponseEntity<List<Room>> getAvailableRooms() {
        return new ResponseEntity<>(reservationService.getAvailableRoom(), HttpStatus.OK);
    }

    // Request a reservation for users
    @PostMapping()
    public ResponseEntity<Reservation> createReservation(Reservation reservation) {
        return new ResponseEntity<>(reservationService.CreateReservation(reservation), HttpStatus.OK);
    }

    @GetMapping("/waiting-list")
    public ResponseEntity<List<Reservation>> waitingReserveRequest() {
        return new ResponseEntity<>(reservationService.waitingList(), HttpStatus.OK);
    }

    @PostMapping("/waiting-list/{reservationId}")
    public ResponseEntity<Optional<Reservation>> updateReserveStatus(@PathVariable Long reservationId, @RequestBody String updatedStatus) {
        return new ResponseEntity<>(reservationService.updateReservationStatus(reservationId, updatedStatus), HttpStatus.OK);
    }
}
