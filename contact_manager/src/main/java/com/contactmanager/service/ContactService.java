package com.contactmanager.service;

import com.contactmanager.dao.ContactDAOImpl;
import com.contactmanager.dao.ContactDAO;
import com.contactmanager.model.Contact;

import java.util.List;


/**
 * Service layer for Contact operations
 * Contains business logic and validation
 * <p>
 * Interview Point: Service layer acts as intermediary between UI and DAO
 * - Validates input before database operations
 * - Handles business rules
 * - Can add logging, caching, transaction management
 * <p>
 * Why separate from DAO?
 * - DAO: "HOW to access data" (technical)
 * - Service: "WHAT to do with data" (business logic)
 */


public class ContactService {

    //dao instance for database operations
    private final ContactDAO contactDAO;

    /**
     * Constructor - initializes DAO
     * In real applications, this would use Dependency Injection
     */

    public ContactService(ContactDAO contactDAO) {
        this.contactDAO = contactDAO;
    }


    /**
     * Adds a new contact with validation
     *
     * @param contact Contact object to add
     * @return true if added successfully, false otherwise
     */

    public boolean addContact(Contact contact) {
        // Validation before database operation

        if (!isValidContact(contact)) {
            System.out.println("Validation Failed ! - contact data is invalid");
            return false;
        }

        //check for duplicate phone no
        if (isPhoneExists(contact.getPhone())) {
            System.out.println("phone number existss" + contact.getPhone());
            return false;
        }

        // All validations passed - proceed with database operation
        boolean result = contactDAO.addContact(contact);

        if(result){
            System.out.println("contact added succesfully: "+ contact.getFirstName());
        }else{
            System.out.println("failed to add contact to database");
        }

        return result;
    }

 /**
     * Retrieves contact by ID
     *
     * @param id Contact ID
     * @return Contact object if found, null otherwise
     */

 public Contact getContactById(int id){
     if(id<=0){
         System.out.println("Invaldi ID : Must be poistive num");
         return null;
     }

     Contact contact = contactDAO.getContactById(id);

     if(contact == null){
         System.out.println("no contact found with id " + id);
     }

     return contact;
 }

   /**
     * Retrieves all contacts
     *
     * @return List of all contacts
     */

   public List<Contact> getAllContacts(){
       List<Contact> contacts = contactDAO.getAllContacts();

       if(contacts.isEmpty()){
           System.out.println("no contacts found in database");
       }else{
           System.out.println("retrieved " + contacts.size() + "contacts ");
       }
       return contacts;
   }

   /**
     * Searches contacts by name
     *
     * @param name Name to search (can be partial)
     * @return List of matching contacts
     */

   


    private boolean isValidContact(Contact contact) {
    }


}
