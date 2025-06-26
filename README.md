# EduConnect-CLI

**EduConnect-CLI** is a console-based Student, Course, and Enrollment Management System built with Java and MS SQL Server. Designed to reinforce core programming concepts like OOP, CRUD operations, and JDBC, this project serves as a perfect entry-level backend application for learners and freshers.

---

## 📑 Table of Contents

- [About the Project](#about-the-project)
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Setup Guide](#setup-guide)
  - [Prerequisites](#prerequisites)
  - [Database Setup](#database-setup)
  - [Project Setup (Eclipse)](#project-setup-eclipse)
  - [Database Credentials Configuration](#database-credentials-configuration)
- [Final Run](#final-run)
- [Usage](#usage)
- [Demo](#demo)
- [Contributing](#contributing)
- [License](#license)

---

## 📘 About the Project

**EduConnect-CLI** is a command-line Java application that simplifies the management of student data, course offerings, and student enrollments. It demonstrates fundamental backend development skills through real-world CRUD operations using JDBC with MS SQL Server.

---

## ✅ Features

### 👨‍🎓 Students

| Operation | Description |
|----------|-------------|
| Create   | Add new student details (Name, Email, Phone, DOB, Gender) |
| Read     | View or search student records |
| Update   | Modify student information |
| Delete   | Remove student record |

### 📚 Courses

| Operation | Description |
|----------|-------------|
| Create   | Add new courses (Name, Duration) |
| Read     | View or search courses |
| Update   | Modify course details |
| Delete   | Remove a course |

### 📝 Enrollments

| Operation | Description |
|----------|-------------|
| Create   | Enroll students in courses |
| Read     | View or filter enrollments |
| Update   | Edit enrollment data |
| Delete   | Remove enrollments |

---

## 🛠️ Technology Stack

| Category    | Technology       | Version        | Purpose                       |
|-------------|------------------|----------------|-------------------------------|
| Language    | Java             | JDK 17+        | Core application logic        |
| Database    | MS SQL Server    | Any recent     | Relational data storage       |
| Connectivity| JDBC             | Standard API   | Java to SQL communication     |
| IDE         | Eclipse IDE      | Latest stable  | Development environment       |

---

## 🚀 Setup Guide

### ✅ Prerequisites

- Java JDK 17 or later
- MS SQL Server (any version)
- SSMS or any SQL client
- Git
- Eclipse IDE

---

### 🗃️ Database Setup

Open SQL Server and run:

```sql
USE StudentDB;
GO

-- Drop existing tables (if any)
IF OBJECT_ID('dbo.Enrollments', 'U') IS NOT NULL DROP TABLE dbo.Enrollments;
IF OBJECT_ID('dbo.Students', 'U') IS NOT NULL DROP TABLE dbo.Students;
IF OBJECT_ID('dbo.Courses', 'U') IS NOT NULL DROP TABLE dbo.Courses;

-- Create tables
CREATE TABLE Students (
    student_id INT PRIMARY KEY IDENTITY(1,1),
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone VARCHAR(20),
    dob DATE,
    gender VARCHAR(10)
);

CREATE TABLE Courses (
    course_id INT PRIMARY KEY IDENTITY(1,1),
    course_name VARCHAR(255) NOT NULL,
    duration VARCHAR(50)
);

CREATE TABLE Enrollments (
    enrollment_id INT PRIMARY KEY IDENTITY(1,1),
    student_id INT NOT NULL,
    course_id INT NOT NULL,
    enroll_date DATE NOT NULL,
    FOREIGN KEY (student_id) REFERENCES Students(student_id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES Courses(course_id) ON DELETE CASCADE
);

-- Insert sample data
INSERT INTO Students (name, email, phone, dob, gender)
VALUES ('Alice Smith', 'alice.s@example.com', '111-222-3333', '2000-01-15', 'Female'),
       ('Bob Johnson', 'bob.j@example.com', '444-555-6666', '1999-03-20', 'Male');

INSERT INTO Courses (course_name, duration)
VALUES ('Introduction to Java', '3 months'),
       ('Database Fundamentals', '2 months');
