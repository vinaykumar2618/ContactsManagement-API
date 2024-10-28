#Project Title :: Contacts Management API

DESCRPTION:

The Contacts Management API is a RESTful application for managing contact information. It includes features for creating, updating, retrieving, and deleting contact details with added layers of security through JWT-based authentication and role-based access control.

Table of Contents

Features, Technologies, Prerequisites, Installation, API Endpoints, Usage,

Features

JWT Authentication: Secure login using JSON Web Tokens. Role-Based Access Control: Access control for user roles (USER, ADMIN) without a separate Role entity. CRUD Operations: Full create, read, update, and delete operations for contacts. Swagger Documentation: Integrated Swagger UI for API documentation and testing.

Technologies Spring Boot: 3.x (Spring Web, Spring Security, Spring Data JPA) JWT: JSON Web Token for secure, stateless authentication. MySQL: Database for persisting user and contact data. Swagger: API documentation and testing interface. Lombok: Simplify model code with annotations. H2 Database: Optional in-memory testing database.

Prerequisites Java: JDK 17 or later MySQL: Ensure MySQL server is running and accessible. Postman (or swagger): For testing API endpoints.

Installation Clone the Repository:

bash Copy code git clone https://github.com/vinaykumar2618/ContactsManagement-API.git cd ContactsManagement-API

Configure Database:

Set up a MySQL database. Update application.properties with your database credentials: properties Copy code spring.datasource.url=jdbc:mysql://localhost:3306/your_database spring.datasource.username=your_username spring.datasource.password=your_password

Run the Application:

The server will start on http://localhost:8282.

API Endpoints

Authentication

Method Endpoint Description

POST /auth/login Authenticate user and return a JWT token. POST /auth/register Register a new user. Contacts Method Endpoint Description POST /contact/create Create a new contact. PUT /contact/update/{id} Update an existing contact by ID. GET /contact/getAllContacts Retrieve all contacts. DELETE /contact/delete/{id} Delete a contact by ID. POST /contact/merge Merge multiple contacts by their IDs.

Usage

Register a User:

Send a POST request to /auth/register with JSON payload: json Copy code { "username": "newuser", "password": "password", "role": "USER" } 
Authenticate:
http://localhost:8282/api/getAllContacts             : Admin access
http://localhost:8282/api/create                     :Admin access
http://localhost:8282/api/update                     : User access
Send a POST request to /auth/login to receive a JWT token. Authorize Requests:

Use the JWT token as a Bearer token for accessing secured endpoints (e.g., CRUD operations for contacts).
Swagger Documentation
Access the Swagger UI at http://localhost:8282/swagger-ui.html.





https://github.com/vinaykumar2618/ContactsManagement-API.git 

