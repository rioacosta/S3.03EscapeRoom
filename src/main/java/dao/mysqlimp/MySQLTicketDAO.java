package org.example.dao.mysqlimp;

import org.example.dao.DatabaseConnection;
import org.example.dao.interfaces.IGenericDAO;
import org.example.model.entities.Tickets;

import java.sql.*;
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
        String sql = "INSERT INTO tickets (idTickets, idRoom, idPlayer, boughtOn, price) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, ticket.idTickets());
            stmt.setInt(2, ticket.idRoom());
            stmt.setInt(3, ticket.idPlayer());
            stmt.setDate(4, Date.valueOf(ticket.boughtOn())); // Conversión de LocalDate a SQL Date
            stmt.setDouble(5, ticket.price());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error creando el ticket", e);
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
        String sql = "UPDATE tickets SET idRoom = ?, idPlayer = ?, boughtOn = ?, price = ? WHERE idTickets = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, ticket.idRoom());
            stmt.setInt(2, ticket.idPlayer());
            stmt.setDate(3, Date.valueOf(ticket.boughtOn())); // Conversión de LocalDate a SQL Date
            stmt.setDouble(4, ticket.price());
            stmt.setInt(5, ticket.idTickets());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error modificando el ticket ID: " + ticket.idTickets(), e);
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
        return new Tickets(
                rs.getInt("idTickets"),
                rs.getInt("idRoom"),
                rs.getInt("idPlayer"),
                rs.getDate("boughtOn").toLocalDate(), // Conversión de SQL Date a LocalDate
                rs.getDouble("price")
        );
    }
}
