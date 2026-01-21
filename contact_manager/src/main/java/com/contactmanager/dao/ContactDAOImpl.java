package com.contactmanager.dao;

import com.contactmanager.model.Contact;
import com.contactmanager.util.DatabaseConnection;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of ContactDAO interface
 * Contains actual JDBC code for database operations
 * <p>
 * Interview Point: This class demonstrates:
 * - PreparedStatement usage (prevents SQL injection)
 * - Try-with-resources (automatic resource management)
 * - ResultSet handling
 * - Transaction management
 * - Exception handling
 */


public class ContactDAOImpl implements ContactDAO {


    /**
     * Adds a new contact to database
     * Uses PreparedStatement to prevent SQL injection
     */

    @Override
    public boolean addContact(Contact contact) {
        String sql = "INSERT INTO contacts (first_name, last_name, phone, email,address) VALUES (?,?,?,?,?)";

        // Try-with-resources: Automatically closes Connection and PreparedStatement
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Index starts from 1 (not 0!)

            pstmt.setString(1, contact.getFirstName());
            pstmt.setString(2, contact.getLastName());
            pstmt.setString(3, contact.getPhone());
            pstmt.setString(4, contact.getEmail());
            pstmt.setString(5, contact.getAddress());

            // Execute INSERT query
            // executeUpdate() returns number of rows affected
            int rowsAffected = pstmt.executeUpdate();

            // If rowsAffected > 0, insertion successful
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("ERROR ADDING CONTACT " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Retrieves contact by ID
     * Demonstrates ResultSet handling
     */
    @Override
    public Contact getContactById(int id) {

        String sql = "SELECT * FROM contacts WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            // executeQuery() for SELECT statements
            // Returns ResultSet containing results
            ResultSet rs = pstmt.executeQuery();

            // ResultSet cursor initially before first row
            // next() moves to next row, returns true if row exists
            if (rs.next()) {
                // Extract data from ResultSet and create Contact object
                return extractContactFromResultSet(rs);
            }
            return null;
        } catch (SQLException e) {
            System.out.println("Error fetching contact by Id : " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Retrieves all contacts
     * Demonstrates looping through ResultSet
     */
    @Override
    public List<Contact> getAllContacts() {

        List<Contact> contacts = new ArrayList<>();
        String sql = "SELECT * FROM contacts ORDER BY id ASC";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // Loop through all rows
            // next() returns false when no more rows

            while (rs.next()) {
                Contact contact = extractContactFromResultSet(res);
                contacts.add(contact);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching all contacts: " + e.getMessage());
            e.printStackTrace();
        }
        return contacts;
    }



      /**
     * Searches contacts by name
     * Demonstrates LIKE query for partial matching
     */

    @Override
    public List<Contact> searchContactByName(String name) {

        List<Contact> contacts = new ArrayList<>();

        // ILIKE is case-insensitive LIKE in PostgreSQL
        // % is wildcard (matches any characters)

        String sql = "SELECT * FROM contacts WHERE first_name ILIKE ? OR last_name ILIKE ?";
        try(Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)){


            // Add wildcards for partial matching
            // "Am" becomes "%Am%"
String searchPattern = "%" + name + "%";
pstmt.setString(1,searchPattern);
pstmt.setString(2,searchPattern);

ResultSet rs = pstmt.executeQuery();

while (rs.next()){
    contacts.add(extractContactFromResultSet(rs));
}
        }catch (SQLException e){
            System.out.println("Errpr searching contacts " + e.getMessage());
            e.printStackTrace();
        }

        return contacts;
    }

    private Contact extractContactFromResultSet(ResultSet rs) {

        return new Contact();
    }

    @Override
    public boolean updateCotact(Contact contact) {
        return false;
    }

    @Override
    public boolean deleteContact(int id) {
        return false;
    }

    @Override
    public int getContactCount() {
        return 0;
    }


    /**
     * Retrieves contact by ID
     * Demonstrates ResultSet handling
     */


}
