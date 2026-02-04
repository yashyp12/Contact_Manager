package com.contactmanager.api;

import com.contactmanager.dao.ContactDAO;
import com.contactmanager.dao.ContactDAOImpl;
import com.contactmanager.service.ContactService;
import com.contactmanager.util.DatabaseConnection;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * API Server - Starts Javalin web server
 */
public class ApiServer {

    /**
     * Initialize database - create table if not exists
     */
    private static void initializeDatabase() {
        String createTableSQL = """
            CREATE TABLE IF NOT EXISTS contacts (
                id SERIAL PRIMARY KEY,
                first_name VARCHAR(50) NOT NULL,
                last_name VARCHAR(50),
                phone VARCHAR(15) NOT NULL,
                email VARCHAR(100) UNIQUE,
                address TEXT,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            );
            
            CREATE INDEX IF NOT EXISTS idx_contacts_name ON contacts(first_name, last_name);
            CREATE INDEX IF NOT EXISTS idx_contacts_phone ON contacts(phone);
            """;

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(createTableSQL);
            System.out.println("✓ Database table created/verified!");

            // Check if table is empty, insert sample data
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM contacts");
            if (rs.next() && rs.getInt(1) == 0) {
                String insertSQL = """
                    INSERT INTO contacts (first_name, last_name, phone, email, address) 
                    VALUES 
                        ('Amit', 'Sharma', '9876543210', 'amit.sharma@example.com', 'Delhi, India'),
                        ('Priya', 'Verma', '8765432109', 'priya.verma@example.com', 'Mumbai, India'),
                        ('Rahul', 'Kumar', '7654321098', 'rahul.kumar@example.com', 'Bangalore, India')
                    """;
                stmt.execute(insertSQL);
                System.out.println("✓ Sample data inserted!");
            } else {
                System.out.println("✓ Existing data found, skipping sample insert");
            }

        } catch (SQLException e) {
            System.err.println("⚠️ Error initializing database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Initialize database first
        System.out.println("Initializing database...");
        initializeDatabase();

        // Create dependencies
        ContactDAO dao = new ContactDAOImpl();
        ContactService service = new ContactService(dao);
        ContactController controller = new ContactController(service);

        // Get port from environment or use 7000 for local
        int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "7000"));

        // Create and configure Javalin app
        Javalin app = Javalin.create(config -> {
            config.plugins.enableCors(cors -> {
                cors.add(it -> it.anyHost());
            });
            config.staticFiles.add("/public", Location.CLASSPATH);
        }).start(port);

        // Register API routes
        controller.registerRoutes(app);

        // Display startup message
        System.out.println("\n" + "=".repeat(60));
        System.out.println("  🚀 Contact Manager Started!");
        System.out.println("=".repeat(60));
        System.out.println("  🌐 Server: http://localhost:" + port);
        System.out.println("  🔌 API: http://localhost:" + port + "/api");
        System.out.println("=".repeat(60) + "\n");
    }
}