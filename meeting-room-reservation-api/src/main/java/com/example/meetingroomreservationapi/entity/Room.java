package com.example.meetingroomreservationapi.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
@Entity
@Table(name = "Rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long roomId;

    @Column(name = "room_capacity")
    private int roomCapacity;

    @Column(name = "is_empty")
    private boolean isEmpty = true;
}
