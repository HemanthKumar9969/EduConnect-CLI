package com.EduConnect.model; // Declares this file belongs to the 'model' subpackage under 'com.EduConnect'

/**
 * Represents a Course entity in the EduConnect application.
 * Encapsulates course data (ID, name, duration).
 */
public class Course {
    private int courseId;
    private String courseName;
    private String duration;

    /** Default constructor. */
    public Course() {}

    /** Constructor for creating a NEW course (ID assigned by DB). */
    public Course(String courseName, String duration) {
        this.courseName = courseName;
        this.duration = duration;
    }

    /** Constructor for retrieving an existing course (ID read from DB). */
    public Course(int courseId, String courseName, String duration) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.duration = duration;
    }

    // Getters: To read course attributes.
    public int getCourseId() { return courseId; }
    public String getCourseName() { return courseName; }
    public String getDuration() { return duration; }

    // Setters: To modify course attributes.
    public void setCourseId(int courseId) { this.courseId = courseId; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public void setDuration(String duration) { this.duration = duration; }

    /** toString method for easy printing of Course objects. */
    @Override
    public String toString() {
        return "ID: " + courseId + ", Name: " + courseName + ", Duration: " + duration;
    }
}
