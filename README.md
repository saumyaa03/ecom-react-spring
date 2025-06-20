# ADS Lifestyle Store – Backend

This repository contains the backend code for the **ADS Lifestyle Store**, a full-stack e-commerce platform. Built using Spring Boot, the backend provides secure REST APIs for authentication, product management, and user role enforcement.

---

## Features

- User registration and login
- Password hashing with Spring Security
- JWT-based authentication and authorization
- Role-based access (Admin/User)
- CRUD operations for products
- Product image support
- PostgreSQL database integration
- CORS enabled for React frontend

---

## Tech Stack

- Java 17
- Spring Boot
- Spring Security
- JWT (JSON Web Tokens)
- PostgreSQL
- Maven
- Lombok

---

## Project Structure

```
src/main/java/com/saumya/ecom/proj/
├── config/         # Security config (JWT filters, CORS, etc.)
├── controller/     # REST controllers for auth and products
├── dto/            # Data transfer objects
├── model/          # Entity classes (User, Product, etc.)
├── repository/     # Spring Data JPA repositories
├── service/        # Business logic and service layer
└── Application.java
```

---

## Getting Started

### Prerequisites

- Java 17+
- Maven
- PostgreSQL

### 1. Clone the repository

```bash
git clone https://github.com/saumyaa03/ads-backend.git
cd ads-backend
```

### 2. Configure application.properties

Edit `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/ads_db
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3. Run the application

```bash
mvn spring-boot:run
```

Backend runs on: http://localhost:8080

---

## API Endpoints

| Method | Endpoint             | Access     | Description           |
|--------|----------------------|------------|-----------------------|
| POST   | /auth/register       | Public     | Register new user     |
| POST   | /auth/login          | Public     | Login and get JWT     |
| GET    | /api/products        | Public     | Get all products      |
| POST   | /api/product         | Admin only | Add new product       |
| PUT    | /api/product/{id}    | Admin only | Update product        |
| DELETE | /api/product/{id}    | Admin only | Delete product        |

---

## Authentication Flow

- On login, a JWT token is generated and returned.
- Token is sent in the `Authorization: Bearer <token>` header.
- Spring Security verifies the token and role.
- Access is restricted using role-based method security and filters.

---

## Author

**Saumya Rana**  
Master’s in Applied Computer Science, Concordia University  
[GitHub](https://github.com/saumyaa03)  
[LinkedIn](https://linkedin.com/in/saumya-rana12)

