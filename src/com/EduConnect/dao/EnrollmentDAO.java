package com.EduConnect.dao;

import com.EduConnect.model.Enrollment;
import com.EduConnect.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAO {
    private static final String INSERT_SQL = "INSERT INTO Enrollments (student_id, course_id, enroll_date) VALUES (?, ?, ?)";
    private static final String SELECT_ALL_SQL = "SELECT enrollment_id, student_id, course_id, enroll_date FROM Enrollments";
    private static final String SELECT_BY_ID_SQL = "SELECT enrollment_id, student_id, course_id, enroll_date FROM Enrollments WHERE enrollment_id = ?";
    private static final String SELECT_BY_STUDENT_ID_SQL = "SELECT enrollment_id, student_id, course_id, enroll_date FROM Enrollments WHERE student_id = ?";
    private static final String SELECT_BY_COURSE_ID_SQL = "SELECT enrollment_id, student_id, course_id, enroll_date FROM Enrollments WHERE course_id = ?";
    private static final String UPDATE_SQL = "UPDATE Enrollments SET student_id = ?, course_id = ?, enroll_date = ? WHERE enrollment_id = ?";
    private static final String DELETE_SQL = "DELETE FROM Enrollments WHERE enrollment_id = ?";

    public boolean addEnrollment(Enrollment e) {
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {
            ps.setInt(1, e.getStudentId()); ps.setInt(2, e.getCourseId()); ps.setDate(3, e.getEnrollDate());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) { System.err.println("Add Enrollment Error: " + ex.getMessage()); return false; }
    }

    public List<Enrollment> getAllEnrollments() {
        List<Enrollment> enrollments = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(SELECT_ALL_SQL); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                enrollments.add(new Enrollment(rs.getInt("enrollment_id"), rs.getInt("student_id"),
                                               rs.getInt("course_id"), rs.getDate("enroll_date")));
            }
        } catch (SQLException e) { System.err.println("Get All Enrollments Error: " + e.getMessage()); }
        return enrollments;
    }

    public Enrollment getEnrollmentById(int id) {
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID_SQL)) {
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

    public List<Enrollment> getEnrollmentsByStudentId(int studentId) {
        List<Enrollment> enrollments = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(SELECT_BY_STUDENT_ID_SQL)) {
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

    public List<Enrollment> getEnrollmentsByCourseId(int courseId) {
        List<Enrollment> enrollments = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(SELECT_BY_COURSE_ID_SQL)) {
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

    public boolean updateEnrollment(Enrollment e) {
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)) {
            ps.setInt(1, e.getStudentId()); ps.setInt(2, e.getCourseId()); ps.setDate(3, e.getEnrollDate());
            ps.setInt(4, e.getEnrollmentId());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) { System.err.println("Update Enrollment Error: " + ex.getMessage()); return false; }
    }

    public boolean deleteEnrollment(int id) {
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(DELETE_SQL)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { System.err.println("Delete Enrollment Error: " + e.getMessage()); return false; }
    }
}
