# Meeting Room Reservation API
## Table of Contents
1. [Project Overview](#Project-Overview)
2. [Features](#Features)
3. [Technologies Used](#Technologies-Used)
4. [File and Folder Structure](#File-and-Folder-Structure)
5. [Getting Started](#Getting-Started)
6. [Installation](#Installation)
7. [API Documentation](#API-Documentation)
8. [Usage](#Usage)
9. [Error Handling](#Error-Handling)
10. [Logging](#Logging)
11. [Resources and References](#Resources-and-References)
## Project Overview
The Meeting Room Reservation API is a RESTful service that allows users to manage meeting room reservations, including operations like adding, viewing, updating, and deleting rooms and reservations. The API also includes user management and room availability features. This project is built using Spring Boot and provides efficient meeting room management for businesses.
## Features
+ Room Management: Create, update, view, and delete meeting rooms.
- Reservation Management: Reserve rooms for specific dates and times, view reservation details, and cancel reservations.
+ User Management: Manage users with roles, including admin and regular users.
- Availability Check: Retrieve all available (empty) rooms.
+ Error Handling: Global error handling for common exceptions such as resource not found or unauthorized access.
## Technologies Used
+ Java upper 8
- Spring Boot 2.7 (Spring Data JPA, Spring Web, Spring HATEOAS)
+ H2 Database (in-memory for development)
- Maven (for dependency management)
+ Lombok (to reduce boilerplate code)
- Swagger/OpenAPI (for API documentation)
+ Logback (for logging)
- Postman (for unit testing)
## File and Folder Structure
----
```
meeting-room-reservation-api/
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── meetingroomreservationapi
│   │   │               ├── controller
│   │   │               │   ├── RoomController.java
│   │   │               │   ├── ReservationController.java
│   │   │               │   └── UserController.java
│   │   │               ├── entity
│   │   │               │   ├── Room.java
│   │   │               │   ├── Reservation.java
│   │   │               │   └── User.java
│   │   │               ├── repository
│   │   │               │   ├── RoomRepository.java
│   │   │               │   ├── ReservationRepository.java
│   │   │               │   └── UserRepository.java
│   │   │               ├── service
│   │   │               │   ├── RoomService.java
│   │   │               │   ├── ReservationService.java
│   │   │               │   └── UserService.java
│   │   │               ├── errHandler
│   │   │               │   ├── GlobalExceptionHandler.java
│   │   │               │   ├── NotFoundException.java
│   │   │               │   └── UnathorizeException.java
│   │   │               ├── config
│   │   │               │   └── SwaggerConfig.java
│   │   │               └── MeetingRoomReservationApiApplication.java
│   │   └── resources
│   │       ├── application.properties
│   │       ├── data.sql
│   │       └── schema.sql
│   └── test
│       ├── java
│       │   └── com
│       │       └── example
│       │           └── meetingroomreservationapi
│       │               ├── RoomServiceTest.java
│       │               ├── ReservationServiceTest.java
│       │               └── UserServiceTest.java
│       └── resources
│           └── application-test.properties
├── pom.xml
├── README.md
└── app.log
```
## Getting Started
### Prerequisites
+ Java 8 or later
- Maven 2.7
+ A code editor (e.g., IntelliJ IDEA, Eclipse)
### Installation
1. Clone the repository:    
`git clone https://github.com/Alirez-13/meeting-room-reservation-api.git`    
`cd meeting-room-reservation-api`
2. Build the project:    
`mvn clean install`
3. Run the project:    
`mvn spring-boot:run`
4. Access the API documentation at:    
`http://localhost:8080/swagger-ui.html`
## API Documentation
The API is documented using Swagger. You can view the interactive API documentation by navigating to     `http://localhost:8080/swagger-ui.html`.
### Sample Endpoints
1. Rooms:    
Get all rooms: `GET /api/rooms`.    
Create a new room: `POST /api/rooms`.    
Update room details: `PUT /api/rooms/{id}`     
Delete a room: `DELETE /api/rooms/{id}`    
2. Reservations:    
Get all reservations: `GET /api/reservations`    
Create a new reservation: `POST /api/reservations`    
Cancel a reservation: `DELETE /api/reservations/{id}`    
3. Users:    
Get all users: `GET /api/users`    
Create a new user: `POST /api/users`    
Update user details: `PUT /api/users/{id}`
## Usage
1. Creating a Room:
```
POST /api/rooms
Body:
{
  "roomCapacity": 10,
  "isEmpty": true
}
```
2. Reserving a Room:
```
POST /api/reservations
Body:
{
  "userId": 1,
  "roomId": 2,
  "checkInDate": "2024-09-20",
  "checkOutDate": "2024-09-21",
  "status": "ACCEPTED"
}
```
3. Viewing Available Rooms:    
```
GET /api/rooms/available
```
## Error Handling
This API provides meaningful error messages and appropriate HTTP status codes for different scenarios, including:
+ 404 Not Found: When a room, reservation, or user is not found.
- 400 Bad Request: When the request data is invalid.
+ 401 Unauthorized: When a user tries to access resources without proper authentication.
#### Error responses are formatted as follows:
```java
{
  "status": 404,
  "message": "Resource not found",
  "timeStamp": 1695000000000
}
```
## Logging
Logging is enabled with Logback. Logs are written to the `app.log` file with the following configurations:    
+ INFO level for general application logging.    
- DEBUG level for detailed logging of the package `com.example.meetingroomreservationapi`.    
Log configuration can be customized in the `application.properties` file.
## Resources and References
All related study materials, documentation, and resources are available in the [shared folder](https://podspace.ir/public/folders/QX5JZ5UDDK636KM1) on the public space (Padaspace).











