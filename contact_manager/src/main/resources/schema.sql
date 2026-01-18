-- Drop table if exists (for fresh start)
DROP TABLE IF EXISTS contacts;

-- Create contacts table
CREATE TABLE contacts (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50),
    phone VARCHAR(15) NOT NULL,
    email VARCHAR(100) UNIQUE,
    address TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create indexes for faster search
CREATE INDEX idx_contacts_name ON contacts(first_name, last_name);
CREATE INDEX idx_contacts_phone ON contacts(phone);

-- Insert sample test data
INSERT INTO contacts (first_name, last_name, phone, email, address)
VALUES
    ('Amit', 'Sharma', '9876543210', 'amit.sharma@example.com', 'Delhi, India'),
    ('Priya', 'Verma', '8765432109', 'priya.verma@example.com', 'Mumbai, India'),
    ('Rahul', 'Kumar', '7654321098', 'rahul.kumar@example.com', 'Bangalore, India');

-- Verify data
SELECT * FROM contacts;