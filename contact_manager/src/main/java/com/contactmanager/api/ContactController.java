package com.contactmanager.api;

import com.contactmanager.model.Contact;
import com.contactmanager.service.ContactService;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;


/**
 * REST API Controller for Contact operations
 * Handles HTTP requests and returns JSON responses
 * <p>
 * Interview Points:
 * - This is the API layer that exposes backend functionality to frontend
 * - Uses RESTful conventions (GET, POST, PUT, DELETE)
 * - Returns JSON for easy frontend consumption
 * - Delegates business logic to Service layer (separation of concerns)
 */

public class ContactController {

    private final ContactService contactService;

    /**
     * Constructor - receives ContactService via dependency injection
     */

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    /**
     * Registers all API routes with Javalin app
     *
     * @param app Javalin application instance
     *            <p>
     *            This method sets up REST endpoints
     *            Each endpoint maps to a specific HTTP method and URL path
     */


    public void registerRoutes(Javalin app) {
        // GET /api/contacts - Get all contacts
        app.get("/api/contacts", this::getAllContacts);

        // GET /api/contacts/{id} - Get contact by ID
        app.get("/api/contacts/{id}", this::getContactById);

        // POST /api/contacts - Create new contact
        app.get("/api/contacts/{id}", this::getContactById);

        // POST /api/contacts - Create new contact
        app.get("/api/contacts", this::createContact);

        // PUT /api/contacts/{id} - Update existing contact
        app.put("/api/contacts/{id}", this::updateContact);


        // DELETE /api/contacts/{id} - Delete contact
        app.delete("/api/contacts/{id}", this::deleteContact);

        // GET /api/contacts/search?name=xyz - Search contacts by name
        app.get("/api/contacts/search", this::searchContacts);

        // GET /api/contacts/count - Get total count
        app.get("/api/contacts/count", this::getContactCount);

    }


    /**
     * GET /api/contacts
     * Returns all contacts as JSON array
     * <p>
     * Example response:
     * [
     * {"id":1,"firstName":"Amit","lastName":"Sharma",...},
     * {"id":2,"firstName":"Priya","lastName":"Verma",...}
     * ]
     */

    private void getAllContacts(Context ctx) {

        try {

            List<Contact> contacts = contactService.getAllContacts();

            // Return 200 OK with JSON array
            ctx.json(contacts);
            ctx.status(200);

        } catch (Exception e) {
            // Return 500 Internal Server Error
            ctx.status(500);
            ctx.json(new ErrorResponse(" Failed to fetch contacts: " + e.getMessage()));
        }

    }

    /**
     * GET /api/contacts/{id}
     * Returns single contact by ID
     * <p>
     * Example: GET /api/contacts/1
     * Response: {"id":1,"firstName":"Amit",...}
     */

    private void getContactById(Context ctx) {
        try {
            // Extract ID from URL path parameter
            int id = Integer.parseInt(ctx.pathParam("id"));

            Contact contact = contactService.getContactById(id);

            if (contact != null) {
                // Return 200 OK with contact JSON
                ctx.json(contact);
                ctx.status(200);
            } else {
                //return 404
                ctx.status(404);
                ctx.json(new ErrorResponse("Contact not found with ID: " + id));

            }
        } catch (NumberFormatException e) {
            //return 404
            ctx.status(400);
            ctx.json(new ErrorResponse("Failed to fetch contact : " + e.getMessage()));

        }
    }

    /**
     * POST /api/contacts
     * Creates new contact from JSON request body
     * <p>
     * Example request body:
     * {
     * "firstName": "New",
     * "lastName": "User",
     * "phone": "9999999999",
     * "email": "new@example.com",
     * "address": "Mumbai"
     * }
     */

