package com.contactmanager.dao;
import com.contactmanager.model.Contact;
import java.util.List;


/**
 * DAO Interface for Contact entity
 * Defines all database operations for contacts
 *
 * Interview Point: Using interface allows multiple implementations
 * (PostgreSQL, MySQL, MongoDB, etc.) without changing business logic
 *
 * This follows the Dependency Inversion Principle (SOLID principles)
 */

public interface ContactDAO {


    /**
     * Inserts a new contact into database
     *
     * @param contact Contact object with data to insert
     * @return true if insertion successful, false otherwise
     *
     * Real-time use: When user adds new contact from UI
     */

    boolean addContact(Contact contact);


    /**
     * Retrieves a contact by ID
     *
     * @param id Unique identifier of contact
     * @return Contact object if found, null otherwise
     *
     * Real-time use: When user searches contact by ID
     */

    Contact getContactById(int id);


    /**
     * Retrieves all contacts from database
     *
     * @return List of all contacts, empty list if none found
     *
     * Real-time use: Display all contacts, generate reports
     */

    List<Contact> getAllContacts();


      /**
     * Searches contacts by name (first or last name)
     * Uses LIKE query for partial matching
     *
     * @param name Name to search (can be partial)
     * @return List of matching contacts
     *
     * Real-time use: Search functionality in UI
     */

      List<Contact> searchContactByName(String name);
  /**
     * Updates existing contact details
     *
     * @param contact Contact object with updated data (must have valid id)
     * @return true if update successful, false otherwise
     *
     * Real-time use: When user edits contact information
     */


  boolean updateContact(Contact contact);

   /**
     * Deletes a contact by ID
     *
     * @param id Unique identifier of contact to delete
     * @return true if deletion successful, false otherwise
     *
     * Real-time use: When user removes a contact
     */

   boolean deleteContact(int id);

     /**
     * Counts total number of contacts
     *
     * @return Total count of contacts in database
     *
     * Real-time use: Dashboard statistics, pagination
     */

     int getContactCount();



}
