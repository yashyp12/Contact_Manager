package com.contactmanager.api;

import com.contactmanager.dao.ContactDAO;
import com.contactmanager.dao.ContactDAOImpl;
import com.contactmanager.service.ContactService;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;


/**
 * API Server - Starts Javalin web server
 * <p>
 * Interview Points:
 * - This creates HTTP server on port 7000
 * - Registers REST API endpoints
 * - Enables CORS for frontend access
 * - Serves static files (HTML, CSS, JS)
 */
public class ApiServer {
    public static void main(String[] args) {
        // Create dependencies (manual dependency injection)
        ContactDAO dao = new ContactDAOImpl();
        ContactService service = new ContactService(dao);
        ContactController controller = new ContactController(service);

        // Create Javalin app with configuration
        Javalin app = Javalin.create(config -> {
            // Enable CORS (Cross-Origin Resource Sharing)
            // Allows frontend on different port/domain to call our API

            config.plugins.enableCors(cors -> {
                cors.add(it -> {
                    it.anyHost(); //allow request from any origin
                });
            });

            // Serve static files from src/main/resources/public
            // This will serve our HTML, CSS, JS files
//            config.staticFiles.add("/public", Location.CLASSPATH);
        }).start(7000); //start server on 700 port

    //register api routes
        controller.registerRoutes(app);

         // Welcome message
        System.out.println("\n" + "=".repeat(60));
        System.out.println("   Contact Manager API Server Started!");
        System.out.println("=".repeat(60));
        System.out.println("  API Base URL: http://localhost:7000/api");
        System.out.println("  Frontend URL: http://localhost:7000");
        System.out.println("=".repeat(60));
        System.out.println("\n  Available Endpoints:");
        System.out.println("  GET    /api/contacts           - Get all contacts");
        System.out.println("  GET    /api/contacts/{id}      - Get contact by ID");
        System.out.println("  POST   /api/contacts           - Create new contact");
        System.out.println("  PUT    /api/contacts/{id}      - Update contact");
        System.out.println("  DELETE /api/contacts/{id}      - Delete contact");
        System.out.println("  GET    /api/contacts/search?name=xyz - Search");
        System.out.println("  GET    /api/contacts/count     - Get total count");
        System.out.println("\n" + "=".repeat(60));
        System.out.println("  Press Ctrl+C to stop server");
        System.out.println("=".repeat(60) + "\n");
    }
}

