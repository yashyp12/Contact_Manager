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

public List<Contact> searchContactByName(String name) {
    if (name == null || name.trim().isEmpty()) {
        System.out.println("search name cannot be empty");
        return List.of();//return the empty list
    }


    List<Contact> results = contactDAO.searchContactByName(name.trim());

    if (results.isEmpty()) {
        System.out.println("no contact found matching " + name);
    } else {
        System.out.println("found " + results.size() + "contact matching " + name);
    }
    return results;
}
      /**
     * Updates existing contact with validation
     *
     * @param contact Contact with updated data
     * @return true if updated successfully, false otherwise
     */

      public boolean updateContact(Contact contact) {
          if (contact.getId() <= 0) {
              System.out.println("invalid contact id ");
              return false;
          }

          //check if exists
          Contact existingContact = contactDAO.getContactById(contact.getId());
          if (existingContact == null) {
              System.out.println("contact not found with id " + contact.getId());
              return false;
          }

          // Validate new data
          if (!isValidContact(contact)) {
              System.out.println("❌ Validation failed: Updated contact data is invalid");
              return false;
          }

          //perform update
          boolean result = contactDAO.updateContact(contact);

          if (result) {
              System.out.println("contact updated successfully " + contact.getFirstName());

          } else {
              System.out.println("failed to update");
          }

          return result;
      }

       /**
     * Deletes contact by ID
     *
     * @param id Contact ID to delete
     * @return true if deleted successfully, false otherwise
     */

       public boolean deleteContact(int id ){
           if(id<=0){
               System.out.println("invalid id : must be positive ");
           return false;
           }

           //check if contact exists before deleting
           Contact contact = contactDAO.getContactById(id);
           if(contact == null){
               System.out.println("cannot delte :contact not found ");
           return false;
           }

                   // Confirm deletion (in real app, this would be in UI layer)
boolean result = contactDAO.deleteContact(id);

           if(result){
               System.out.println("contact delted succes " + contact.getFirstName());
           }else{
               System.out.println("failed to delete");
           }
           return result;
}



    /**
     * Gets total count of contacts
     *
     * @return Total number of contacts
     */
    public int getContactCount() {
        return contactDAO.getContactCount();
    }



 /**
     * Validates contact data
     * Ensures required fields are present and valid
     *
     * @param contact Contact to validate
     * @return true if valid, false otherwise
     */
    private boolean isValidContact(Contact contact) {

        if(contact == null){
            System.out.println("validation error : contact object is null");
        return false;
        }

        //validate first name
        if(contact.getFirstName()==null || contact.getFirstName().trim().isEmpty()){
            System.out.println("validation error : first name is required");
        return false;
        }

        if(contact.getFirstName().length()>50){
            System.out.println("validation error : First Name too long ");
            return false;
        }

        //validate phone number
        if(contact.getPhone() == null || contact.getPhone().trim().isEmpty()){
            System.out.println("validation erro phone number is required");
            return false;
        }


        //phone should be numeric
        String phone = contact.getPhone().replaceAll("[^0-9]","");//remove non digit
        if(phone.length()<10 || phone.length()>15){
            System.out.println("validation error : phone number must be in 10-15 digits");
            return false;
        }

        //validate email format
        if(contact.getEmail() !=null && !contact.getEmail().isEmpty()){
            if(!isValidEmail(contact.getEmail())){
                System.out.println("validatin eror: invalid email format ");
                return false;
            }
        }

        return true;
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email.matches(emailRegex);
    }


    /**
     * Checks if phone number already exists in database
     *
     * @param phone Phone number to check
     * @return true if exists, false otherwise
     */

    private boolean isPhoneExists(String phone){
        List<Contact> allContacts = contactDAO.getAllContacts();

        for(Contact contact:allContacts){
            if(contact.getPhone().equals(phone)){
                return true;
            }
        }

        return false;
    }

      /**
     * Displays all contacts in formatted way
     * Utility method for UI layer
     */

      public void displayAllContacts(){
          List<Contact>contacts = getAllContacts();

          if(contacts.isEmpty()){
              System.out.println("\n No contacts to display \n");
              return;
          }

          System.out.println("\n" + "=".repeat(100));
          System.out.println("             All CONTACTS");
          System.out.println("=".repeat(100));

          for(Contact contact:contacts){
              System.out.println(contact.toDisplayString());
          }

          System.out.println("=".repeat(100));
          System.out.println("Total contacts " + contacts.size());
          System.out.println("=".repeat(100)+"\n");
      }




}
