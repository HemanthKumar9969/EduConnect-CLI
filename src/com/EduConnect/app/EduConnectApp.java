package com.EduConnect.app; // Declares this file belongs to the 'app' subpackage under 'com.EduConnect'

// Import all necessary classes from other EduConnect packages (models, DAOs, util)
import com.EduConnect.dao.CourseDAO;
import com.EduConnect.dao.EnrollmentDAO;
import com.EduConnect.dao.StudentDAO;
import com.EduConnect.model.Course;
import com.EduConnect.model.Enrollment;
import com.EduConnect.model.Student;
import com.EduConnect.util.DatabaseConnection; // Explicitly import DatabaseConnection.
import java.sql.Date;         // For SQL Date objects.
import java.text.SimpleDateFormat; // For formatting/parsing dates.
import java.util.List;        // For handling lists of objects.
import java.util.Scanner;     // For reading user input from the console.

/**
 * Main application class for EduConnect.
 * Provides a console-based menu system for managing Students, Courses, and Enrollments.
 */
public class EduConnectApp {
    // Create instances of DAOs to interact with respective database tables.
    private static StudentDAO sDao = new StudentDAO();
    private static CourseDAO cDao = new CourseDAO();
    private static EnrollmentDAO eDao = new EnrollmentDAO();

    // Scanner for reading input from the console.
    private static Scanner sc = new Scanner(System.in);

    // Date format for parsing/formatting dates (DD-MM-YYYY as requested).
    private static final SimpleDateFormat DF = new SimpleDateFormat("dd-MM-yyyy");

    /**
     * The main entry point of the application.
     * @param args Command line arguments (not used here).
     */
    public static void main(String[] args) {
        System.out.println("--- Welcome to EduConnect ---"); // Welcome message.
        int ch; // Variable to store user's menu choice.
        do { // Main application loop, continues until user chooses to exit.
            System.out.println("\nMain Menu:\n1. Students\n2. Courses\n3. Enrollments\n4. Exit"); // Display main menu.
            System.out.print("\nChoice: "); // Prompt for user choice, with a leading newline for cursor positioning.
            ch = getInt(); // Get integer input from user using a helper method.
            switch (ch) { // Use switch statement to handle different menu choices.
                case 1: handleStudentOps(); break;    // Call method to handle student-related operations.
                case 2: handleCourseOps(); break;     // Call method to handle course-related operations.
                case 3: handleEnrollmentOps(); break; // Call method to handle enrollment-related operations.
                case 4: System.out.println("Exiting EduConnect!"); break; // Exit message.
                default: System.out.println("Invalid option."); break; // Handle invalid choices.
            }
        } while (ch != 4); // Loop continues as long as choice is not 4 (Exit).
        sc.close(); // Close the scanner to release system resources.
    }

    /**
     * Helper method to get a valid integer input from the user.
     * Handles non-numeric input gracefully.
     * @return The integer entered by the user.
     */
    private static int getInt() {
        while (!sc.hasNextInt()) { // Loop while the next input is not an integer.
            System.out.print("Enter number: "); // Prompt for correct input.
            sc.next(); sc.nextLine(); // Consume the invalid token and the rest of the line.
        }
        int i = sc.nextInt(); // Read the integer.
        sc.nextLine(); // Consume the leftover newline character after reading the int. IMPORTANT!
        return i; // Return the valid integer.
    }

    /**
     * Helper method to get a valid date input from the user in "DD-MM-YYYY" format.
     * @param p The prompt message to display.
     * @return A java.sql.Date object if input is valid, null otherwise.
     */
    private static Date getDate(String p) {
        System.out.print(p); // Display the prompt.
        try { // Try to parse the date string.
            // Parse the input string into a java.util.Date, then convert to java.sql.Date.
            return new Date(DF.parse(sc.nextLine()).getTime());
        } catch (Exception e) { // Catch any parsing errors.
            System.out.println("Invalid date (DD-MM-YYYY)."); // Inform user of invalid format.
            return null; // Return null to indicate failure.
        }
    }

    // --- Student Operations: Provides menu and methods for Student CRUD ---
    private static void handleStudentOps() {
        int ch;
        do {
            System.out.println("\nStudent Menu:\n1. Add\n2. View All\n3. View By ID\n4. Update\n5. Delete\n6. Back");
            System.out.print("\nChoice: "); // Prompt with cursor fix.
            ch = getInt();
            switch (ch) {
                case 1: addStudent(); break;
                case 2: sDao.getAllStudents().forEach(System.out::println); break; // Fetch and print all students.
                case 3: viewStudentById(); break;
                case 4: updateStudent(); break;
                case 5: deleteStudent(); break;
                case 6: break; // Go back to main menu.
                default: System.out.println("Invalid option.");
            }
        } while (ch != 6);
    }

    private static void addStudent() {
        System.out.print("Enter Name: "); String n = sc.nextLine();
        System.out.print("Enter Email: "); String e = sc.nextLine();
        System.out.print("Enter Phone: "); String p = sc.nextLine();
        Date d = getDate("Enter DOB (DD-MM-YYYY): "); // Get date of birth.
        System.out.print("Enter Gender: "); String g = sc.nextLine();
        // Call DAO to add student and print success/failure message.
        System.out.println(sDao.addStudent(new Student(n, e, p, d, g)) ? "Added." : "Failed.");
    }

    private static void viewStudentById() {
        System.out.print("ID: "); int id = getInt();
        Student s = sDao.getStudentById(id); // Fetch student by ID.
        System.out.println(s != null ? s : "Not found."); // Print student or "Not found".
    }

