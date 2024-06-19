CREATE DATABASE training_db;

USE training_db;

CREATE TABLE Students (
    student_id CHAR(4) NOT NULL,
    name NVARCHAR(31) NOT NULL,
    email VARCHAR(63) NOT NULL,
    password VARCHAR(63) NOT NULL,
    PRIMARY KEY (student_id)
);

INSERT INTO Students
(student_id, name, email, password)
VALUES ('1801', 'Java太郎', 'java@example.com', 'JavaJava');
