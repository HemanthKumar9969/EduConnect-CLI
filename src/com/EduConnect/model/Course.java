package com.EduConnect.model;

public class Course {
    private int courseId;
    private String courseName;
    private String duration;

    public Course() {}

    public Course(String courseName, String duration) {
        this.courseName = courseName;
        this.duration = duration;
    }

    public Course(int courseId, String courseName, String duration) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.duration = duration;
    }

    public int getCourseId() { return courseId; }
    public String getCourseName() { return courseName; }
    public String getDuration() { return duration; }

    public void setCourseId(int courseId) { this.courseId = courseId; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public void setDuration(String duration) { this.duration = duration; }

    @Override
    public String toString() {
        return "ID: " + courseId + ", Name: " + courseName + ", Duration: " + duration;
    }
}

