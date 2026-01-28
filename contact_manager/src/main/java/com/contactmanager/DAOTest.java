package com.contactmanager;

import com.contactmanager.dao.ContactDAO;
import com.contactmanager.dao.ContactDAOImpl;
import com.contactmanager.model.Contact;

import java.util.List;

/**
 * Test class to verify DAO operations
 * Tests all CRUD functionality
 */
public class DAOTest {

    public static void main(String[] args) {
        // Create DAO instance
        ContactDAO dao = new ContactDAOImpl();

        System.out.println("===========================================");
        System.out.println("    TESTING CRUD OPERATIONS");
        System.out.println("===========================================\n");

        // TEST 1: Get all contacts
        System.out.println("TEST 1: Fetching all existing contacts...");
        List<Contact> allContacts = dao.getAllContacts();
        System.out.println("Found " + allContacts.size() + " contacts:");
        for (Contact c : allContacts) {
            System.out.println("  - " + c.toDisplayString());
        }

        System.out.println("\n-------------------------------------------\n");

        // TEST 2: Add new contact
        System.out.println("TEST 2: Adding new contact...");
        Contact newContact = new Contact("Yash", "Patil", "999999999", "yash@gmail.com", "Pune, India");
        boolean added = dao.addContact(newContact);
        System.out.println(added ? "✓ Contact added successfully!" : "✗ Failed to add contact");

        System.out.println("\n-------------------------------------------\n");

        // TEST 3: Search by name
        System.out.println("TEST 3: Searching contacts with name 'Yash'...");
        List<Contact> searchResults = dao.searchContactByName("Yash");
        System.out.println("Found " + searchResults.size() + " match(es):");
        for (Contact c : searchResults) {
            System.out.println("  - " + c.toDisplayString());
        }

        System.out.println("\n-------------------------------------------\n");

        // TEST 4: Get contact by ID
        System.out.println("TEST 4: Fetching contact with ID 1...");
        Contact contact = dao.getContactById(1);
        if (contact != null) {
            System.out.println("✓ Found: " + contact.toDisplayString());
        } else {
            System.out.println("✗ No contact found with ID 1");
        }

        System.out.println("\n-------------------------------------------\n");

        // TEST 5: Update contact
        System.out.println("TEST 5: Updating contact ID 1...");
        if (contact != null) {
            contact.setPhone("111111111");
            contact.setAddress("Updated Aadhar Address, India");
            boolean updated = dao.updateContact(contact);  // Fixed typo
            System.out.println(updated ? "✓ Contact updated successfully!" : "✗ Failed to update");

            // Fetch again to verify
            Contact updatedContact = dao.getContactById(1);
            System.out.println("After update: " + updatedContact.toDisplayString());
        }

        System.out.println("\n-------------------------------------------\n");

        // TEST 6: Count contacts
        System.out.println("TEST 6: Counting total contacts...");
        int count = dao.getContactCount();
        System.out.println("Total contacts in database: " + count);

        System.out.println("\n-------------------------------------------\n");

        System.out.println("\n===========================================");
        System.out.println("    ALL TESTS COMPLETED!");
        System.out.println("===========================================");
    }
}
