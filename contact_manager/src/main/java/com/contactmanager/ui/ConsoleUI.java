package com.contactmanager.ui;

import com.contactmanager.dao.ContactDAO;
import com.contactmanager.dao.ContactDAOImpl;
import com.contactmanager.model.Contact;
import com.contactmanager.service.ContactService;

import java.util.List;
import java.util.Scanner;

public class ConsoleUI {

    private final ContactService contactService;
    private final Scanner scanner;

 public ConsoleUI(ContactService contactService) {
    this.contactService = contactService;
    this.scanner = new Scanner(System.in);
}



    /**
     * Main entry point - starts the application
     */

    public void start() {
        displayWelcomeBanner();

        boolean running = true;

        while (running) {
            displayMenu();
            int choise = getIntInput("Enter your choise ");

            System.out.println();

            switch (choise) {
                case 1:
                    addNewContact();
                    break;
                case 2:
                    viewAllContacts();
                    break;
                case 3:
                    searchContact();
                    break;
                case 4:
                    updateContact();
                    break;
                case 5:
                    deleteContact();
                    break;
                case 6:
                    running = false;
                    displayExitMessage();
                    break;
                default:
                    System.out.println("invalid choise please enter 1 - 6");
            }

            if (running) {
                waitForEnter();
            }
        }

        scanner.close();

    }

    private void waitForEnter() {

        System.out.println("\n Press Enter to continue ");
        scanner.nextLine();
    }

    private void searchContact() {

        System.out.println("=".repeat(60));
        System.out.println("              SEARCH CONTACT");
        System.out.println("=".repeat(60));

        String searchName = getStringInput("Enter name to search: ");

        if (searchName.isEmpty()
        ) {
            System.out.println("search name cannot be empty");
            return;
        }

        System.out.println("\n Searching .... \n");
        List<Contact> results = contactService.searchContactByName(searchName);

        if (!results.isEmpty()) {
            System.out.println("\n" + "-".repeat(60));
            System.out.println("Search result ");
            System.out.println("-".repeat(60));

            for (Contact contact : results) {
                System.out.println(contact.toDisplayString());
            }
            System.out.println("-".repeat(60));
        }
    }


    private void updateContact() {
        System.out.println("=".repeat(60));
        System.out.println("              UPDATE CONTACT");
        System.out.println("=".repeat(60));

        int id = getIntInput("Enter Contact ID to update: ");

        Contact existingContact = contactService.getContactById(id);

        if (existingContact == null) {
            return;
        }

        System.out.println("Current Details");
        System.out.println(existingContact.toDisplayString());
        System.out.println("\n enter new details (press enter to keep current value ):");

        String firstName = getStringInput("FirstName [" + existingContact.getFirstName() + "]: ");
        String lastName = getStringInput("Last Name [" + existingContact.getLastName() + "]: ");
        String phone = getStringInput("Phone [" + existingContact.getPhone() + "]: ");
        String email = getStringInput("Email [" + existingContact.getEmail() + "]: ");
        String address = getStringInput("Address [" + existingContact.getAddress() + "]: ");


        if (!firstName.isEmpty()) existingContact.setFirstName(firstName);
        if (!lastName.isEmpty()) existingContact.setLastName(lastName);
        if (!phone.isEmpty()) existingContact.setPhone(phone);
        if (!email.isEmpty()) existingContact.setEmail(email);
        if (!address.isEmpty()) existingContact.setAddress(address);

        System.out.println("\n Updating Contact ..");
        boolean success = contactService.updateContact(existingContact);

        if (success) {
            Contact updated = contactService.getContactById(id);
            System.out.println("Updated Details ");
            System.out.println(updated.toDisplayString());
        }
    }

    private void deleteContact() {
        System.out.println("=".repeat(60));
        System.out.println("              DELETE CONTACT");
        System.out.println("=".repeat(60));

        int id = getIntInput("Enter Contact ID to delete: ");

        Contact contact = contactService.getContactById(id);

        if (contact == null) {
            return;
        }

        System.out.println("Contact to delete");
        System.out.println(contact.toDisplayString());

        String confirm = getStringInput("Are you sure want to delte ? (yes/no)");

        if (confirm.equalsIgnoreCase("yes") || confirm.equalsIgnoreCase("y")) {
            System.out.println("Deleting contact ");
            contactService.deleteContact(id);
        } else {
            System.out.println("deletion cancelled");
        }
    }

    private void displayExitMessage() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("       Thank you for using Contact Manager!");
        System.out.println("                   Goodbye!");
        System.out.println("=".repeat(60) + "\n");
    }


    private void viewAllContacts() {

        System.out.println("=".repeat(60));
        System.out.println("   ALL CONTACTS");
        System.out.println("=".repeat(60));

        contactService.displayAllContacts();


    }

    private void addNewContact() {
        System.out.println("=".repeat(60));
        System.out.println("                ADD NEW CONTACT");
        System.out.println("=".repeat(60));

        String firstName = getStringInput("Enter First Name: ");
        String lastName = getStringInput("Enter Last Name (optional): ");
        String phone = getStringInput("Enter Phone Number: ");
        String email = getStringInput("Enter Email (optional): ");
        String address = getStringInput("Enter Address (optional): ");

        Contact contact = new Contact(
                firstName, lastName.isEmpty() ? null : lastName,
                phone,
                email.isEmpty() ? null : email,
                address.isEmpty() ? null : address
        );

        System.out.println("Adding contact ... ");
        contactService.addContact(contact);

    }


    private String getStringInput(String s) {
        System.out.print(s);
        return scanner.nextLine().trim();
    }

    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.println(prompt);
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("invalid input ! enter a number");
            }
        }
    }


    private void displayWelcomeBanner() {

        System.out.println("\n" + "=".repeat(60));
        System.out.println("       WELCOME TO CONTACT MANAGER SYSTEM");
        System.out.println("=".repeat(60));
        System.out.println("         Manage your contacts efficiently!");
        System.out.println("=".repeat(60) + "\n");
    }

    private void displayMenu() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                     MAIN MENU");
        System.out.println("=".repeat(60));
        System.out.println("  1. Add New Contact");
        System.out.println("  2. View All Contacts");
        System.out.println("  3. Search Contact by Name");
        System.out.println("  4. Update Contact");
        System.out.println("  5. Delete Contact");
        System.out.println("  6. Exit");
        System.out.println("=".repeat(60));
    }


}
