package com.EduConnect.dao;

import com.EduConnect.model.Student;
import com.EduConnect.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private static final String INSERT_SQL = "INSERT INTO Students (name, email, phone, dob, gender) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_SQL = "SELECT student_id, name, email, phone, dob, gender FROM Students";
    private static final String SELECT_BY_ID_SQL = "SELECT student_id, name, email, phone, dob, gender FROM Students WHERE student_id = ?";
    private static final String UPDATE_SQL = "UPDATE Students SET name = ?, email = ?, phone = ?, dob = ?, gender = ? WHERE student_id = ?";
    private static final String DELETE_SQL = "DELETE FROM Students WHERE student_id = ?";

    public boolean addStudent(Student s) {
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {
            ps.setString(1, s.getName()); ps.setString(2, s.getEmail()); ps.setString(3, s.getPhone());
            ps.setDate(4, s.getDob()); ps.setString(5, s.getGender());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { System.err.println("Add Student Error: " + e.getMessage()); return false; }
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(SELECT_ALL_SQL); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                students.add(new Student(rs.getInt("student_id"), rs.getString("name"), rs.getString("email"),
                                        rs.getString("phone"), rs.getDate("dob"), rs.getString("gender")));
            }
        } catch (SQLException e) { System.err.println("Get All Students Error: " + e.getMessage()); }
        return students;
    }

    public Student getStudentById(int id) {
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID_SQL)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Student(rs.getInt("student_id"), rs.getString("name"), rs.getString("email"),
                                       rs.getString("phone"), rs.getDate("dob"), rs.getString("gender"));
                }
            }
        } catch (SQLException e) { System.err.println("Get Student By ID Error: " + e.getMessage()); }
        return null;
    }

    public boolean updateStudent(Student s) {
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)) {
            ps.setString(1, s.getName()); ps.setString(2, s.getEmail()); ps.setString(3, s.getPhone());
            ps.setDate(4, s.getDob()); ps.setString(5, s.getGender()); ps.setInt(6, s.getStudentId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { System.err.println("Update Student Error: " + e.getMessage()); return false; }
    }

    public boolean deleteStudent(int id) {
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(DELETE_SQL)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { System.err.println("Delete Student Error: " + e.getMessage()); return false; }
    }
}

