package com.schoolmanagement.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/oop-project";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "ransilu5718";

    // Singleton instance of the connection
    private static Connection connection = null;

    // Load MySQL JDBC Driver (only once when the class is loaded)
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        }
    }

    // Private constructor to prevent instantiation
    private DatabaseConnection() {}

    // Method to get a connection to the database (Singleton)
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            synchronized (DatabaseConnection.class) {
                if (connection == null || connection.isClosed()) {
                    connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                }
            }
        }
        return connection;
    }
}
