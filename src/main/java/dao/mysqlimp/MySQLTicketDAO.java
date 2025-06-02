package dao.mysqlimp;

import dao.DatabaseConnection;
import dao.interfaces.IGenericDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLTicketDAO implements IGenericDAO<Ticket, Integer> {
    private static final Logger logger = Logger.getLogger(MySQLTicketDAO.class.getName());
    private final Connection connection;

    public MySQLTicketDAO(DatabaseConnection databaseConnection) {
        this.connection = databaseConnection.getConnection();
    }

    @Override
    public boolean create(Ticket ticket) {
        String sql = "INSERT INTO tickets (idTickets, idRoom, idPlayer, boughtOn, price) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, ticket.getIdTickets());
            stmt.setInt(2, ticket.getIdRoom());
            stmt.setInt(3, ticket.getIdPlayer());
            stmt.setTimestamp(4, ticket.getBoughtOn());
            stmt.setBigDecimal(5, ticket.getPrice());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                logger.log(Level.INFO, "Ticket created with ID: {0}", ticket.getIdTickets());
                return true;
            } else {
                logger.warning("Create failed, no rows affected");
                return false;
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error creating ticket", e);
            return false;
        }
    }

    @Override
    public Optional<Ticket> findById(Integer id) {
        String sql = "SELECT * FROM tickets WHERE idTickets = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToTicket(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error finding ticket by ID: " + id, e);
        }
        return Optional.empty();
    }

    @Override
    public boolean update(Ticket ticket) {
        String sql = "UPDATE tickets SET idRoom = ?, idPlayer = ?, boughtOn = ?, price = ? WHERE idTickets = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, ticket.getIdRoom());
            stmt.setInt(2, ticket.getIdPlayer());
            stmt.setTimestamp(3, ticket.getBoughtOn());
            stmt.setBigDecimal(4, ticket.getPrice());
            stmt.setInt(5, ticket.getIdTickets());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                logger.warning("Update failed, no rows affected for ID: " + ticket.getIdTickets());
                return false;
            }
            return true;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating ticket ID: " + ticket.getIdTickets(), e);
            return false;
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        String sql = "DELETE FROM tickets WHERE idTickets = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                logger.warning("Delete failed, ticket not found ID: " + id);
                return false;
            }
            return true;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting ticket ID: " + id, e);
            return false;
        }
    }

    @Override
    public List<Ticket> findAll() {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * FROM tickets";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                tickets.add(mapResultSetToTicket(rs));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error retrieving all tickets", e);
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
            logger.log(Level.SEVERE, "Error checking existence for ID: " + id, e);
            return false;
        }
    }

    private Ticket mapResultSetToTicket(ResultSet rs) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setIdTickets(rs.getInt("idTickets"));
        ticket.setIdRoom(rs.getInt("idRoom"));
        ticket.setIdPlayer(rs.getInt("idPlayer"));
        ticket.setBoughtOn(rs.getTimestamp("boughtOn"));
        ticket.setPrice(rs.getBigDecimal("price"));
        return ticket;
    }
}