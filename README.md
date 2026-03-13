# Interview Insight Tracker – Backend

A backend application that helps candidates **track interview questions, record attempts, and analyze performance** to identify strengths and weak areas across different topics.

---

## Features

* JWT-based **stateless authentication**
* User-scoped **interview question management**
* **Attempt tracking system** for recording question attempts
* Performance **analytics engine**
* Overall **accuracy calculation**
* **Topic-wise performance insights**
* Detection of **weak areas** based on attempt history
* **DTO-based API design** for clean request handling
* **Global exception handling** for consistent error responses
* Database **indexing for performance optimization**

---

## Tech Stack

**Backend:** Java, Spring Boot, Spring Security, JWT, Spring Data JPA  
**Database:** PostgreSQL  
**Concepts Used:** REST APIs, JPQL, DTO Pattern, Global Exception Handling  
**Tools:** Postman, Git, GitHub, Maven

---

## API Overview

### Authentication

* **POST** `/auth/register` — Register new user  
* **POST** `/auth/login` — Authenticate and receive JWT

### Questions

* **POST** `/questions` — Create interview question

### Attempts

* **POST** `/attempts` — Submit attempt for a question

### Analytics

* **GET** `/analytics/accuracy` — Overall accuracy percentage  
* **GET** `/analytics/accuracy-by-topic` — Topic-wise performance  
* **GET** `/analytics/weak-areas` — Identify weak topics

---

## Project Architecture

```
/interview-insight-tracker
 ├── src/main/java/com/iit
 │    ├── config
 │    │    ├── SecurityConfig
 │    │    ├── JwtFilter
 │    │    └── JwtUtil
 │    ├── controller
 │    │    ├── AuthController
 │    │    ├── QuestionController
 │    │    ├── AttemptController
 │    │    └── AnalyticsController
 │    ├── dto
 │    │    ├── LoginRequest
 │    │    ├── RegisterRequest
 │    │    ├── QuestionRequest
 │    │    └── AttemptRequest
 │    ├── entity
 │    │    ├── User
 │    │    ├── Question
 │    │    └── Attempt
 │    ├── repository
 │    │    ├── UserRepository
 │    │    ├── QuestionRepository
 │    │    └── AttemptRepository
 │    ├── exception
 │    │    └── GlobalExceptionHandler
 │    └── InterviewInsightTrackerApplication
 ├── src/main/resources
 │    └── application.properties
 └── pom.xml
```

---

## Running the Project

### Backend

Make sure PostgreSQL is running and database is created.

```
mvn spring-boot:run
```

The API server will start on:

```
http://localhost:8080
```

You can test endpoints using **Postman**.

---

## Key Architecture Highlights

* **Stateless Authentication** using JWT
* **Attempt data modeled as immutable events**
* **Analytics computed using JPQL queries**
* **Database indexes** added to improve query performance
* **DTO-based API design** to prevent entity exposure
* **Centralized exception handling** for clean API responses

---

## Author

Sangram Swain
