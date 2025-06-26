package com.EduConnect.model;

import java.sql.Date;

public class Student {
    private int studentId;
    private String name;
    private String email;
    private String phone;
    private Date dob;
    private String gender;

    public Student() {}

    public Student(String name, String email, String phone, Date dob, String gender) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.gender = gender;
    }

    public Student(int studentId, String name, String email, String phone, Date dob, String gender) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.gender = gender;
    }

    public int getStudentId() { return studentId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public Date getDob() { return dob; }
    public String getGender() { return gender; }

    public void setStudentId(int studentId) { this.studentId = studentId; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setDob(Date dob) { this.dob = dob; }
    public void setGender(String gender) { this.gender = gender; }

    @Override
    public String toString() {
        return "ID: " + studentId + ", Name: " + name + ", Email: " + email +
               ", Phone: " + phone + ", DOB: " + dob + ", Gender: " + gender;
    }
}