    private void createContact(Context ctx) {

        try {
            //parse json requst body to contact object
            Contact contact = ctx.bodyAsClass(Contact.class);

            //validate and add through service
            boolean success = contactService.addContact(contact);

            if (success) {
                ctx.status(201);
                ctx.json(new SuccessResponse("Contact created successfully "));

            } else {
                // return 400 bad requst
                ctx.status(400);
                ctx.json(new ErrorResponse("Failed to create  contact check validation errors  "));
            }
        } catch (Exception e) {
            ctx.status(500);
            ctx.json(new ErrorResponse("Failed to CREATE contact : " + e.getMessage()));
        }
    }

    /**
     * PUT /api/contacts/{id}
     * Updates existing contact
     * <p>
     * Example: PUT /api/contacts/1
     * Request body: {"id":1,"firstName":"Updated",...}
     */

    private void updateContact(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Contact contact = ctx.bodyAsClass(Contact.class);

            // Ensure ID in URL matches ID in body
            contact.setId(id);

            boolean success = contactService.updateContact(contact);

            if (success) {
                // Return 200 OK
                ctx.status(200);
                ctx.json(new SuccessResponse("Contact updated successfully"));
            } else {
                // Return 404 Not Found or 400 Bad Request
                ctx.status(404);
                ctx.json(new ErrorResponse("Contact not found or validation failed"));
            }

        } catch (NumberFormatException e) {
            ctx.status(400);
            ctx.json(new ErrorResponse("Invalid ID format"));
        } catch (Exception e) {
            ctx.status(500);
            ctx.json(new ErrorResponse("Failed to update contact: " + e.getMessage()));
        }
    }


    private void deleteContact(Context ctx) {
     try{
         int id = Integer.parseInt(ctx.pathParam("id"));

         boolean success = contactService.deleteContact(id);


            if (success) {
                // Return 200 OK
                ctx.status(200);
                ctx.json(new SuccessResponse("Contact deleted successfully"));
            } else {
                // Return 404 Not Found
                ctx.status(404);
                ctx.json(new ErrorResponse("Contact not found with ID: " + id));
            }
     }catch (NumberFormatException e){
         ctx.status(400);
         ctx.json(new ErrorResponse("Invalid ID format "));
     }catch (Exception e){
         ctx.status(500);
         ctx.json(new ErrorResponse("Failed to delete contact : " + e.getMessage()));
     }
    }

     /**
     * GET /api/contacts/search?name=xyz
     * Searches contacts by name
     *
     * Example: GET /api/contacts/search?name=Amit
     * Returns: Array of matching contacts
     */

     private void searchContacts(Context ctx){
         try{
             //extract query paramter
             String name = ctx.queryParam("name");

             if(name == null || name.trim().isEmpty()){
                 ctx.status(400);
                 ctx.json(new ErrorResponse("Search name is required "));
                 return;
             }

             List<Contact> results = contactService.searchContactByName(name);

             //return 200 ok with results
             ctx.json(results);
             ctx.status(200);
         }catch (Exception e){
             ctx.status(500);
             ctx.json(new ErrorResponse("Search Failed : " + e.getMessage()));
         }
     }


    /**
     * GET /api/contacts/count
     * Returns total number of contacts
     *
     * Response: {"count": 5}
     */


    private void getContactCount(Context ctx){
        try{
            int count = contactService.getContactCount();

            ctx.json(new CountResponse(count));
            ctx.status(200);
        }catch (Exception e){
            ctx.status(500);
            ctx.json(new ErrorResponse("Failed to get count : " + e.getMessage()));

        }
    }

        // ========== Response Classes (for consistent JSON format) ==========
    /**
     * Success response wrapper
     */

private static class SuccessResponse{
    private final String message;
    private final boolean success = true;

        public SuccessResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public boolean isSuccess(){
            return success;
        }
    }

    //error respone wrapper

    private static class ErrorResponse{
    private final String error;
    private final boolean success = false;

        public ErrorResponse(String error) {
            this.error = error;
        }

        public String getError() { return error;}
        public boolean isSuccess(){ return success;}

    }

     /**
     * Count response wrapper
     */
    private static class CountResponse {
        private final int count;

        public CountResponse(int count) {
            this.count = count;
        }

        public int getCount() { return count; }
    }



}