    private static void updateStudent() {
        System.out.print("ID to update: "); int id = getInt();
        Student s = sDao.getStudentById(id); // Get existing student.
        if (s == null) { System.out.println("Not found."); return; } // If not found, exit.
        System.out.print("New Name (blank): "); String n = sc.nextLine(); if(!n.isEmpty()) s.setName(n); // Update name if provided.
        System.out.println(sDao.updateStudent(s) ? "Updated." : "Failed."); // Call DAO to update.
    }

    private static void deleteStudent() {
        System.out.print("ID to delete: "); int id = getInt();
        System.out.println(sDao.deleteStudent(id) ? "Deleted." : "Failed."); // Call DAO to delete.
    }

    // --- Course Operations: Provides menu and methods for Course CRUD ---
    private static void handleCourseOps() {
        int ch;
        do {
            System.out.println("\nCourse Menu:\n1. Add\n2. View All\n3. View By ID\n4. Update\n5. Delete\n6. Back");
            System.out.print("\nChoice: "); // Prompt with cursor fix.
            ch = getInt();
            switch (ch) {
                case 1: addCourse(); break;
                case 2: cDao.getAllCourses().forEach(System.out::println); break; // Fetch and print all courses.
                case 3: viewCourseById(); break;
                case 4: updateCourse(); break;
                case 5: deleteCourse(); break;
                case 6: break;
                default: System.out.println("Invalid option.");
            }
        } while (ch != 6);
    }

    private static void addCourse() {
        System.out.print("Enter Course Name: "); String n = sc.nextLine();
        System.out.print("Enter Duration: "); String d = sc.nextLine();
        System.out.println(cDao.addCourse(new Course(n, d)) ? "Added." : "Failed.");
    }

    private static void viewCourseById() {
        System.out.print("ID: "); int id = getInt(); Course c = cDao.getCourseById(id);
        System.out.println(c != null ? c : "Not found.");
    }

    private static void updateCourse() {
        System.out.print("ID to update: "); int id = getInt(); Course c = cDao.getCourseById(id);
        if (c == null) { System.out.println("Not found."); return; }
        System.out.print("New Name (blank): "); String n = sc.nextLine(); if(!n.isEmpty()) c.setCourseName(n);
        System.out.println(cDao.updateCourse(c) ? "Updated." : "Failed.");
    }

    private static void deleteCourse() {
        System.out.print("ID to delete: "); int id = getInt();
        System.out.println(cDao.deleteCourse(id) ? "Deleted." : "Failed.");
    }

    // --- Enrollment Operations: Provides menu and methods for Enrollment CRUD ---
    private static void handleEnrollmentOps() {
        int ch;
        do {
            System.out.println("\nEnrollment Menu:\n1. Add\n2. View All\n3. View By ID\n4. View By Student ID\n5. View By Course ID\n6. Update\n7. Delete\n8. Back");
            System.out.print("\nChoice: "); // Prompt with cursor fix.
            ch = getInt();
            switch (ch) {
                case 1: addEnrollment(); break;
                case 2: eDao.getAllEnrollments().forEach(System.out::println); break;
                case 3: viewEnrollmentById(); break;
                case 4: viewEnrollmentsByStudentId(); break;
                case 5: viewEnrollmentsByCourseId(); break;
                case 6: updateEnrollment(); break;
                case 7: deleteEnrollment(); break;
                case 8: break;
                default: System.out.println("Invalid option.");
            }
        } while (ch != 8);
    }

    private static void addEnrollment() {
        System.out.print("Student ID: "); int sId = getInt();
        System.out.print("Course ID: "); int cId = getInt();
        Date d = getDate("Enrollment Date (DD-MM-YYYY): ");
        if (d == null) return; // Exit if date is invalid.
        // Check if student and course exist before enrolling.
        if (sDao.getStudentById(sId) == null) { System.out.println("Student not found."); return; }
        if (cDao.getCourseById(cId) == null) { System.out.println("Course not found."); return; }
        System.out.println(eDao.addEnrollment(new Enrollment(sId, cId, d)) ? "Added." : "Failed.");
    }

    private static void viewEnrollmentById() {
        System.out.print("ID: "); int id = getInt(); Enrollment e = eDao.getEnrollmentById(id);
        System.out.println(e != null ? e : "Not found.");
    }

    private static void viewEnrollmentsByStudentId() {
        System.out.print("Student ID: "); int sId = getInt();
        eDao.getEnrollmentsByStudentId(sId).forEach(System.out::println);
    }

    private static void viewEnrollmentsByCourseId() {
        System.out.print("Course ID: "); int cId = getInt();
        eDao.getEnrollmentsByCourseId(cId).forEach(System.out::println);
    }

    private static void updateEnrollment() {
        System.out.print("ID to update: "); int id = getInt(); Enrollment e = eDao.getEnrollmentById(id);
        if (e == null) { System.out.println("Not found."); return; }
        System.out.print("New Student ID (blank): "); String sIdStr = sc.nextLine();
        if (!sIdStr.isEmpty()) { int newSId = Integer.parseInt(sIdStr); if (sDao.getStudentById(newSId) != null) e.setStudentId(newSId); else System.out.println("Student not found. ID kept."); }
        System.out.print("New Course ID (blank): "); String cIdStr = sc.nextLine();
        if (!cIdStr.isEmpty()) { int newCId = Integer.parseInt(cIdStr); if (cDao.getCourseById(newCId) != null) e.setCourseId(newCId); else System.out.println("Course not found. ID kept."); }
        Date d = getDate("New Enrollment Date (DD-MM-YYYY, blank): "); if (d != null) e.setEnrollDate(d);
        System.out.println(eDao.updateEnrollment(e) ? "Updated." : "Failed.");
    }

    private static void deleteEnrollment() {
        System.out.print("ID to delete: "); int id = getInt();
        System.out.println(eDao.deleteEnrollment(id) ? "Deleted." : "Failed.");
    }
}
