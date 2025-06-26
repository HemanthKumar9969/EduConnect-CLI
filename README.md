EduConnect-CLI
A Console-Based Student, Course, and Enrollment Management System

Table of Contents
About the Project

Features

Technology Stack

Setup Guide

Prerequisites

Database Setup

Project Setup (Eclipse)

Database Credentials Configuration

How to Run

Usage

Demo

Contributing

License

About the Project
EduConnect-CLI is a beginner-friendly, console-based application designed to demonstrate fundamental Java programming concepts. It serves as a simple Student, Course, and Enrollment Management System, focusing on core Object-Oriented Programming (OOP) principles, CRUD (Create, Read, Update, Delete) operations, and JDBC (Java Database Connectivity) for interaction with an MS SQL Server database.

This project is ideal for freshers and beginners looking to understand:

How to structure a basic Java application.

Implementing OOP concepts like encapsulation (via Getter/Setter methods).

Performing standard database operations (CRUD).

Connecting Java applications to a relational database using JDBC.

Managing project dependencies and sensitive information (e.g., database credentials).

Features
The application provides a menu-driven interface to manage three core entities: Students, Courses, and Enrollments.

Feature Category

Operation

Description

Students

Create

Add new student records with Name, Email, Phone, Date of Birth, and Gender.



Read

View all registered students or search for a specific student by ID.



Update

Modify existing student details (e.g., Name, Email, Phone).



Delete

Remove a student record from the system.

Courses

Create

Add new courses with Course Name and Duration.



Read

View all available courses or search for a specific course by ID.



Update

Modify existing course details (e.g., Name, Duration).



Delete

Remove a course from the system.

Enrollments

Create

Enroll a student in a course by linking Student ID and Course ID with an Enrollment Date.



Read

View all enrollments, or filter enrollments by Student ID or Course ID.



Update

Modify existing enrollment details (e.g., change enrolled student/course, update date).



Delete

Remove an enrollment record.

Technology Stack
This project is built using foundational Java and database technologies:

Category

Technology

Version(s)

Purpose

Backend Logic

Java

JDK 17+ (LTS recommended)

Core programming language.

Database

MS SQL Server

Any recent version

Relational database for data storage.

Connectivity

JDBC

Standard API

Java Database Connectivity for MS SQL Server.

IDE

Eclipse IDE

Latest Stable

Development environment.

Setup Guide
Follow these steps to set up and run the EduConnect-CLI project on your local machine.

Prerequisites
Ensure you have the following installed:

Java Development Kit (JDK): Version 17 or higher.

MS SQL Server: A running instance of SQL Server.

SQL Server Management Studio (SSMS) or any other SQL client (e.g., Azure Data Studio) to execute SQL scripts.

Git: For cloning the repository.

Eclipse IDE for Java & Enterprise Developers: Your development environment.

Database Setup
First, set up your database and tables in MS SQL Server:

Open SSMS and connect to your SQL Server instance.

Execute the following SQL script to create the StudentDB database and the Students, Courses, and Enrollments tables.

-- Use the existing database
USE StudentDB;
GO

-- Drop tables in reverse order of dependency to avoid foreign key constraints issues
IF OBJECT_ID('dbo.Enrollments', 'U') IS NOT NULL DROP TABLE dbo.Enrollments;
GO
IF OBJECT_ID('dbo.Students', 'U') IS NOT NULL DROP TABLE dbo.Students;
GO
IF OBJECT_ID('dbo.Courses', 'U') IS NOT NULL DROP TABLE dbo.Courses;
GO

-- Create the Students table
CREATE TABLE Students (
    student_id INT PRIMARY KEY IDENTITY(1,1),
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone VARCHAR(20),
    dob DATE,
    gender VARCHAR(10)
);
GO

-- Create the Courses table
CREATE TABLE Courses (
    course_id INT PRIMARY KEY IDENTITY(1,1),
    course_name VARCHAR(255) NOT NULL,
    duration VARCHAR(50)
);
GO

