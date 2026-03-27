# LearnTrack — Student & Course Management System

A console-based management system built with **Core Java** to practice OOP fundamentals,
collections, exception handling, and clean modular design.

---

## Features

**Student Management**
- Add student (with or without email)
- View all students
- Search by ID
- Update student details
- Deactivate student (soft delete)

**Course Management**
- Add course (with or without description)
- View all courses
- Search by ID
- Toggle Active / Inactive

**Enrollment Management**
- Enroll a student in a course
- View enrollments by student
- View all enrollments
- Mark enrollment as Completed
- Cancel enrollment

---

## Project Structure

```
src/com/airtribe/learntrack/
├── Main.java
├── entity/
│   ├── Person.java          ← abstract base class
│   ├── Student.java         ← extends Person
│   ├── Trainer.java         ← extends Person (inheritance demo)
│   ├── Course.java
│   └── Enrollment.java
├── service/
│   ├── StudentService.java
│   ├── CourseService.java
│   └── EnrollmentService.java
├── exception/
│   ├── EntityNotFoundException.java
│   └── InvalidInputException.java
├── util/
│   ├── IdGenerator.java
│   └── InputValidator.java
└── enums/
    └── EnrollmentStatus.java
```

---

## Class Diagram

```
                 ┌─────────────────────┐
                 │   <<abstract>>      │
                 │      Person         │
                 │─────────────────────│
                 │ - id: int           │
                 │ - firstName: String │
                 │ - lastName: String  │
                 │ - email: String     │
                 │─────────────────────│
                 │ + getDisplayName()  │
                 └────────┬────────────┘
                          │ extends
             ┌────────────┴────────────┐
             │                         │
   ┌─────────▼──────────┐   ┌──────────▼──────────┐
   │      Student        │   │       Trainer        │
   │────────────────────│   │─────────────────────│
   │ - batch: String     │   │ - specialization    │
   │ - active: boolean   │   │─────────────────────│
   │────────────────────│   │ + getDisplayName()   │
   │ + getDisplayName()  │   └─────────────────────┘
   └────────────────────┘

   ┌──────────────────┐       ┌──────────────────────────┐
   │     Course        │       │        Enrollment         │
   │──────────────────│       │──────────────────────────│
   │ - id: int         │       │ - id: int                 │
   │ - courseName      │◄──────│ - studentId: int          │
   │ - description     │       │ - courseId: int           │
   │ - durationInWeeks │       │ - enrollmentDate          │
   │ - active: boolean │       │ - status: EnrollmentStatus│
   └──────────────────┘       └──────────────────────────┘

   ┌──────────────────────────────────────────────┐
   │                  IdGenerator                  │
   │──────────────────────────────────────────────│
   │ - studentIdCounter: int    (static)           │
   │ - courseIdCounter: int     (static)           │
   │ - enrollmentIdCounter: int (static)           │
   │──────────────────────────────────────────────│
   │ + getNextStudentId()       (static)           │
   │ + getNextCourseId()        (static)           │
   │ + getNextEnrollmentId()    (static)           │
   └──────────────────────────────────────────────┘
```

---

## How to Compile & Run

**Requirements:** JDK 17 or above

```bash
# From the LearnTrack/ root directory

# 1. Collect all source files
find src -name "*.java" > sources.txt

# 2. Create output directory
mkdir -p out

# 3. Compile
javac -d out @sources.txt

# 4. Run
java -cp out com.airtribe.learntrack.Main
```

**Using an IDE (IntelliJ / Eclipse / VS Code):**
1. Open the `LearnTrack/` folder as a project
2. Mark `src/` as the Sources Root
3. Run `Main.java`
