package com.EduConnect.util;

import java.sql.Connection;
import java.sql.DriverManager; 
import java.sql.SQLException; 


public class DatabaseConnection {

  
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=StudentDB;encrypt=true;trustServerCertificate=true;";

    private static final String USER = "Your Username";

    private static final String PASS = "Your Password";

    public static Connection getConnection() throws SQLException {
     
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public static void main(String[] args) {
     
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("Connection successful: " + (conn != null)); 
            } else {
                System.out.println("Failed to get a database connection."); 
            }
        } catch (SQLException e) { 
            System.err.println("Connection error: " + e.getMessage()); 
        }
    }
}
