**SpringBootEssentials - Minsait**
========================================================================

This project was developed in Minsait's "SpringBoot Essentials" training. For the Student Enrollment API developed in the training, the following functionalities were designed: CRUD of courses and students, enrolling students in a course, searching for enrolled students, etc.

**Some of the resources used**
- Git
- Maven
- Spring Boot 3.x.x
- H2
- REST
- JPA (Hibernate)
- Spring Data

EXAMPLES OF EXECUTION

find all students GET : localhost:8080/students

Response:
```json
[
  {
    "id": 1,
    "name": "John",
    "createdAt": "2024-03-12T11:53:08.368907"
  },
  {
    "id": 2,
    "name": "Mary",
    "createdAt": "2024-03-12T11:53:08.368907"
  },
  {
    "id": 3,
    "name": "Michael",
    "createdAt": "2024-03-12T11:53:08.368907"
  }
]
```
find all courses GET: localhost:8080/courses

Response:
```json
[
  {
    "id": 1,
    "title": "Algorithms and Data Structures",
    "credits": 4
  },
  {
    "id": 2,
    "title": "Cybersecurity",
    "credits": 4
  },
  {
    "id": 3,
    "title": "Theory of Computation",
    "credits": 4
  },
  {
    "id": 4,
    "title": "Artificial Intelligence",
    "credits": 4
  },
  {
    "id": 5,
    "title": "Web Development",
    "credits": 4
  },
  {
    "id": 6,
    "title": "Distributed Systems",
    "credits": 4
  }
]
```
Create a new student POST: localhost:8080/students

Request:
```json
{
  "name": "Emma"
}
```
Updates an existing student PATCH: localhost:8080/students/1

Request:
```json
{
  "name": "Sophia"
}
```
find course GET: localhost:8080/courses/1

Response:
```json
{
  "id": 1,
  "title": "Algorithms and Data Structures",
  "credits": 4
}
```
Create course POST: localhost:8080/courses

Request:
```json
{
  "title": "Computer Networks",
  "credits": 4
}
```
Updates an existing course PATCH: localhost:8080/courses/8

Request:
```json
{
  "title": "Human-Computer Interaction",
  "credits": 4
}
```

find enrolled GET: localhost:8080/enrollees/1

Response:
```json
{
  "name": "John",
  "course": "Algorithms and Data Structures"
}
```

Create enrollment POST: localhost:8080/enrollees

Request:
```json
{
  "idCourse": 6,
  "idStudent": 1
}
```

Updates an existing enrollment PATCH: localhost:8080/enrollees/1

Request:
```json
{
  "idCourse": 5,
  "idStudent": 1
}
```

Delete an existing course DELETE: localhost:8080/courses/1

Delete an existing student DELETE: localhost:8080/students/1

Delete an existing enrollment DELETE: localhost:8080/enrollees/1
