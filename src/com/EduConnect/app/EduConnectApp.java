package com.EduConnect.app;

import com.EduConnect.dao.CourseDAO;
import com.EduConnect.dao.EnrollmentDAO;
import com.EduConnect.dao.StudentDAO;
import com.EduConnect.model.Course;
import com.EduConnect.model.Enrollment;
import com.EduConnect.model.Student;
import com.EduConnect.util.DatabaseConnection;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class EduConnectApp {
    private static StudentDAO sDao = new StudentDAO();
    private static CourseDAO cDao = new CourseDAO();
    private static EnrollmentDAO eDao = new EnrollmentDAO();

    private static Scanner sc = new Scanner(System.in);

    private static final SimpleDateFormat DF = new SimpleDateFormat("dd-MM-yyyy");

    public static void main(String[] args) {
        System.out.println("--- EduConnect ---");
        int ch;
        do {
            System.out.println("\nMain Menu:\n1. Students\n2. Courses\n3. Enrollments\n4. Exit");
            System.out.print("\nChoice: ");
            ch = getInt();
            switch (ch) {
                case 1: handleStudentOps(); break;
                case 2: handleCourseOps(); break;
                case 3: handleEnrollmentOps(); break;
                case 4: System.out.println("Exiting EduConnect!"); break;
                default: System.out.println("Invalid option.");
            }
        } while (ch != 4);
        sc.close();
    }

    private static int getInt() {
        while (!sc.hasNextInt()) {
            System.out.print("Enter number: ");
            sc.next(); sc.nextLine();
        }
        int i = sc.nextInt();
        sc.nextLine();
        return i;
    }

    private static Date getDate(String p) {
        String dateString;
        Date parsedDate = null;
        boolean isValidDate = false;

        do {
            System.out.print(p);
            dateString = sc.nextLine().trim();

            if (dateString.isEmpty()) {
                parsedDate = null;
                isValidDate = true;
            } else {
                try {
                    parsedDate = new Date(DF.parse(dateString).getTime());
                    isValidDate = true;
                } catch (Exception e) {
                    System.out.println("Invalid date (DD-MM-YYYY). Please try again or press Enter to skip.");
                    isValidDate = false;
                }
            }
        } while (!isValidDate);

        return parsedDate;
    }

    private static void handleStudentOps() {
        int ch;
        do {
            System.out.println("\nStudent Menu:\n1. Add\n2. View All\n3. View By ID\n4. Update\n5. Delete\n6. Back");
            System.out.print("\nChoice: ");
            ch = getInt();
            switch (ch) {
                case 1: addStudent(); break;
                case 2: sDao.getAllStudents().forEach(System.out::println); break;
                case 3: viewStudentById(); break;
                case 4: updateStudent(); break;
                case 5: deleteStudent(); break;
                case 6: break;
                default: System.out.println("Invalid option.");
            }
        } while (ch != 6);
    }

    private static void addStudent() {
        System.out.print("Enter Name: "); String n = sc.nextLine();
        String e;
        do {
            System.out.print("Enter Email: "); e = sc.nextLine().trim();
            if (e.isEmpty() || e.equalsIgnoreCase("blank")) {
                System.out.println("Email cannot be empty or 'blank'. Please enter a valid email.");
            } else {
                break;
            }
        } while (true);
        System.out.print("Enter Phone: "); String p = sc.nextLine();
        Date d = getDate("Enter DOB (DD-MM-YYYY): ");
        System.out.print("Enter Gender: "); String g = sc.nextLine();
        System.out.println(sDao.addStudent(new Student(n, e, p, d, g)) ? "Added." : "Failed.");
    }

    private static void viewStudentById() {
        System.out.print("ID: "); int id = getInt();
        Student s = sDao.getStudentById(id);
        System.out.println(s != null ? s : "Not found.");
    }

    private static void updateStudent() {
        System.out.print("ID to update: "); int id = getInt();
        Student s = sDao.getStudentById(id);
        if (s == null) { System.out.println("Not found."); return; }

        System.out.println("Current Details: " + s);

        System.out.print("New Name (press Enter to keep current '" + s.getName() + "'): ");
        String n = sc.nextLine().trim();
        if (!n.isEmpty()) {
            if (n.equalsIgnoreCase("blank")) {
                System.out.println("Keeping current Name: '" + s.getName() + "'");
            } else {
                s.setName(n);
            }
        }

        String emInput;
        do {
            System.out.print("New Email (press Enter to keep current '" + s.getEmail() + "'): ");
            emInput = sc.nextLine().trim();
            if (emInput.isEmpty()) {
                break;
            } else if (emInput.equalsIgnoreCase("blank")) {
                System.out.println("Email cannot be 'blank'. Please enter a valid email or just press Enter.");
            } else {
                s.setEmail(emInput);
                break;
            }
        } while (true);


        System.out.print("New Phone (press Enter to keep current '" + s.getPhone() + "'): ");
        String p = sc.nextLine().trim();
        if (!p.isEmpty()) {
            if (p.equalsIgnoreCase("blank")) {
                System.out.println("Keeping current Phone: '" + s.getPhone() + "'");
            } else {
                s.setPhone(p);
            }
        }

        Date d = getDate("New DOB (DD-MM-YYYY, press Enter to keep current '" + s.getDob() + "'): ");
        if (d != null) s.setDob(d);

        System.out.print("New Gender (press Enter to keep current '" + s.getGender() + "'): ");
        String g = sc.nextLine().trim();
        if (!g.isEmpty()) {
            if (g.equalsIgnoreCase("blank")) {
                System.out.println("Keeping current Gender: '" + s.getGender() + "'");
            } else {
                s.setGender(g);
            }
        }

        System.out.println(sDao.updateStudent(s) ? "Updated." : "Failed.");
    }

    private static void deleteStudent() {
        System.out.print("ID to delete: "); int id = getInt();
        System.out.println(sDao.deleteStudent(id) ? "Deleted." : "Failed.");
    }

    private static void handleCourseOps() {
        int ch;
        do {
            System.out.println("\nCourse Menu:\n1. Add\n2. View All\n3. View By ID\n4. Update\n5. Delete\n6. Back");
            System.out.print("\nChoice: ");
            ch = getInt();
            switch (ch) {
                case 1: addCourse(); break;
                case 2: cDao.getAllCourses().forEach(System.out::println); break;
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
        System.out.print("ID to update: "); int id = getInt();
        Course c = cDao.getCourseById(id);
        if (c == null) { System.out.println("Not found."); return; }

        System.out.println("Current Details: " + c);

        System.out.print("New Course Name (press Enter to keep current '" + c.getCourseName() + "'): ");
        String n = sc.nextLine().trim();
        if (!n.isEmpty()) {
            if (n.equalsIgnoreCase("blank")) {
                System.out.println("Keeping current Course Name: '" + c.getCourseName() + "'");
            } else {
                c.setCourseName(n);
            }
        }

        System.out.print("New Duration (press Enter to keep current '" + c.getDuration() + "'): ");
        String d = sc.nextLine().trim();
        if (!d.isEmpty()) {
            if (d.equalsIgnoreCase("blank")) {
                System.out.println("Keeping current Duration: '" + c.getDuration() + "'");
            } else {
                c.setDuration(d);
            }
        }

        System.out.println(cDao.updateCourse(c) ? "Updated." : "Failed.");
    }

    private static void deleteCourse() {
        System.out.print("ID to delete: "); int id = getInt();
        System.out.println(cDao.deleteCourse(id) ? "Deleted." : "Failed.");
    }

    private static void handleEnrollmentOps() {
        int ch;
        do {
            System.out.println("\nEnrollment Menu:\n1. Add\n2. View All\n3. View By ID\n4. View By Student ID\n5. View By Course ID\n6. Update\n7. Delete\n8. Back");
            System.out.print("\nChoice: ");
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
        if (d == null) return;
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
