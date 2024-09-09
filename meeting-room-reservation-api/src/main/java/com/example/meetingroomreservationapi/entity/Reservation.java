package com.example.meetingroomreservationapi.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reservationId;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "room_id")
    private int roomId;

    @Column(name = "check_in_date")
    private Date checkInDate;

    @Column(name = "check_out_date")
    private Date checkOutDate;

    @Column(name = "status")
    private String status;


}
