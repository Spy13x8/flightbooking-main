
# Airline Management System

A simple Airline Management System built using Spring Boot.

## Features

- View all flight schedules.
- Get flight details by ID.
- Book a ticket.
- View ticket details by ticket ID.
- Cancel a booked ticket.
- Basic validations for duplication and input checks.

## Endpoints

### Flights

| Endpoint                            | Method | Description                          |
|------------------------------------|--------|--------------------------------------|
| `/flightSchedule?sort=asc/desc`    | GET    | Get all flight schedules (sorted)    |
| `/flightSchedule/id/{id}`          | GET    | Get flight schedule by ID            |
| `/flightSchedule`                  | POST   | Create a new flight schedule         |

### Tickets

| Endpoint                              | Method | Description                    |
|--------------------------------------|--------|--------------------------------|
| `/ticket/book`                       | POST   | Book a new ticket              |
| `/ticket/TicketId/{ticketId}`        | GET    | Get ticket details by ticket ID|
| `/ticket/cancel/{ticketId}`          | DELETE | Cancel a ticket by ticket ID   |

## Tools & Technologies

- Spring Boot
- Java
- REST API

---
