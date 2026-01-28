package com.contactmanager.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.Properties;

/**
 * Utility class for managing database connections
 * Implements Singleton pattern (only one instance exists)
 */
public class DatabaseConnection {
    // Database connection parameters
    private static String DB_URL;
    private static String DB_USERNAME;
    private static String DB_PASSWORD;
    private static String DB_DRIVER;


    // Static block - runs when class is loaded (before any method call)
    static {
        loadDatabaseProperties();
    }

    /**
     * Loads database configuration from database.properties file
     * This method reads the properties file from resources folder
     */

    private static void loadDatabaseProperties() {
        Properties properties = new Properties();

        // Try-with-resources: Automatically closes InputStream after use
        try (InputStream input = DatabaseConnection.class
                .getClassLoader().getResourceAsStream("database.properties")) {
            if (input == null) {
                System.err.println("ERROR: Unable to find database.properties file!");
                System.err.println("Make sure database.properties exists in src/main/resources/");
                return;
            }

            //LOAD PROPERTIES FROM FILE
            properties.load(input);

            //read val from the proeprties file - it accepts the key and return null if not found
            DB_URL = properties.getProperty("db.url");
            DB_USERNAME = properties.getProperty("db.username");
            DB_PASSWORD = properties.getProperty("db.password");
            DB_DRIVER = properties.getProperty("db.driver");

//    load jdbc driver class THROWS CLASS NOT FOUND EXCEPTION
            Class.forName(DB_DRIVER);

            System.out.println("✓ Database configuration loaded successfully!");


        } catch (IOException E) {
            System.err.println("ERROR: Failed to load database.properties");
        } catch (ClassNotFoundException e) {
            System.err.println("ERROR: PostgreSQL JDBC Driver not found!");
            System.err.println("Make sure postgresql dependency is in pom.xml");
            e.printStackTrace();
        }
    }

    /**
     * Creates and returns a new database connection
     *
     * @return Connection object connected to PostgreSQL database
     * @throws SQLException if connection fails
     *                      <p>
     *                      Interview Point: Each DAO method will call this to get a connection,
     *                      then close it in finally block or try-with-resources
     */

    public static Connection getConnection() throws SQLException {
        if (DB_URL == null || DB_USERNAME == null || DB_PASSWORD == null) {
            throw new SQLException("Database configuration not loaded properly!");
        }

//         create and return connection
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

    }

    /**
     * Tests database connection
     * Useful for debugging connection issues
     *
     * @return true if connection successful, false otherwise
     */

    public static boolean testConnection() {
        try (Connection connection = getConnection()) {
            if (connection != null && !connection.isClosed()) {
                System.out.println("DATABASE CONNECTION SUCCESS");
                System.out.println("Connected to " + DB_URL);
                return true;
            }
        } catch (SQLException e) {
            System.out.println("database connection failed ");
            System.out.println("Error " + e.getMessage());
            return false;
        }
        return false;
    }

    /**
     * Utility method to close database resources safely
     * Prevents resource leaks
     *
     * @param connection Connection to close
     */

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("connection closed");
            } catch (SQLException E) {
                System.out.println("error closing the connection " + E.getMessage());
            }
        }
    }

}
