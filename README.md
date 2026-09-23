# Student & Course Manager — Full Stack Java Prototype

A beginner-friendly full-stack project to learn/demonstrate Java web development:
**Spring Boot (backend + REST API) + HTML/CSS/JavaScript (frontend) + H2 (in-memory database)**.

No external database setup needed — it just runs.

## What it does
- Add/list/delete **Students**
- Add/list/delete **Courses**
- **Enroll** a student in a course and track grades
- All data is stored via a REST API backed by JPA/Hibernate

## Tech stack
| Layer      | Technology                          |
|------------|--------------------------------------|
| Backend    | Java 17, Spring Boot 3, Spring Data JPA |
| Database   | H2 (in-memory)                      |
| Frontend   | Plain HTML, CSS, JavaScript (fetch API) |
| Build tool | Maven                               |

## Project structure
```
edu-prototype/
├── pom.xml
├── src/main/java/com/example/edu/
│   ├── EduApplication.java          # main entry point
│   ├── model/                       # Student, Course, Enrollment entities
│   ├── repository/                  # Spring Data JPA repositories
│   └── controller/                  # REST controllers (StudentController, CourseController, EnrollmentController)
└── src/main/resources/
    ├── application.properties       # DB + server config
    └── static/                      # frontend (index.html, app.js, style.css)
```

## How to run

### Requirements
- Java 17 or newer (`java -version`)
- Maven 3.6+ (`mvn -version`) — or open the folder directly in **IntelliJ IDEA** / **Eclipse** / **VS Code (with Java extensions)**, which bundles Maven support.

### Option A — Command line
```bash
cd edu-prototype
mvn spring-boot:run
```

### Option B — In an IDE
1. Open the `edu-prototype` folder as a Maven project.
2. Let the IDE download dependencies.
3. Run `EduApplication.java` (right-click → Run).

Then open your browser at:
```
http://localhost:8080
```
You'll see the working UI — add students, add courses, and enroll them.

The H2 database console (to inspect the tables directly) is at:
```
http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:eduDb
User: sa
Password: (leave blank)
```

## REST API reference (for learning/testing with Postman)
| Method | Endpoint                     | Description            |
|--------|-------------------------------|-------------------------|
| GET    | `/api/students`               | list all students      |
| POST   | `/api/students`               | create a student        |
| PUT    | `/api/students/{id}`          | update a student        |
| DELETE | `/api/students/{id}`          | delete a student        |
| GET    | `/api/courses`                | list all courses       |
| POST   | `/api/courses`                | create a course         |
| POST   | `/api/enrollments`            | enroll `{studentId, courseId}` |
| PUT    | `/api/enrollments/{id}/grade` | set a grade `{grade}`   |
| DELETE | `/api/enrollments/{id}`       | remove an enrollment    |

## Suggested next steps (to grow this into a bigger portfolio project)
1. Add Spring Security + login (JWT) for student/admin roles.
2. Swap H2 for MySQL/PostgreSQL for persistent storage.
3. Add pagination and search/filter to the student & course lists.
4. Add unit tests with JUnit + Mockito for the controllers/repositories.
5. Deploy it (e.g., Render, Railway, or a free-tier AWS/Azure instance) and put the live link on your resume.
6. Rebuild the frontend in React for a more modern UI (optional).

This prototype intentionally keeps things simple so you can read every line of code and understand
exactly how a Spring Boot REST backend talks to a JPA database and a plain JS frontend — a solid
foundation before adding more advanced features.
