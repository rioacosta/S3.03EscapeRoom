package org.example.dao.mysqlimp;

import org.example.dao.DatabaseConnection;
import org.example.dao.interfaces.IGenericDAO;
import org.example.model.entities.Decoration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLDecorationDAO implements IGenericDAO<Decoration, Integer> {
    private static final Logger logger = Logger.getLogger(MySQLDecorationDAO.class.getName());
    private final Connection connection;

    public MySQLDecorationDAO(DatabaseConnection databaseConnection) {
        this.connection = databaseConnection.getConnection();
    }

    @Override
    public boolean create(Decoration decoration) {
        String sql = "INSERT INTO decoration (idRoom_ref, description, material, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, decoration.getIdRoom_ref());
            stmt.setString(2, decoration.getDescription());
            stmt.setString(3, decoration.getMaterial());
            stmt.setBigDecimal(4, decoration.getPrice());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                logger.warning("Create failed, no rows affected");
                return false;
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    decoration.setIdDecoration(generatedKeys.getInt(1));
                    logger.log(Level.INFO, "Decoration created with ID: {0}", decoration.getIdDecoration());
                } else {
                    logger.warning("Failed to retrieve generated ID");
                    return false;
                }
            }
            return true;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error creating decoration", e);
            return false;
        }
    }

    @Override
    public Optional<Decoration> findById(Integer id) {
        String sql = "SELECT * FROM decoration WHERE idDecoration = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToDecoration(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error finding decoration by ID: " + id, e);
        }
        return Optional.empty();
    }

    @Override
    public boolean update(Decoration decoration) {
        String sql = "UPDATE decoration SET idRoom_ref = ?, description = ?, material = ?, price = ? WHERE idDecoration = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, decoration.getIdRoom_ref());
            stmt.setString(2, decoration.getDescription());
            stmt.setString(3, decoration.getMaterial());
            stmt.setBigDecimal(4, decoration.getPrice());
            stmt.setInt(5, decoration.getIdDecoration());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                logger.warning("Update failed, no rows affected for ID: " + decoration.getIdDecoration());
                return false;
            }
            return true;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating decoration ID: " + decoration.getIdDecoration(), e);
            return false;
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        String sql = "DELETE FROM decoration WHERE idDecoration = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                logger.warning("Delete failed, decoration not found ID: " + id);
                return false;
            }
            return true;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting decoration ID: " + id, e);
            return false;
        }
    }

    @Override
    public List<Decoration> findAll() {
        List<Decoration> decorations = new ArrayList<>();
        String sql = "SELECT * FROM decoration";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                decorations.add(mapResultSetToDecoration(rs));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error retrieving all decorations", e);
        }
        return decorations;
    }

    @Override
    public boolean existsById(Integer id) {
        String sql = "SELECT 1 FROM decoration WHERE idDecoration = ?";
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

    private Decoration mapResultSetToDecoration(ResultSet rs) throws SQLException {
        Decoration decoration = new Decoration();
        decoration.setIdDecoration(rs.getInt("idDecoration"));
        decoration.setIdRoom_ref(rs.getInt("idRoom_ref"));
        decoration.setDescription(rs.getString("description"));
        decoration.setMaterial(rs.getString("material"));
        decoration.setPrice(rs.getBigDecimal("price"));
        return decoration;
    }
}