-- Create the Enrollments table
CREATE TABLE Enrollments (
    enrollment_id INT PRIMARY KEY IDENTITY(1,1),
    student_id INT NOT NULL,
    course_id INT NOT NULL,
    enroll_date DATE NOT NULL,
    FOREIGN KEY (student_id) REFERENCES Students(student_id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES Courses(course_id) ON DELETE CASCADE
);
GO

-- Optional: Insert some sample data
INSERT INTO Students (name, email, phone, dob, gender) VALUES ('Alice Smith', 'alice.s@example.com', '111-222-3333', '2000-01-15', 'Female');
INSERT INTO Students (name, email, phone, dob, gender) VALUES ('Bob Johnson', 'bob.j@example.com', '444-555-6666', '1999-03-20', 'Male');
INSERT INTO Courses (course_name, duration) VALUES ('Introduction to Java', '3 months');
INSERT INTO Courses (course_name, duration) VALUES ('Database Fundamentals', '2 months');
GO

Project Setup (Eclipse)
Clone the Repository:

git clone https://github.com/HemanthKumar9969/EduConnect-CLI.git

Import into Eclipse:

Open Eclipse.

Go to File > Import....

Select General > Existing Projects into Workspace.

Click Next.

Browse to the directory where you cloned EduConnect-CLI.

Ensure EduConnect-CLI is selected. Click Finish.

Add MS SQL JDBC Driver:

Download the latest Microsoft JDBC Driver for SQL Server (mssql-jdbc-X.X.X.jar).

In Eclipse's Package Explorer, right-click on the EduConnect-CLI project.

Go to Build Path > Configure Build Path....

Select the Libraries tab.

Click Add External JARs....

Navigate to the downloaded JDBC driver .jar file, select it, and click Open.

Click Apply and then Apply and Close.

Database Credentials Configuration
To connect to your database securely, you need to provide your credentials in a db.properties file:

Create db.properties:

In Eclipse, right-click on your EduConnect-CLI project (the top-level folder).

Select New > File.

Name it db.properties.

Click Finish.

Add your credentials:

Open the newly created db.properties file.

Add the following content, replacing the placeholder values with your actual SQL Server details:

db.url=jdbc:sqlserver://localhost:1433;databaseName=StudentDB;encrypt=true;trustServerCertificate=true;
db.user=YourSQLServerUsername
db.password=YourSQLServerPassword

IMPORTANT: This file is intentionally excluded from Git tracking via .gitignore to protect your sensitive credentials. Never commit db.properties to your public repository!

How to Run
Ensure Database Connection:

Before running the main application, you can test your database connection independently.

In Eclipse, navigate to src/com.EduConnect.util/DatabaseConnection.java.

Right-click DatabaseConnection.java > Run As > Java Application.

The console should display "Connection successful: true". If not, double-check your db.properties credentials and SQL Server status.

Run the Main Application:

In Eclipse, navigate to src/com.EduConnect.app/EduConnectApp.java.

Right-click EduConnectApp.java > Run As > Java Application.

The application will start in your Eclipse console.

Usage
The EduConnect-CLI application presents a simple, interactive console menu:

--- Welcome to EduConnect ---

Main Menu:
1. Students
2. Courses
3. Enrollments
4. Exit

Choice:

Navigate Menus: Enter the number corresponding to your choice and press Enter.

Student, Course, Enrollment Operations: Each main menu option leads to a sub-menu for performing CRUD operations on that entity.

Inputting Data: Follow the prompts to enter details. Dates should be in DD-MM-YYYY format.

Returning to Previous Menu: Each sub-menu has a "Back" option to return to the main menu.

Demo
(You can embed your animation video (e.g., GIF, MP4) here. For a GIF, simply add ![Demo](path/to/your/demo.gif))

Contributing
Feel free to fork this repository, submit issues, or propose pull requests.

License
This project is open-source. (Consider adding a specific license, e.g., MIT, Apache 2.0)
