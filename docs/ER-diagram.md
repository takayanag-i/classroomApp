# ER図
@version: 1.1.0


```mermaid
erDiagram
    Students {
        CHAR(4) student_id PK
        NVARCHAR(31) name
        VARCHAR(63) email
        VARCHAR(63) password
    }
    
    Instructors {
        CHAR(5) instructor_id PK
        NVARCHAR(31) name
        VARCHAR(63) email
        VARCHAR(63) password
    }
        
    Courses {
        CHAR(5) course_id PK
        NVARCHAR(31) course_name
        NCHAR(2) day_of_week
        INT period
    }
    
    Enrollments {
        CHAR(4) student_id FK
        CHAR(5) course_id FK
        DATETIME2 enrollment_date
    }

    Instructions {
        string course_id FK
        string instructor_id FK
    }
        
    Students ||--o{ Enrollments : enrolls
    Courses ||--o{ Enrollments : includes
    Courses ||--o{ Instructions : includes
    Instructors ||--o{ Instructions : instructs
```