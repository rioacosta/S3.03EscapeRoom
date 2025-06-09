package com.escapeRoom.controllers;

import com.escapeRoom.dao.DatabaseConnection;
import com.escapeRoom.dao.mysqlimp.MySQLTicketDAO;
import com.escapeRoom.exceptions.EmptyInputException;
import com.escapeRoom.manager.MenuManager;
import com.escapeRoom.services.TicketHandler;
import com.escapeRoom.utils.InputUtils;

import java.util.Scanner;

public class TicketController {

    private Scanner scanner = new Scanner(System.in);
    private DatabaseConnection dbConnection = DatabaseConnection.getInstance();
    private MySQLTicketDAO ticketDAO = new MySQLTicketDAO(dbConnection);
    private MenuManager menuManager;
    private TicketHandler ticketHandler = new TicketHandler(ticketDAO);

    private int selection = 0;

    public void handleTicketOperations() {
        String input;

        do {
            try {
                input = menuManager.showTicketMenu();
                selection = checkInput(input);

                switch (selection) {
                    case 1 -> ticketHandler.createTicket();
                    case 2 -> ticketHandler.deleteTicket();
                    case 3 -> System.out.println(ticketHandler.calculateTotalProfit() + " euros de beneficio total");
                    case 4 -> menuManager.showMainMenu();
                    default -> System.err.println("Error: El número introducido debe ser del 1 al 4");
                }
            } catch (EmptyInputException | IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        } while (selection != 4);
    }

    public int checkInput(String input) {
        InputUtils.checkEmptyInput(input);

        try {
            selection = Integer.parseInt(input);
            InputUtils.getValidInt(selection);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error: Debes introducir un número");
        }

        return selection;
    }
}