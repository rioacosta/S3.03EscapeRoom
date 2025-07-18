package com.escapeRoom.controllers;

import com.escapeRoom.dao.DatabaseConnection;
import com.escapeRoom.dao.mysqlimp.MySQLTicketDAO;
import com.escapeRoom.exceptions.EmptyInputException;
import com.escapeRoom.manager.MenuManager;
import com.escapeRoom.services.TicketHandler;

import java.util.Scanner;

public class TicketController {

    private Scanner scanner;
    private DatabaseConnection dbConnection;
    private MySQLTicketDAO ticketDAO;
    private MenuManager menuManager;
    private TicketHandler ticketHandler;

    public TicketController(Scanner scanner) {
        this.scanner = scanner;
        this.menuManager = new MenuManager(scanner);
        this.dbConnection = DatabaseConnection.getInstance();
        this.ticketDAO = new MySQLTicketDAO(dbConnection);
        this.ticketHandler = new TicketHandler(ticketDAO);
    }

    public void handleTicketOperations() {
        int option = 0;

        do {
            try {
                option = menuManager.showTicketMenu();

                switch (option) {
                    case 1 -> ticketHandler.createTicket();
                    case 2 -> ticketHandler.deleteTicket();
                    case 3 -> System.out.println(ticketHandler.calculateTotalProfit() + " euros de beneficio total");
                }
            } catch (EmptyInputException | IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        } while (option != 0);
    }

}