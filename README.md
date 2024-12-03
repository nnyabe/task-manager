# Task Manager API

This project is a Task Manager API built using Java and Spring Boot. It provides CRUD (Create, Read, Update, Delete) operations for managing tasks through a RESTful API. The project allows users to create, retrieve, update, and delete tasks, with the task details being persisted in a database.

## Table of Contents
- [Overview](#overview)
- [Technologies Used](#technologies-used)
- [API Endpoints](#api-endpoints)
- [How to Run the Project](#how-to-run-the-project)
- [Project Structure](#project-structure)
- [License](#license)

## Overview
The Task Manager API is designed to help manage tasks with details such as title, description, due date, and status. This project provides endpoints for creating, retrieving, updating, and deleting tasks. Each task is represented as a `TaskModel`, and data transfer is facilitated by `TaskDTO`.

## Technologies Used
- **Java 17** (or higher)
- **Spring Framework** (version 2.7.x or higher)
- **Spring Web** for building REST APIs
- **Servlet** for database interaction
- **JDBC** (for any supported relational database)
- **Maven** for project management and dependency management

## API Endpoints

### 1. List All Tasks
- **HTTP Method**: `GET`
- **Endpoint**: `/api/v1/tasks/`
- **Response**: Returns a list of all tasks with their details.
- **Status Code**: `200 OK`
- **Response Body**:
    ```json
    [
        {
            "id": 1,
            "title": "Task 1",
            "url": "http://localhost:8080/api/v1/tasks/1"
        },
        ...
    ]
    ```

### 2. Get a Task by ID
- **HTTP Method**: `GET`
- **Endpoint**: `/api/v1/tasks/{id}`
- **Path Parameter**: `id` (integer) - ID of the task.
- **Response**: Returns the task with the specified ID.
- **Status Code**: `200 OK`
- **Response Body**:
    ```json
    {
        "id": 1,
        "title": "Task 1",
        "description": "Description of Task 1",
        "dueDate": "2024-12-01",
        "status": "Pending"
    }
    ```

### 3. Create a Task
- **HTTP Method**: `POST`
- **Endpoint**: `/api/v1/tasks/`
- **Request Body**:
    ```json
    {
        "title": "New Task",
        "description": "Description of the new task",
        "dueDate": "2024-12-15",
        "status": "Pending"
    }
    ```
- **Response**: Returns the created task with a location URL.
- **Status Code**: `201 Created`
- **Response Body**:
    ```json
    {
        "title": "New Task",
        "url": "http://localhost:8080/api/v1/tasks/1"
    }
    ```

### 4. Update a Task
- **HTTP Method**: `PUT`
- **Endpoint**: `/api/v1/tasks/{id}`
- **Path Parameter**: `id` (integer) - ID of the task.
- **Request Body**:
    ```json
    {
        "title": "Updated Task Title",
        "description": "Updated description",
        "dueDate": "2024-12-20",
        "status": "Completed"
    }
    ```
- **Response**: Returns the updated task with a location URL.
- **Status Code**: `200 OK`
- **Response Body**:
    ```json
    {
        "id": 1,
        "title": "Updated Task Title",
        "url": "http://localhost:8080/api/v1/tasks/1"
    }
    ```

### 5. Delete a Task
- **HTTP Method**: `DELETE`
- **Endpoint**: `/api/v1/tasks/{id}`
- **Path Parameter**: `id` (integer) - ID of the task.
- **Response**: Returns a confirmation message.
- **Status Code**: `200 OK`
- **Response Body**:
    ```json
    {
        "message": "Task with ID 1 deleted successfully."
    }
    ```

## How to Run the Project

### Prerequisites
- **Java Development Kit (JDK)** 17 or higher
- **Apache Maven** for building and running the project

### Steps to Run
1. **Clone the repository**:
    ```bash
    git clone https://github.com/your-username/task-manager-api.git
    cd task-manager-api
    ```

2. **Build the project**:
    ```bash
    mvn clean install
    ```

3. **Run the project**:
    ```bash
    mvn spring-boot:run
    ```

4. The application will start and be available at `http://localhost:8080`.

## Project Structure
The project structure is organized as follows:
```
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── task/
│   │   │           └── manager/
│   │   │               ├── controller/
│   │   │               │   └── TaskController.java
│   │   │               ├── dto/
│   │   │               │   └── TaskDTO.java
│   │   │               ├── model/
│   │   │               │   └── TaskModel.java
│   │   │               ├── repository/
│   │   │               │   └── TaskRepository.java
│   │   │               └── service/
│   │   │                   └── TaskServiceImpl.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── static/
│   
│
└── pom.xml
```

