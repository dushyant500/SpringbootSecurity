Spring Boot Security with JWT and Role-Based Access
This project is a Spring Boot Security demo that integrates JWT (JSON Web Token) authentication and role-based authorization. It includes secure user registration, password encryption, and restricted API access based on user roles.

🚀 Features
✅ User Registration

Stores users in a database.

Passwords are encrypted using BCryptPasswordEncoder with 12 rounds of hashing.

🔐 JWT Authentication

Generates JWT tokens upon successful login.

Validates tokens for secure access to protected endpoints.

🔒 Role-Based Authorization

APIs are protected and accessible based on user roles (e.g., ROLE_USER, ROLE_ADMIN).

Custom access control for specific endpoints.

🛠️ Technologies Used
Spring Boot

Spring Security

JWT (JSON Web Token)

H2 / MySQL (for persistence)

BCrypt (for password hashing)

