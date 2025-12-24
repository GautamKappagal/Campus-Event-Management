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