# To-Do List REST API

A Spring Boot backend service that lets users create, read, update, delete, and **fuzzy**-search tasks.  
Built with Java 17, Spring Boot 3, Hibernate Search (Lucene) and MySQL.

---

## Tech Stack

- **Java 17**  
- **Spring Boot 3** (Web, Data JPA, Validation)  
- **Hibernate Search** (Lucene backend) for fuzzy full-text search  
- **MySQL**
- **Maven** for build & dependency management  

---

## Features

- **CRUD on Tasks**  
  - Title, description, due date, status (PENDING / COMPLETED)  
- **Pagination**  
  - `GET /api/tasks?page=0&size=10`
- **Global Exception Handling**  
  - Custom 404 and validation error payloads  
- **Full-Text Fuzzy Search**  
  - `GET /api/tasks/search?query=meetng&limit=5`  
  - Matches “meetng”, “meeting”, etc.  


## Configure MySQL
- Update src/main/resources/application.properties with your credentials
- `spring.datasource.url=jdbc:mysql://localhost:3306/todo_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=secret`

## Build & Run
`mvn clean package
java -jar target/todo-0.0.1-SNAPSHOT.jar`

## API Endpoints

| Method | Endpoint                   | Description                                      |
|--------|----------------------------|--------------------------------------------------|
| **POST**   | `/api/tasks`             | Create a new task                                |
| **GET**    | `/api/tasks`             | List all tasks (paginated)                       |
| **GET**    | `/api/tasks/{id}`        | Retrieve a task by its ID                        |
| **PUT**    | `/api/tasks/{id}`        | Update an existing task                          |
| **DELETE** | `/api/tasks/{id}`        | Delete a task by its ID                          |
| **GET**    | `/api/tasks/search`      | Fuzzy-search tasks by text<br>Query params:<br>- `query` (search term)<br>- `limit` (max results, default 10) |

