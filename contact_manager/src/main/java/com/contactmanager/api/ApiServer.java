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

        int port = Integer.parseInt(System.getenv().getOrDefault("PORT","7000"));



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
            config.staticFiles.add("/public", Location.CLASSPATH);
        }).start(port); //start server on 700 port // using dynamic port

    //register api routes
        controller.registerRoutes(app);

         // Welcome message
       System.out.println("\n" + "=".repeat(60));
    System.out.println("  🚀 Contact Manager Started!");
    System.out.println("=".repeat(60));
    System.out.println("  🌐 Server: http://localhost:" + port);
    System.out.println("  🔌 API: http://localhost:" + port + "/api");
    System.out.println("=".repeat(60) + "\n");
    }
}

