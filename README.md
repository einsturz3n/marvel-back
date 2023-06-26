# marvel-api
API to provide Marvel Comics data

This project was created from the Spring Initializr with H2 Database, Spring Web, Spring Data JPA, Spring Security and Spring Boot Dev Tools

Based on https://developer.marvel.com/docs#!/public

Access http://localhost:8080/v1/characters/ with user "test" and password "marvel" to see a list of characters.

###API Documentation

http://localhost:8080/swagger-ui.html

### APIs endpoints
GET http://localhost:8080/v1/characters/ [list all characters]  
GET http://localhost:8080/v1/characters/{id} [list a character by ID]
