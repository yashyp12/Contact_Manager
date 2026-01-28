package com.contactmanager;

import com.contactmanager.dao.ContactDAO;
import com.contactmanager.dao.ContactDAOImpl;
import com.contactmanager.model.Contact;
import com.contactmanager.service.ContactService;

import java.util.List;

/**
 * Test class for Service layer
 * Tests business logic and validation
 */
public class ServiceTest {

    public static void main(String[] args) {
        ContactDAO dao = new ContactDAOImpl();
        ContactService service = new ContactService(dao);
        System.out.println("===========================================");
        System.out.println("    TESTING SERVICE LAYER");
        System.out.println("===========================================\n");

        // TEST 1: Add valid contact
        System.out.println("TEST 1: Adding valid contact...");
        Contact validContact = new Contact("Ravi", "Kumar", "9191919191",
                "ravi@example.com", "Chennai, India");
        service.addContact(validContact);

        System.out.println("\n-------------------------------------------\n");

        // TEST 2: Add contact with invalid data (empty name)
        System.out.println("TEST 2: Adding contact with empty name (should fail)...");
        Contact invalidContact1 = new Contact("", "Test", "8888888888",
                "test@example.com", "Mumbai");
        service.addContact(invalidContact1);

        System.out.println("\n-------------------------------------------\n");

        // TEST 3: Add contact with invalid phone
        System.out.println("TEST 3: Adding contact with invalid phone (should fail)...");
        Contact invalidContact2 = new Contact("Test", "User", "123",
                "test@example.com", "Mumbai");
        service.addContact(invalidContact2);

        System.out.println("\n-------------------------------------------\n");

        // TEST 4: Add contact with duplicate phone
        System.out.println("TEST 4: Adding contact with duplicate phone (should fail)...");
        Contact duplicateContact = new Contact("Duplicate", "User", "9191919191",
                "duplicate@example.com", "Delhi");
        service.addContact(duplicateContact);

        System.out.println("\n-------------------------------------------\n");

        // TEST 5: Search contacts
        System.out.println("TEST 5: Searching for 'Ravi'...");
        service.searchContactByName("Ravi");

        System.out.println("\n-------------------------------------------\n");

        // TEST 6: Display all contacts
        System.out.println("TEST 6: Displaying all contacts...");
        service.displayAllContacts();

        System.out.println("\n-------------------------------------------\n");

        // TEST 7: Get contact count
        System.out.println("TEST 7: Getting total contact count...");
        int count = service.getContactCount();
        System.out.println("Total contacts in database: " + count);

        System.out.println("\n===========================================");
        System.out.println("    SERVICE LAYER TESTS COMPLETED!");
        System.out.println("===========================================");
    }
}