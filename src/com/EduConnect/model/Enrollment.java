package com.EduConnect.model; // Declares this file belongs to the 'model' subpackage under 'com.EduConnect'

import java.sql.Date; // Imports java.sql.Date for handling date values

/**
 * Represents an Enrollment entity, linking a Student to a Course.
 * This class captures the relationship and the enrollment date.
 */
public class Enrollment {
    private int enrollmentId; // Unique ID for this enrollment record
    private int studentId;    // Foreign Key: References a student in the Students table
    private int courseId;     // Foreign Key: References a course in the Courses table
    private Date enrollDate;  // The date of enrollment

    /** Default constructor. */
    public Enrollment() {}

    /** Constructor for creating a NEW enrollment record. */
    public Enrollment(int studentId, int courseId, Date enrollDate) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollDate = enrollDate;
    }

    /** Constructor for retrieving an existing enrollment record. */
    public Enrollment(int enrollmentId, int studentId, int courseId, Date enrollDate) {
        this.enrollmentId = enrollmentId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollDate = enrollDate;
    }

    // Getters: To read enrollment attributes.
    public int getEnrollmentId() { return enrollmentId; }
    public int getStudentId() { return studentId; }
    public int getCourseId() { return courseId; }
    public Date getEnrollDate() { return enrollDate; }

    // Setters: To modify enrollment attributes.
    public void setEnrollmentId(int enrollmentId) { this.enrollmentId = enrollmentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }
    public void setEnrollDate(Date enrollDate) { this.enrollDate = enrollDate; }

    /** toString method for easy printing of Enrollment objects. */
    @Override
    public String toString() {
        return "Enrollment ID: " + enrollmentId + ", Student ID: " + studentId +
               ", Course ID: " + courseId + ", Date: " + enrollDate;
    }
}
