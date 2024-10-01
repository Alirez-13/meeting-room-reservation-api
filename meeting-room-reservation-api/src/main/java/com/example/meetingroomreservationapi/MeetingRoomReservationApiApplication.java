package com.example.meetingroomreservationapi;

import com.example.meetingroomreservationapi.dto.ReservationDTO;
import com.example.meetingroomreservationapi.entity.Room;
import com.example.meetingroomreservationapi.entity.User;
import com.example.meetingroomreservationapi.service.ReservationService;
import com.example.meetingroomreservationapi.service.RoomService;
import com.example.meetingroomreservationapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@SpringBootApplication
public class MeetingRoomReservationApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeetingRoomReservationApiApplication.class, args);
    }

    @Autowired
    RoomService roomService;
    @Autowired
    UserService userService;
    @Autowired
    ReservationService reservationService;

    // define @PostContruct for create Data in H2 DB
    @PostConstruct
    public void loadData() {
        User user1 = new User(1, "Ali", "1234", "ADMIN");
        userService.saveUser(user1);

        User user2 = new User(2, "Narges", "1234", "MANAGER");
        userService.saveUser(user2);

        User user3 = new User(30, "Hanie", "1234", "USER");
        userService.saveUser(user3);

        Room room1 = new Room(1, 2, true);
        roomService.createRoom(room1);

        Room room2 = new Room(2, 4, true);
        roomService.createRoom(room2);

        Room room3 = new Room(3, 8, false);
        roomService.createRoom(room3);

        LocalDate checkIn = LocalDate.now();
        ReservationDTO reserve = new ReservationDTO(1, 1, 1,checkIn, checkIn.plusDays(4), "WAITING" );
        reservationService.CreateReservation(reserve);
    }
}
