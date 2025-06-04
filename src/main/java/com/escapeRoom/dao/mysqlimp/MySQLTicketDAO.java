package com.escapeRoom.dao.mysqlimp;

import com.escapeRoom.dao.DatabaseConnection;
import com.escapeRoom.dao.interfaces.IGenericDAO;
import com.escapeRoom.entities.Tickets;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLTicketDAO implements IGenericDAO<Tickets, Integer> {
    private static final Logger logger = Logger.getLogger(MySQLTicketDAO.class.getName());
    private final Connection connection;

    public MySQLTicketDAO(DatabaseConnection databaseConnection) {
        this.connection = databaseConnection.getConnection();
    }

    @Override
    public boolean create(Tickets ticket) {
        if (ticket.getIdRoom() <= 0 || ticket.getIdPlayer() <= 0) {
            throw new IllegalArgumentException("IDs inválidos");
        }

        String sql = "INSERT INTO tickets (idRoom, idPlayer, boughtOn, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, ticket.getIdRoom());
            stmt.setInt(2, ticket.getIdPlayer());
            stmt.setDate(3, Date.valueOf(ticket.getBoughtOn()));
            stmt.setBigDecimal(4, ticket.getPrice());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        ticket.setIdTickets(rs.getInt(1)); // Asignar ID generado
                        return true;
                    }
                }
            }
            return false;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error creando ticket", e);
            return false;
        }
    }

    @Override
    public Optional<Tickets> findById(Integer id) {
        String sql = "SELECT * FROM tickets WHERE idTickets = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToTicket(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error encontrando el ticket por ID: " + id, e);
        }
        return Optional.empty();
    }

    @Override
    public boolean update(Tickets ticket) {
        if(ticket.getIdRoom() <= 0 || ticket.getIdPlayer() <= 0) {
            throw new IllegalArgumentException("IDs inválidos");
        }
        String sql = "UPDATE tickets SET idRoom = ?, idPlayer = ?, boughtOn = ?, price = ? WHERE idTickets = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, ticket.getIdRoom());
            stmt.setInt(2, ticket.getIdPlayer());
            stmt.setDate(3, Date.valueOf(ticket.getBoughtOn()));
            stmt.setBigDecimal(4, ticket.getPrice());
            stmt.setInt(5, ticket.getIdTickets());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error actualizando ticket ID: " + ticket.getIdTickets(), e);
            return false;
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        String sql = "DELETE FROM tickets WHERE idTickets = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error borrando el ticket ID: " + id, e);
            return false;
        }
    }

    @Override
    public List<Tickets> findAll() {
        List<Tickets> tickets = new ArrayList<>();
        String sql = "SELECT * FROM tickets";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                tickets.add(mapResultSetToTicket(rs));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error recuperando todos los tickets", e);
        }
        return tickets;
    }

    @Override
    public boolean existsById(Integer id) {
        String sql = "SELECT 1 FROM tickets WHERE idTickets = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error checkeando la existencia del ID: " + id, e);
            return false;
        }
    }

    private Tickets mapResultSetToTicket(ResultSet rs) throws SQLException {
        Date boughtOnDate = rs.getDate("boughtOn");
        LocalDate boughtOn = (boughtOnDate != null) ? boughtOnDate.toLocalDate() : null;

        return new Tickets(
                rs.getInt("idTickets"),
                rs.getInt("idRoom"),
                rs.getInt("idPlayer"),
                boughtOn,
                rs.getBigDecimal("price")
        );
    }
}
