package com.escapeRoom.services;

import com.escapeRoom.dao.DatabaseConnection;
import com.escapeRoom.dao.interfaces.IGenericDAO;
import com.escapeRoom.dao.mysqlimp.MySQLPlayerDAO;
import com.escapeRoom.dao.mysqlimp.MySQLRoomDAO;
import com.escapeRoom.dao.mysqlimp.MySQLTicketDAO;
import com.escapeRoom.entities.Player;
import com.escapeRoom.entities.Room;
import com.escapeRoom.entities.Tickets;
import com.escapeRoom.utils.InputUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class TicketHandler {

    private final Scanner scanner = new Scanner(System.in);
    private final MySQLTicketDAO ticketDAO;
    private final MySQLPlayerDAO playerDAO;
    private final MySQLRoomDAO roomDAO;

    public TicketHandler(IGenericDAO<Tickets, Integer> ticketDao) {
        DatabaseConnection dbConnection = DatabaseConnection.getInstance();
        this.ticketDAO = new MySQLTicketDAO(dbConnection);
        this.playerDAO = new MySQLPlayerDAO(dbConnection);
        this.roomDAO = new MySQLRoomDAO(dbConnection);
    }

    public void createTicket() {
        int roomId = getValidRoomId();
        int playerId = getValidPlayerId();
        BigDecimal price = getValidPrice();
        LocalDate boughtOn = LocalDate.now();

        Tickets ticket = new Tickets(roomId, playerId, boughtOn, price);
        boolean created = ticketDAO.create(ticket);

        System.out.println(created ? "Ticket creado" : "Error: El ticket no se pudo crear");
    }

    public void deleteTicket() {
        System.out.println("¿Qué ticket quieres borrar?");
        showTickets();

        int ticketId = InputUtils.readValidInt(scanner);

        boolean deleted = ticketDAO.deleteById(ticketId);
        System.out.println(deleted ? "Ticket eliminado" : "Error: El ticket no se pudo eliminar");
    }

    public void showTickets() {
        List<Tickets> tickets = ticketDAO.findAll();
        Map<Integer, String> playerNames = getPlayerNamesById();

        if (tickets.isEmpty()) {
            System.out.println("No hay tickets registrados.");
            return;
        }

        for (Tickets ticket : tickets) {
            String playerName = playerNames.getOrDefault(ticket.getIdPlayer(), "Jugador desconocido");
            System.out.printf("%d - Vendido a %s. Precio: %s%n",
                    ticket.getIdTickets(), playerName, ticket.getPrice());
        }
    }

    public BigDecimal calculateTotalProfit() {
        return ticketDAO.findAll().stream()
                .map(Tickets::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private int getValidRoomId() {
        List<Room> rooms = roomDAO.findAll();
        if (rooms.isEmpty()) {
            System.err.println("No hay habitaciones disponibles.");
            return -1;
        }

        showRooms(rooms);

        while (true) {
            System.out.println("Ingresa el ID de la sala:");
            int roomId = InputUtils.readValidInt(scanner);

            boolean exists = rooms.stream().anyMatch(room -> room.getIdRoom() == roomId);
            if (exists) {
                return roomId;
            }

            System.err.println("ID de sala no válido. Intenta de nuevo.");
        }
    }

    private void showRooms(List<Room> rooms) {
        for (Room room : rooms) {
            System.out.printf("%d - %s | Dificultad: %s | Tema: %s | Precio: %s%n",
                    room.getIdRoom(), room.getName(), room.getDifficulty(),
                    room.getTheme(), room.getPrice());
        }
    }

    private int getValidPlayerId() {
        Map<Integer, String> playerNames = getPlayerNamesById();

        if (playerNames.isEmpty()) {
            System.err.println("No hay jugadores registrados.");
            return -1;
        }

        playerNames.forEach((id, name) -> System.out.printf("%d - %s%n", id, name));

        while (true) {
            System.out.println("Dime qué jugador compra el ticket:");
            int playerId = InputUtils.readValidInt(scanner);

            if (playerNames.containsKey(playerId)) {
                return playerId;
            }

            System.err.println("ID de jugador no válido. Intenta de nuevo.");
        }
    }

    private BigDecimal getValidPrice() {
        while (true) {
            System.out.println("¿Qué precio tiene?");
            BigDecimal price = InputUtils.readValidBigDecimal(scanner);

            if (price.compareTo(BigDecimal.ZERO) > 0 && price.scale() <= 2) {
                return price;
            }

            System.err.println("Precio inválido. Debe ser positivo y tener máximo dos decimales.");
        }
    }

    private Map<Integer, String> getPlayerNamesById() {
        Map<Integer, String> namesById = new HashMap<>();
        for (Player player : playerDAO.findAll()) {
            namesById.put(player.getIdPlayer(), player.getName());
        }
        return namesById;
    }

}