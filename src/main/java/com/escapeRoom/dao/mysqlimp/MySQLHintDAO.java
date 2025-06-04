package com.escapeRoom.dao.mysqlimp;


import com.escapeRoom.dao.DatabaseConnection;
import com.escapeRoom.dao.interfaces.IGenericDAO;
import com.escapeRoom.entities.Hint;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLHintDAO  implements IGenericDAO<Hint, Integer> {
    private static final Logger logger = Logger.getLogger(MySQLTicketDAO.class.getName());
    private final Connection connection;

    public MySQLHintDAO(DatabaseConnection databaseConnection) {
        this.connection = databaseConnection.getConnection();
    }

    @Override
    public boolean create(Hint hint) {
        String sql = "INSERT INTO hint (idHint, idRoom, description, theme, price) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, hint.getIdHint());
            stmt.setInt(2, hint.getIdRoom());
            stmt.setString(3, hint.getDescription());
            stmt.setString(4, hint.getTheme());
            stmt.setBigDecimal(5, hint.getPrice());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        hint.setIdHint(rs.getInt(1));
                    }
                }
                return true;
            }
            return false;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error creando la pista", e);
            return false;
        }
    }

    @Override
    public Optional<Hint> findById(Integer id) {
        String sql = "SELECT * FROM hint WHERE idHint = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToHint(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error encontrando la pista por ID: " + id, e);
        }
        return Optional.empty();
    }

    @Override
    public boolean update(Hint hint) {
        String sql = "UPDATE hint SET idHint = ?, idRoom = ?, theme = ?, price = ? WHERE idHint = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, hint.getIdHint());
            stmt.setInt(2, hint.getIdRoom());
            stmt.setString(3, hint.getDescription());
            stmt.setString(4, hint.getTheme());
            stmt.setBigDecimal(5, hint.getPrice());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error modificando la pista ID: " + hint.getIdHint(), e);
            return false;
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        String sql = "DELETE FROM hint WHERE idHint = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error borrando la pista ID: " + id, e);
            return false;
        }
    }

    @Override
    public List<Hint> findAll() {
        List<Hint> hints = new ArrayList<>();
        String sql = "SELECT * FROM hints";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                hints.add(mapResultSetToHint(rs));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error recuperando todas las pistas", e);
        }
        return hints;
    }

    @Override
    public boolean existsById(Integer id) {
        String sql = "SELECT 1 FROM hint WHERE idHint = ?";
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

    private Hint mapResultSetToHint(ResultSet rs) throws SQLException {
        return new Hint(
                rs.getInt("idHint"),
                rs.getInt("idRoom"),
                rs.getString("description"),
                rs.getString("theme"),
                rs.getBigDecimal("price")
        );
    }


}
