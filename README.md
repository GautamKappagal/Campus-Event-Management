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
# Campus Event Management System

A backend REST API built using Spring Boot for managing campus clubs, events, admin approvals, student registrations, and secure authentication using JWT.

This project simulates a real-world campus workflow where clubs propose events, admins approve them, and students register.

---

## Tech Stack

- Java 22
- Spring Boot 3
- Spring Data JPA
- Spring Security + JWT
- Swagger / OpenAPI
- H2 In-Memory Database
- Maven

---

## Features

### Core Features
- Create and manage clubs
- Club admins can create events
- Admins can approve pending events
- Students can view approved events
- Students can register for events
- Capacity constraints enforced

### Authentication & Authorization
- Login using `/auth/login`
- JWT-based authentication
- Swagger supports Bearer tokens
- Role-based access control using Spring Security

### Roles
| Role | Abilities |
|------|-----------|
| **ADMIN** | Approve events, view pending events |
| **CLUB_ADMIN** | Create clubs and events |
| **STUDENT** | View and register for events |

---

## Application Flow

```
Club Admin → Create Event → Admin Approval → Student Registration
```

---

## Database Design

Tables generated automatically using JPA:
- `club`
- `event`
- `app_user`
- `registration`

The database is in-memory (H2) and resets on every restart.

---

## How to Run the Project

### Prerequisites
- Java 17+ (Java 22 recommended)
- Maven installed

### Run the Application

```bash
mvn spring-boot:run
```

The server starts at:
```
http://localhost:8080
```

---

## Swagger UI

API documentation and testing available at:

```
http://localhost:8080/swagger-ui/index.html
```

### How to Authenticate in Swagger

1. Go to `POST /auth/login`
2. Use a default user:

```json
{
  "username": "admin",
  "password": "admin"
}
```

3. Copy the returned JWT token  
4. Click **Authorize**  
5. Paste the token in this format:

```
Bearer <your-token>
```

Now you can access protected endpoints.

---

## API Endpoints

### Auth
- `POST /auth/login` — Generate JWT token

### Clubs
- `POST /clubs` — Create a club (ROLE_CLUB_ADMIN)

### Events
- `POST /events` — Create an event (pending approval)
- `GET /events` — View approved events

### Admin
- `GET /admin/pending-events` — View pending events
- `POST /admin/approve/{eventId}` — Approve an event

### Registration
- `POST /events/{eventId}/register/{userId}` — Register for an event

---

## Validation Rules

- Event title cannot be empty
- Event capacity must be greater than 0
- Registration fails if:
  - Event not approved
  - Event full
  - Event/user does not exist

---

## Error Handling

Meaningful JSON errors returned for:

- Validation failures
- Resource not found
- Business rule violations

Example:

```json
{
  "error": "Event not approved yet"
}
```

---

## Default Seed Data (data.sql)

Users preloaded at startup:

| Username | Password | Role |
|----------|----------|------|
| admin | admin | ROLE_ADMIN |
| clubadmin | clubadmin | ROLE_CLUB_ADMIN |
| student | student | ROLE_STUDENT |

Sample clubs and events are also inserted automatically.

---

## Postman Collection

A full Postman API collection is included:

```
PostmanCollections/CampusEventManagement.postman_collection.json
```

---

## Possible Enhancements

- Encrypt passwords with BCrypt
- Add email notifications
- Add event waitlists
- Migrate to PostgreSQL
- Add user profiles
- Deploy on AWS / Render
- Add CI/CD