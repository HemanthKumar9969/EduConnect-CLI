package com.EduConnect.dao; // Declares this file belongs to the 'dao' subpackage under 'com.EduConnect'

import com.EduConnect.model.Enrollment;       // Imports the Enrollment model class
import com.EduConnect.util.DatabaseConnection; // Imports the utility for database connection
import java.sql.*;                             // Imports all classes from java.sql
import java.util.ArrayList;                    // Imports ArrayList
import java.util.List;                         // Imports List interface

/**
 * Data Access Object (DAO) for Enrollment entities.
 * Provides basic CRUD operations to interact with the 'Enrollments' table.
 */
public class EnrollmentDAO {
    // SQL query constants for Enrollment CRUD and specialized READ operations.
    private static final String INSERT_SQL = "INSERT INTO Enrollments (student_id, course_id, enroll_date) VALUES (?, ?, ?)";
    private static final String SELECT_ALL_SQL = "SELECT enrollment_id, student_id, course_id, enroll_date FROM Enrollments";
    private static final String SELECT_BY_ID_SQL = "SELECT enrollment_id, student_id, course_id, enroll_date FROM Enrollments WHERE enrollment_id = ?";
    private static final String SELECT_BY_STUDENT_ID_SQL = "SELECT enrollment_id, student_id, course_id, enroll_date FROM Enrollments WHERE student_id = ?";
    private static final String SELECT_BY_COURSE_ID_SQL = "SELECT enrollment_id, student_id, course_id, enroll_date FROM Enrollments WHERE course_id = ?";
    private static final String UPDATE_SQL = "UPDATE Enrollments SET student_id = ?, course_id = ?, enroll_date = ? WHERE enrollment_id = ?";
    private static final String DELETE_SQL = "DELETE FROM Enrollments WHERE enrollment_id = ?";

    /** Adds a new enrollment record (CREATE). */
    public boolean addEnrollment(Enrollment e) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {
            ps.setInt(1, e.getStudentId());
            ps.setInt(2, e.getCourseId());
            ps.setDate(3, e.getEnrollDate());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) { System.err.println("Add Enrollment Error: " + ex.getMessage()); return false; }
    }

    /** Retrieves all enrollment records (READ ALL). */
    public List<Enrollment> getAllEnrollments() {
        List<Enrollment> enrollments = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL_SQL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                enrollments.add(new Enrollment(rs.getInt("enrollment_id"), rs.getInt("student_id"),
                                               rs.getInt("course_id"), rs.getDate("enroll_date")));
            }
        } catch (SQLException e) { System.err.println("Get All Enrollments Error: " + e.getMessage()); }
        return enrollments;
    }

    /** Retrieves a single enrollment by ID (READ BY ID). */
    public Enrollment getEnrollmentById(int id) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID_SQL)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Enrollment(rs.getInt("enrollment_id"), rs.getInt("student_id"),
                                          rs.getInt("course_id"), rs.getDate("enroll_date"));
                }
            }
        } catch (SQLException e) { System.err.println("Get Enrollment By ID Error: " + e.getMessage()); }
        return null;
    }

    /** Retrieves all enrollments for a specific student ID (READ by Student ID). */
    public List<Enrollment> getEnrollmentsByStudentId(int studentId) {
        List<Enrollment> enrollments = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BY_STUDENT_ID_SQL)) {
            ps.setInt(1, studentId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    enrollments.add(new Enrollment(rs.getInt("enrollment_id"), rs.getInt("student_id"),
                                                   rs.getInt("course_id"), rs.getDate("enroll_date")));
                }
            }
        } catch (SQLException e) { System.err.println("Get Enrollments By Student ID Error: " + e.getMessage()); }
        return enrollments;
    }

    /** Retrieves all enrollments for a specific course ID (READ by Course ID). */
    public List<Enrollment> getEnrollmentsByCourseId(int courseId) {
        List<Enrollment> enrollments = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BY_COURSE_ID_SQL)) {
            ps.setInt(1, courseId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    enrollments.add(new Enrollment(rs.getInt("enrollment_id"), rs.getInt("student_id"),
                                                   rs.getInt("course_id"), rs.getDate("enroll_date")));
                }
            }
        } catch (SQLException e) { System.err.println("Get Enrollments By Course ID Error: " + e.getMessage()); }
        return enrollments;
    }

    /** Updates an existing enrollment record (UPDATE). */
    public boolean updateEnrollment(Enrollment e) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)) {
            ps.setInt(1, e.getStudentId());
            ps.setInt(2, e.getCourseId());
            ps.setDate(3, e.getEnrollDate());
            ps.setInt(4, e.getEnrollmentId());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) { System.err.println("Update Enrollment Error: " + ex.getMessage()); return false; }
    }

    /** Deletes an enrollment record by ID (DELETE). */
    public boolean deleteEnrollment(int id) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_SQL)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { System.err.println("Delete Enrollment Error: " + e.getMessage()); return false; }
    }
}
