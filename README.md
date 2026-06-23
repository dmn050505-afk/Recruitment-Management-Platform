# Recruitment Management Platform

Enterprise recruitment management platform developed with Spring Boot, PostgreSQL, JWT Authentication and Docker.

The platform allows recruiters to create and manage job vacancies, candidates to apply for positions, and recruiters to track applications through the recruitment process.

## Key Features

* JWT Authentication
* Role-Based Access Control (ADMIN, RECRUITER, CANDIDATE)
* Job Management
* Application Management
* Recruiter Workflow
* Global Exception Handling
* Request Validation
* Swagger/OpenAPI Documentation
* Dockerized Deployment

## Technology Stack

* Java 21
* Spring Boot
* Spring Security
* Spring Data JPA
* PostgreSQL
* JWT
* Docker
* Swagger / OpenAPI
* Maven
* Lombok

## Security

Authentication and authorization are implemented using Spring Security and JWT tokens.

Roles supported:

* ADMIN
* RECRUITER
* CANDIDATE

Protected endpoints require a valid JWT token.

## API Documentation

<img width="1346" height="911" alt="image" src="https://github.com/user-attachments/assets/3d2743e3-5165-4ee0-9468-c2168e34b39e" />

Swagger UI:

http://localhost:8080/swagger-ui/index.html

## Running with Docker

<img width="977" height="356" alt="image" src="https://github.com/user-attachments/assets/14c4b214-c5a1-455d-8a78-877aeef591ee" />

Start the entire environment:

```bash
docker compose up --build
```

Stop containers:

```bash
docker compose down
```

Services started:

* recruitment-app
* recruitment-db

## Project Structure

* Controller Layer
* Service Layer
* Repository Layer
* DTO Layer
* Security Layer
* Global Exception Handling

## Future Improvements

* Pagination
* Refresh Tokens
* Unit Tests
* Integration Tests
* CI/CD Pipeline

## Author

Daniel Nunes
