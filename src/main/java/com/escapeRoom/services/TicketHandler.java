package com.escapeRoom.services;

import com.escapeRoom.dao.DatabaseConnection;
import com.escapeRoom.dao.interfaces.IGenericDAO;
import com.escapeRoom.dao.mysqlimp.MySQLPlayerDAO;
import com.escapeRoom.dao.mysqlimp.MySQLTicketDAO;
import com.escapeRoom.entities.Player;
import com.escapeRoom.entities.Tickets;
import com.escapeRoom.utils.InputUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class TicketHandler {

    private final IGenericDAO<Tickets, Integer> ticketDao;
    private Scanner scanner = new Scanner(System.in);
    private DatabaseConnection dbConnection = DatabaseConnection.getInstance();
    private MySQLTicketDAO ticketDAO = new MySQLTicketDAO(dbConnection);
    private MySQLPlayerDAO playerDAO = new MySQLPlayerDAO(dbConnection);

    public TicketHandler(IGenericDAO<Tickets, Integer> ticketDao) {
        this.ticketDao = ticketDao;
    }

    public void createTicket() {
        int idRoom = getRoom();
        int idPlayer = getPlayer();
        LocalDate boughtOn = LocalDate.now();
        BigDecimal price = getPrice();

        Tickets newTicket = new Tickets(idRoom, idPlayer, boughtOn, price);
        boolean result = ticketDAO.create(newTicket);

        if (result) {
            System.out.println("Ticket creado");
        } else {
            System.err.println("Error: El ticket no se pudo crear");
        }
    }

    public void deleteTicket() {
        // COMPROBAR QUE ESTO FUNCIONA
        // Ahora debería mostrar el jugador por nombre, no por id
        // Si funciona, refactorizar
        // Si no funciona, volver al commit anterior. Se veía feucho pero funcionaba
        System.out.println("Qué ticket quieres borrar?");
        List<Tickets> tickets = ticketDAO.findAll();

        List<Player> players = playerDAO.findAll();
        Map<Integer, String> playerNamesById = new HashMap<>();
        for (Player player : players) {
            playerNamesById.put(player.getIdPlayer(), player.getName());
        }

        for (Tickets ticket : tickets) {
            String playerName = playerNamesById.getOrDefault(ticket.getIdPlayer(), "Jugador desconocido");

            System.out.println(ticket.getIdTickets() + " - Vendido a " + playerName
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

    public int getRoom() {
        System.out.println("Para qué habitación es?");
        // mostrar lista de habitaciones

        int idRoom = scanner.nextInt();
        scanner.nextLine();
        // validar la respuesta no está vacía
        InputUtils.getValidInt(idRoom);
        // validar que la habitación existe

        return idRoom;
    }

    public int getPlayer() {
        System.out.println("Dime qué jugador compra el ticket");
        // mostrar lista de jugadores
        int idPlayer = scanner.nextInt();
        scanner.nextLine();
        // validar que no está vacío
        InputUtils.getValidInt(idPlayer);
        // validar que el jugador existe

        return idPlayer;
    }

    public BigDecimal getPrice() {
        System.out.println("Qué precio tiene?");
        BigDecimal price = scanner.nextBigDecimal();
        scanner.nextLine();
        // validar que no está vacío
        InputUtils.getValidBigDecimal(price);
        // validar que no sobrepasa las cifras permitidas por la base de datos

        return price;
    }

}