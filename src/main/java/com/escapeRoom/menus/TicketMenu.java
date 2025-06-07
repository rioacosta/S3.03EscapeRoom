package com.escapeRoom.menus;

import com.escapeRoom.dao.DatabaseConnection;
import com.escapeRoom.dao.mysqlimp.MySQLTicketDAO;
import com.escapeRoom.exceptions.EmptyInputException;
import com.escapeRoom.services.TicketHandler;
import com.escapeRoom.utils.InputUtils;

import java.util.Scanner;

public class TicketMenu {

    private Scanner scanner = new Scanner(System.in);
    private DatabaseConnection dbConnection = DatabaseConnection.getInstance();
    private MySQLTicketDAO ticketDAO = new MySQLTicketDAO(dbConnection);
    TicketHandler ticketHandler = new TicketHandler(ticketDAO);

    private int selection = 0;

    public void runTicketMenu() {

        do {
            try {
                System.out.println("""
                        1 - Crear ticket
                        2 - Borrar ticket
                        3 - Calcular beneficio por venta de tickets
                        4 - Volver al menú principal""");

                String input = scanner.nextLine();
                selection = checkInput(input);

                switch (selection) {
                    case 1:
                        ticketHandler.createTicket();
                        break;
                    case 2:
                        ticketHandler.deleteTicket();
                        break;
                    case 3:
                        ticketHandler.calculateTotalProfit();
                        break;
                    case 4:
                        // TODO
                        // enviar al menú principal
                        break;
                    default:
                        System.err.println("Error: El número introducido debe ser del 1 al 4");
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