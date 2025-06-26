package com.EduConnect.dao; // Declares this file belongs to the 'dao' subpackage under 'com.EduConnect'

import com.EduConnect.model.Course;           // Imports the Course model class
import com.EduConnect.util.DatabaseConnection; // Imports the utility for database connection
import java.sql.*;                             // Imports all classes from java.sql
import java.util.ArrayList;                    // Imports ArrayList
import java.util.List;                         // Imports List interface

/**
 * Data Access Object (DAO) for Course entities.
 * Provides basic CRUD operations to interact with the 'Courses' table.
 */
public class CourseDAO {
    // SQL query constants for Course CRUD operations.
    private static final String INSERT_SQL = "INSERT INTO Courses (course_name, duration) VALUES (?, ?)";
    private static final String SELECT_ALL_SQL = "SELECT course_id, course_name, duration FROM Courses";
    private static final String SELECT_BY_ID_SQL = "SELECT course_id, course_name, duration FROM Courses WHERE course_id = ?";
    private static final String UPDATE_SQL = "UPDATE Courses SET course_name = ?, duration = ? WHERE course_id = ?";
    private static final String DELETE_SQL = "DELETE FROM Courses WHERE course_id = ?";

    /** Adds a new course record (CREATE). */
    public boolean addCourse(Course c) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {
            ps.setString(1, c.getCourseName());
            ps.setString(2, c.getDuration());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { System.err.println("Add Course Error: " + e.getMessage()); return false; }
    }

    /** Retrieves all course records (READ ALL). */
    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL_SQL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                courses.add(new Course(rs.getInt("course_id"), rs.getString("course_name"), rs.getString("duration")));
            }
        } catch (SQLException e) { System.err.println("Get All Courses Error: " + e.getMessage()); }
        return courses;
    }

    /** Retrieves a single course by ID (READ BY ID). */
    public Course getCourseById(int id) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID_SQL)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Course(rs.getInt("course_id"), rs.getString("course_name"), rs.getString("duration"));
                }
            }
        } catch (SQLException e) { System.err.println("Get Course By ID Error: " + e.getMessage()); }
        return null;
    }

    /** Updates an existing course record (UPDATE). */
    public boolean updateCourse(Course c) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)) {
            ps.setString(1, c.getCourseName());
            ps.setString(2, c.getDuration());
            ps.setInt(3, c.getCourseId()); // ID for WHERE clause.
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { System.err.println("Update Course Error: " + e.getMessage()); return false; }
    }

    /** Deletes a course record by ID (DELETE). */
    public boolean deleteCourse(int id) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_SQL)) {
            ps.setInt(1, id); // ID for WHERE clause.
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { System.err.println("Delete Course Error: " + e.getMessage()); return false; }
    }
}
