# Campus Event Management System

A backend REST API built using Spring Boot for managing campus clubs, events, admin approvals, and student registrations.

This project simulates a real-world campus workflow where clubs propose events, admins approve them, and students register.

---

## Tech Stack

- Java 22
- Spring Boot
- Spring Data JPA
- H2 In-Memory Database
- Maven
- Postman (for API testing)

---

## Core Features

- Create and manage clubs
- Clubs can create events
- Admin can view and approve pending events
- Students can view approved events
- Students can register for events
- Capacity constraints enforced

---

## Application Flow

Club → Event Creation → Admin Approval → Student Registration

---

## Database Design

Tables (auto-generated using JPA):
- club
- event
- app_user
- registration

The database is in-memory and resets on every restart.

---

## How to Run the Project

### Prerequisites
- Java 17 or higher (Java 22 recommended)
- Maven

### Run Command
```bash
mvn spring-boot:run
```

The application will start on:
http://localhost:8080

---

## API Testing (Postman)

A ready-to-use Postman collection is included in the project.

Location:
PostmanCollections/CampusEventManagement.postman_collection.json

Steps:
1. Open Postman
2. Click Import
3. Select the collection JSON file
4. Run requests in sequence

---

## API Endpoints

### Clubs
- POST /clubs — Create a club

### Events
- POST /events — Create an event (pending approval)
- GET /events — View approved events

### Admin
- GET /admin/pending-events — View pending events
- POST /admin/approve/{eventId} — Approve an event

### Registration
- POST /events/{eventId}/register/{userId} — Register for an event

---

## Validation Rules

- Event title cannot be empty
- Event capacity must be greater than 0
- Registration fails if:
  - Event is not approved
  - Event capacity is full
  - Event or user does not exist

---

## Error Handling

Centralized exception handling ensures meaningful error responses.

Example response:
```json
{
  "error": "Event not approved yet"
}
```

Handled cases:
- Invalid input
- Resource not found
- Business logic violations

---

## Role Simulation

Roles are simulated without authentication:
- ADMIN — Approves events
- STUDENT — Registers for events
- CLUB_ADMIN — Creates events

Role checks are enforced at the service and controller layer.

---

## Possible Enhancements

- Add Spring Security with JWT authentication
- Add Swagger / OpenAPI documentation
- Add pagination and filtering
- Use a persistent database (MySQL or PostgreSQL)
- Introduce DTOs for cleaner API contracts