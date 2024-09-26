package com.example.meetingroomreservationapi.controller;

import com.example.meetingroomreservationapi.dto.ReservationDTO;
import com.example.meetingroomreservationapi.entity.Reservation;
import com.example.meetingroomreservationapi.entity.Room;
import com.example.meetingroomreservationapi.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Get all reservations", description = "Retrieve a list of all reservations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of reservations",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Reservation.class))),
            @ApiResponse(responseCode = "404", description = "No reservations found")
    })
    public ResponseEntity<List<ReservationDTO>> getAllReservations() {
        return new ResponseEntity<>(reservationService.getAllReservation(), HttpStatus.OK);
    }

    // for users to see which room is available
    @GetMapping("/available")
    @Operation(summary = "Get available rooms", description = "Retrieve a list of available rooms for reservation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of available rooms",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Room.class))),
            @ApiResponse(responseCode = "404", description = "No available rooms found")
    })
    public ResponseEntity<List<Room>> getAvailableRooms() {
        return new ResponseEntity<>(reservationService.getAvailableRoom(), HttpStatus.OK);
    }

    // Request a reservation for users
    @PostMapping()
    @Operation(summary = "Create a reservation", description = "Create a new reservation for a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created reservation",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Reservation.class))),
            @ApiResponse(responseCode = "400", description = "Invalid reservation request")
    })
    public ResponseEntity<Reservation> createReservation(@RequestBody ReservationDTO reservation){
        return new ResponseEntity<>(reservationService.CreateReservation(reservation), HttpStatus.OK);
    }

    @GetMapping("/waiting-list")
    @Operation(summary = "Get waiting list of reservations", description = "Retrieve a list of reservations that are on the waiting list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved waiting list of reservations",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Reservation.class))),
            @ApiResponse(responseCode = "404", description = "No reservations found in the waiting list")
    })
    public ResponseEntity<List<Reservation>> waitingReserveRequest() {
        return new ResponseEntity<>(reservationService.waitingList(), HttpStatus.OK);
    }

    @PostMapping("/waiting-list/{reservationId}")
    @Operation(summary = "Update reservation status", description = "Update the status of a reservation by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated reservation status",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Reservation.class))),
            @ApiResponse(responseCode = "400", description = "Invalid reservation ID or status"),
            @ApiResponse(responseCode = "404", description = "Reservation not found")
    })
    public ResponseEntity<Optional<Reservation>> updateReserveStatus(@PathVariable Long reservationId, @RequestBody String updatedStatus) {
        return new ResponseEntity<>(reservationService.updateReservationStatus(reservationId, updatedStatus), HttpStatus.OK);
    }
}