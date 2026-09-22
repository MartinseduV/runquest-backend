# 🏃 RunQuest Backend

Backend REST API developed with **Java and Spring Boot** for RunQuest, a fitness application that combines running and walking with RPG progression mechanics.

The project is currently under development and is also being used to practice backend development concepts, API design, persistence, validation and automated testing.

## 🚀 Technologies

- Java
- Spring Boot
- Spring Data JPA / Hibernate
- PostgreSQL
- Maven
- JUnit
- Mockito
- Bean Validation
- Swagger / OpenAPI

## 🏗 Architecture

The project follows a layered architecture:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
PostgreSQL
```

- **Controller:** handles HTTP requests and responses.
- **Service:** contains business rules.
- **Repository:** handles data persistence.
- **Entity:** represents persisted domain data.
- **DTO:** controls data transferred through the API.

## ✅ Current Features

- Player creation and management
- Run registration
- Player and run persistence with PostgreSQL
- Player-to-run relationship
- XP calculation based on completed runs
- Player level progression
- Request validation
- Global exception handling
- Standardized API error responses
- Unit tests with JUnit and Mockito
- API documentation with Swagger/OpenAPI

## 📡 API Examples

Some of the currently implemented endpoints include:

```http
POST /players
GET /players
GET /players/{id}
PUT /players/{id}
DELETE /players/{id}

POST /players/{id}/runs
GET /players/{id}/runs
```

## 🧪 Tests

The project includes unit tests for the service layer using **JUnit and Mockito**, covering business logic and error scenarios.

Run the tests with:

```bash
./mvnw test
```

On Windows:

```bash
mvnw.cmd test
```

## ▶️ Running the Project

### Requirements

- Java
- PostgreSQL

Create a PostgreSQL database named:

```text
runquest
```

The database password is provided through the `DB_PASSWORD` environment variable and is not stored in the source code.

Then run:

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

## 📖 API Documentation

With the application running, the API documentation can be accessed through Swagger UI.

```text
http://localhost:8080/swagger-ui/index.html
```

## 🔮 Project Roadmap

Planned improvements include:

- Authentication and authorization
- Improved test coverage
- Docker support
- Security improvements
- Expansion of the RPG progression system

---

Developed by **Eduardo Martins Ventura Filho**.