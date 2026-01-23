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

        


    }
}
