# Back-End for Unix "cd" Command Simulation

## Objective
The goal of this project is to create an API that simulates the Unix "cd" command. This API allows users to navigate a virtual file system starting from the root directory ("/").

## API Overview
The API provides a single endpoint `/api/cd` that accepts a POST request with a command and returns the new current working directory (cwd).

## Requirements
- Java 11 or higher
- Maven
- Spring Boot

## Installation
1. Clone the repository:
```bash
git clone <repository_url>
```
2. Navigate to the project directory:
```bash
cd backend
```
3.	Build the project:
```bash
mvn clean install
```
## Usage
1. Start the server:
```bash
mvn spring-boot:run
```
2.	The server will start on port 8080 by default. You can test the endpoint using a tool like curl or Postman.
#### Example Request
```bash
curl -X POST http://localhost:8080/api/cd -H "Content-Type: application/json" -d '{"command": "cd /bar"}'
```
#### Example Response
```json
{
   "cwd": "/bar"
}   
```
