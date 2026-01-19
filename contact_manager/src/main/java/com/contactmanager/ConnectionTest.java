package com.contactmanager;
import com.contactmanager.util.DatabaseConnection;


public class ConnectionTest {


    public static void main(String[] args) {
          System.out.println("===========================================");
        System.out.println("    DATABASE CONNECTION TEST");
        System.out.println("===========================================\n");

        boolean isConnected = DatabaseConnection.testConnection();

          System.out.println("\n===========================================");
        if (isConnected) {
            System.out.println(" SUCCESS! Database is ready to use ");
        } else {
            System.out.println("FAILED! Check your configuration ");
            System.out.println("\nTroubleshooting:");
            System.out.println("1. Check if PostgreSQL is running");
            System.out.println("2. Verify database.properties has correct password");
            System.out.println("3. Ensure contact_manager database exists");
        }
        System.out.println("===========================================");
    }

}
