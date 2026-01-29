package com.contactmanager.api;
import com.contactmanager.model.Contact;
import com.contactmanager.service.ContactService;
import io.javalin.Javalin;
import io.javalin.http.Context;
import java.util.List;



/**
 * REST API Controller for Contact operations
 * Handles HTTP requests and returns JSON responses
 *
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
     *
     * This method sets up REST endpoints
     * Each endpoint maps to a specific HTTP method and URL path
     */


    public void registerRoutes(Javalin app){
                // GET /api/contacts - Get all contacts
        app.get("/api/contacts",this::getAllContacts);

        // GET /api/contacts/{id} - Get contact by ID
        app.get("/api/contacts/{id}",this::getContactById);

                // POST /api/contacts - Create new contact
        app.get("/api/contacts/{id}",this::getContactById);

        // POST /api/contacts - Create new contact
        app.get("/api/contacts",this::createContact);

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
     *
     * Example response:
     * [
     *   {"id":1,"firstName":"Amit","lastName":"Sharma",...},
     *   {"id":2,"firstName":"Priya","lastName":"Verma",...}
     * ]
     */

private void getAllContacts(Context ctx){

    
}


}
