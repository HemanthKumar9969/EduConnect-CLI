package com.EduConnect.model;

import java.sql.Date;

public class Enrollment {
    private int enrollmentId;
    private int studentId;
    private int courseId;
    private Date enrollDate;

    public Enrollment() {}

    public Enrollment(int studentId, int courseId, Date enrollDate) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollDate = enrollDate;
    }

    public Enrollment(int enrollmentId, int studentId, int courseId, Date enrollDate) {
        this.enrollmentId = enrollmentId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollDate = enrollDate;
    }

    public int getEnrollmentId() { return enrollmentId; }
    public int getStudentId() { return studentId; }
    public int getCourseId() { return courseId; }
    public Date getEnrollDate() { return enrollDate; }

    public void setEnrollmentId(int enrollmentId) { this.enrollmentId = enrollmentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }
    public void setEnrollDate(Date enrollDate) { this.enrollDate = enrollDate; }

    @Override
    public String toString() {
        return "Enrollment ID: " + enrollmentId + ", Student ID: " + studentId +
               ", Course ID: " + courseId + ", Date: " + enrollDate;
    }
}

