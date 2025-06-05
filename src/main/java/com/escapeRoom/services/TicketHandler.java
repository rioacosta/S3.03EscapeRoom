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
        InputUtils.getValidInt(idRoom);

        System.out.println("Dime qué jugador compra el ticket");
        int idPlayer = scanner.nextInt();
        InputUtils.getValidInt(idPlayer);

        LocalDate boughtOn = LocalDate.now();

        System.out.println("Qué precio tiene?");
        BigDecimal price = scanner.nextBigDecimal();
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
            System.out.println(ticket.getIdTickets());
        }

        int newTicketId = scanner.nextInt();
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

// CREO QUE ESTO NO LO NECESITO PORQUE HAGO LA VALIDACIÓN DE OTRA FORMA, PERO DEJAR POR SI ACASO DE MOMENTO

//    public boolean ticketIsValid(Tickets ticket) {
//        if(ticket == null || ticket.getIdRoom() <= 0 || ticket.getIdPlayer() <= 0 ||
//            ticket.getBoughtOn() == null || ticket.getPrice() == null) {
//                throw new NullOrEmptyException("Datos del ticket inválidos");
//        }
//        return ticketDao.create(ticket);
//    }

//    public boolean findTicketById(Integer id) {
//        if(id == null) {
//            throw new NullOrEmptyException("Id del ticket inválido");
//        }
//        return ticketDao.existsById(id);
//    }
//
//    public boolean deleteTicket(Integer id) {
//        if(!findTicketById(id)) {
//            throw new NullOrEmptyException("El ticket no existe");
//        }
//        return ticketDao.deleteById(id);
//    }

}