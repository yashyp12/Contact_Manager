package com.contactmanager.model;

import java.sql.Timestamp;

/**
 * Model class representing a Contact entity
 * This class maps to the 'contacts' table in database
 * <p>
 * Interview Point: This is a POJO (Plain Old Java Object) used to transfer data
 * between different layers of the application
 */

public class Contact {

    // Private fields - encapsulation (data hiding)
    //all of these are the columns in rthe table
    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String address;
    private Timestamp createdAt;
    private Timestamp updatedAt;

//    def consatructor for creatinf empty objects


//    parameterized constructor for creating objects with data

    public Contact(int id, String firstName, String lastName, String phone, String email, String address, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

// Constructor without id (used when inserting new contacts)
    // Database auto-generates id (SERIAL PRIMARY KEY)
//for new contacts
    public Contact(String firstName, String lastName, String phone, String email, String address, Timestamp createdAt, Timestamp updatedAt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Contact(String firstName, String lastName, String phone, String email, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public Contact() {
        // for handling sample cases
    }

    // Getters and Setters
    // Getters: To READ data from private fields
    // Setters: To WRITE data to private fields
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdateAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    // toString() method - for printing object details
    // Very useful for debugging and displaying data
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", firstName=" + firstName + '\'' +
                ", lastName= " + lastName + '\'' +
                ", phone =" + phone + '\'' +
                " email =" + email + '\'' +
                "address " + address + '\'' +
                " ,createdAt " + createdAt + '\'' +
                ",updatedAt =" + updatedAt +
                '}';
    }

// Method to display contact in user-friendly format
    public String toDisplayString(){
        return String.format(
          "ID : %d | Name: %s %s | phone: %s | Email: %s  | Address: %s",
          id,firstName,(lastName!=null ? lastName  :""),
          phone,(email !=null ? email : "N/A"),
                (address !=null ? address : "N/A")
        );
    }

}
