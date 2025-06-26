package com.EduConnect.util; // Declares this file belongs to the 'util' subpackage under 'com.EduConnect'

import java.sql.Connection;    // Imports the Connection interface for database connections
import java.sql.DriverManager; // Imports DriverManager to get a connection to the database
import java.sql.SQLException;  // Imports SQLException to handle database-related errors

/**
 * Utility class to manage the database connection for the EduConnect application.
 * This class provides a static method to obtain a connection to the MS SQL Server database.
 */
public class DatabaseConnection {

    // private static final String: Declares a constant string, visible only within this class.
    // DB_URL: The connection string (URL) that tells JDBC how to connect to the database.
    // "jdbc:sqlserver://localhost:1433": Protocol (jdbc), database type (sqlserver), server address (localhost), port (1433).
    // "databaseName=StudentDB": Specifies the name of the database to connect to.
    // "encrypt=true;trustServerCertificate=true;": Common parameters for local SQL Server setups,
    //   `encrypt=true` means data is encrypted, `trustServerCertificate=true` bypasses certificate validation (good for dev, not prod).
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=StudentDB;encrypt=true;trustServerCertificate=true;";

    // USER: The username for logging into the SQL Server. You MUST change this.
    private static final String USER = "your username"; // Example: "sa"

    // PASS: The password for the SQL Server user. You MUST change this.
    private static final String PASS = "your password"; // Example: "StrongP@ssw0rd!"

    /**
     * Public static method to get a database connection.
     * `public`: Accessible from any other class.
     * `static`: Belongs to the class itself, not an object instance, so you call it like `DatabaseConnection.getConnection()`.
     * `Connection`: Return type is a JDBC Connection object.
     * `throws SQLException`: Indicates that this method might throw an SQLException, requiring calling code to handle it.
     */
    public static Connection getConnection() throws SQLException {
        // DriverManager.getConnection(): Attempts to establish a connection to the given database URL.
        // It uses the provided username and password for authentication.
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    /**
     * Main method to test the database connection independently.
     * This allows you to run this file directly in Eclipse to check if your database credentials are correct.
     */
    public static void main(String[] args) {
        // try-with-resources: Ensures that the 'conn' (Connection) object is automatically closed
        // when the try block finishes, even if an error occurs, preventing resource leaks.
        try (Connection conn = getConnection()) {
            // Checks if the connection object is successfully created (not null).
            if (conn != null) {
                System.out.println("Connection successful: " + (conn != null)); // Prints success message.
            } else {
                System.out.println("Failed to get a database connection."); // Should not happen if getConnection doesn't throw.
            }
        } catch (SQLException e) { // Catches any database errors that occur during connection.
            System.err.println("Connection error: " + e.getMessage()); // Prints error message to the error stream.
        }
    }
}
