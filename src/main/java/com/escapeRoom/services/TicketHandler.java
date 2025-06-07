package com.escapeRoom.services;

import com.escapeRoom.dao.DatabaseConnection;
import com.escapeRoom.dao.interfaces.IGenericDAO;
import com.escapeRoom.dao.mysqlimp.MySQLTicketDAO;
import com.escapeRoom.entities.Tickets;
import com.escapeRoom.utils.InputUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class TicketHandler {

    private final IGenericDAO<Tickets, Integer> ticketDao;
    private Scanner scanner = new Scanner(System.in);
    private DatabaseConnection dbConnection = DatabaseConnection.getInstance();
    private MySQLTicketDAO ticketDAO = new MySQLTicketDAO(dbConnection);

    public TicketHandler(IGenericDAO<Tickets, Integer> ticketDao) {
        this.ticketDao = ticketDao;
    }

    public void createTicket() {
        System.out.println("Para qué habitación es?");
        int idRoom = scanner.nextInt();
        scanner.nextLine();
        InputUtils.getValidInt(idRoom);

        System.out.println("Dime qué jugador compra el ticket");
        int idPlayer = scanner.nextInt();
        scanner.nextLine();
        InputUtils.getValidInt(idPlayer);

        LocalDate boughtOn = LocalDate.now();

        System.out.println("Qué precio tiene?");
        BigDecimal price = scanner.nextBigDecimal();
        scanner.nextLine();
        InputUtils.getValidBigDecimal(price);

        Tickets newTicket = new Tickets(idRoom, idPlayer, boughtOn, price);
        boolean result = ticketDAO.create(newTicket);

        if (result) {
            System.out.println("Ticket creado");
        } else {
            System.err.println("Error: El ticket no se pudo crear");
        }
    }

    public void deleteTicket() {
        System.out.println("Qué ticket quieres borrar?");

        List<Tickets> tickets = ticketDAO.findAll();
        for (Tickets ticket : tickets) {
            // estaría bien que mostrara el nombre del jugador, no el número
            System.out.println(ticket.getIdTickets() + " - Vendido aljugador num " + ticket.getIdPlayer()
                    + ". Precio de " + ticket.getPrice());
        }

        int newTicketId = scanner.nextInt();
        scanner.nextLine();
        InputUtils.getValidInt(newTicketId);

        boolean result = ticketDAO.deleteById(newTicketId);

        if (result) {
            System.out.println("Ticket eliminado");
        } else {
            System.err.println("Error: El ticket no se pudo eliminar");
        }
    }

    public BigDecimal calculateTotalProfit() {
        BigDecimal totalProfit = BigDecimal.ZERO;
        List<Tickets> tickets = ticketDao.findAll();

        for (Tickets ticket : tickets) {
            totalProfit = totalProfit.add(ticket.getPrice());
        }

        return totalProfit;
    }

}