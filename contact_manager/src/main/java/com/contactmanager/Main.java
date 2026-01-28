package com.contactmanager;

import com.contactmanager.dao.ContactDAO;
import com.contactmanager.dao.ContactDAOImpl;
import com.contactmanager.service.ContactService;
import com.contactmanager.ui.ConsoleUI;

public class Main {

    public static void main(String[] args) {
        ContactDAO dao = new ContactDAOImpl();

        ContactService service = new ContactService(dao);

    ConsoleUI ui = new ConsoleUI(service);
ui.start();
    }


}


