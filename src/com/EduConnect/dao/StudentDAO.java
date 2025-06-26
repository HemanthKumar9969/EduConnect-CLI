package com.EduConnect.dao; // Declares this file belongs to the 'dao' subpackage under 'com.EduConnect'

import com.EduConnect.model.Student;         // Imports the Student model class
import com.EduConnect.util.DatabaseConnection; // Imports the utility for database connection
import java.sql.*;                           // Imports all classes from java.sql (Connection, PreparedStatement, ResultSet, SQLException)
import java.util.ArrayList;                  // Imports ArrayList for dynamic lists
import java.util.List;                       // Imports List interface

/**
 * Data Access Object (DAO) for Student entities.
 * Provides basic CRUD operations to interact with the 'Students' table.
 */
public class StudentDAO {
    // SQL query constants for each CRUD operation. Using PreparedStatement placeholders ('?')
    // is crucial for security (prevents SQL injection) and performance.
    private static final String INSERT_SQL = "INSERT INTO Students (name, email, phone, dob, gender) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_SQL = "SELECT student_id, name, email, phone, dob, gender FROM Students";
    private static final String SELECT_BY_ID_SQL = "SELECT student_id, name, email, phone, dob, gender FROM Students WHERE student_id = ?";
    private static final String UPDATE_SQL = "UPDATE Students SET name = ?, email = ?, phone = ?, dob = ?, gender = ? WHERE student_id = ?";
    private static final String DELETE_SQL = "DELETE FROM Students WHERE student_id = ?";

    /**
     * Adds a new student record to the database (CREATE operation).
     * @param s The Student object to add.
     * @return true if successful, false otherwise.
     */
    public boolean addStudent(Student s) {
        // try-with-resources: Ensures resources (Connection, PreparedStatement) are closed automatically.
        try (Connection conn = DatabaseConnection.getConnection(); // Get a database connection.
             PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) { // Prepare the SQL INSERT statement.
            ps.setString(1, s.getName());    // Set the 1st '?' to student's name.
            ps.setString(2, s.getEmail());   // Set the 2nd '?' to student's email.
            ps.setString(3, s.getPhone());   // Set the 3rd '?' to student's phone.
            ps.setDate(4, s.getDob());       // Set the 4th '?' to student's DOB (SQL Date type).
            ps.setString(5, s.getGender());  // Set the 5th '?' to student's gender.
            return ps.executeUpdate() > 0;   // Execute the UPDATE (for INSERT/UPDATE/DELETE) and check if any rows were affected.
        } catch (SQLException e) { // Catch any database errors.
            System.err.println("Add Student Error: " + e.getMessage()); // Print a simplified error message.
            // e.printStackTrace(); // Uncomment for full debugging stack trace.
            return false;
        }
    }

    /**
     * Retrieves all student records from the database (READ ALL operation).
     * @return A List of Student objects. Returns an empty list if no students or an error occurs.
     */
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>(); // Initialize an empty list to store students.
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL_SQL); // Prepare the SQL SELECT ALL statement.
             ResultSet rs = ps.executeQuery()) { // Execute the query (for SELECT) and get the results.
            while (rs.next()) { // Loop through each row in the ResultSet.
                // Retrieve data from current row using column names.
                int id = rs.getInt("student_id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                Date dob = rs.getDate("dob");
                String gender = rs.getString("gender");
                students.add(new Student(id, name, email, phone, dob, gender)); // Create and add a new Student object to the list.
            }
        } catch (SQLException e) {
            System.err.println("Get All Students Error: " + e.getMessage());
        }
        return students;
    }

    /**
     * Retrieves a single student by ID (READ BY ID operation).
     * @param id The ID of the student to retrieve.
     * @return The Student object if found, null otherwise.
     */
    public Student getStudentById(int id) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID_SQL)) {
            ps.setInt(1, id); // Set the ID parameter for the WHERE clause.
            try (ResultSet rs = ps.executeQuery()) { // Execute and get results.
                if (rs.next()) { // If a record is found (resultSet has a next row).
                    return new Student(rs.getInt("student_id"), rs.getString("name"), rs.getString("email"),
                                       rs.getString("phone"), rs.getDate("dob"), rs.getString("gender"));
                }
            }
        } catch (SQLException e) { System.err.println("Get Student By ID Error: " + e.getMessage()); }
        return null; // Return null if no student found or error occurs.
    }

    /**
     * Updates an existing student record (UPDATE operation).
     * @param s The Student object with updated details and the existing ID.
     * @return true if successful, false otherwise.
     */
    public boolean updateStudent(Student s) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)) {
            ps.setString(1, s.getName());
            ps.setString(2, s.getEmail());
            ps.setString(3, s.getPhone());
            ps.setDate(4, s.getDob());
            ps.setString(5, s.getGender());
            ps.setInt(6, s.getStudentId()); // This ID is used in the WHERE clause to specify which student to update.
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { System.err.println("Update Student Error: " + e.getMessage()); return false; }
    }

    /**
     * Deletes a student record by ID (DELETE operation).
     * @param id The ID of the student to delete.
     * @return true if successful, false otherwise.
     */
    public boolean deleteStudent(int id) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_SQL)) {
            ps.setInt(1, id); // Set the ID parameter for the WHERE clause.
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { System.err.println("Delete Student Error: " + e.getMessage()); return false; }
    }
}
