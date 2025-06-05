package com.escapeRoom.services;

import com.escapeRoom.dao.interfaces.IGenericDAO;
import com.escapeRoom.entities.Tickets;
import com.escapeRoom.exceptions.NullOrEmptyException;

import java.math.BigDecimal;
import java.util.List;

public class TicketHandler {

    private final IGenericDAO<Tickets, Integer> ticketDao;
    private List<Tickets> ticketsList;

    public TicketHandler(IGenericDAO<Tickets, Integer> ticketDao) {
        this.ticketDao = ticketDao;
    }

    public boolean createTicket(Tickets ticket) {
        if(ticket == null || ticket.getIdRoom() <= 0 || ticket.getIdPlayer() <= 0 ||
            ticket.getBoughtOn() == null || ticket.getPrice() == null) {
                throw new NullOrEmptyException("Datos del ticket inválidos");
        }
        // TODO
        // FALTA QUE SI LO CREA LO AÑADA A LA ticketsList
        return ticketDao.create(ticket);
    }

    public boolean findTicketById(Integer id) {
        if(id == null) {
            throw new NullOrEmptyException("Id del ticket inválido");
        }
        return ticketDao.existsById(id);
    }

    public boolean deleteTicket(Integer id) {
        if(!findTicketById(id)) {
            throw new NullOrEmptyException("El ticket no existe");
        }
        return ticketDao.deleteById(id);
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