package com.escapeRoom.dao.mysqlimp;


import com.escapeRoom.dao.DatabaseConnection;
import com.escapeRoom.dao.interfaces.IGenericDAO;
import com.escapeRoom.entities.Decoration;

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
                logger.warning("Creación fallida, ninguna fila afectada");
                return false;
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    decoration.setIdDecoration(generatedKeys.getInt(1));
                    logger.log(Level.INFO, "Decoración creado con ID: {0}", decoration.getIdDecoration());
                } else {
                    logger.warning("No se pudo recuperar el ID generado");
                    return false;
                }
            }
            return true;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error creando decoración", e);
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
            logger.log(Level.SEVERE, "Error encontrando la decoración por ID: " + id, e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Decoration> findByName(String name) {
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
                logger.warning("Modificiación fallida, ninguna fila ha sido afectada en su ID: " + decoration.getIdDecoration());
                return false;
            }
            return true;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error modificando el decoración ID: " + decoration.getIdDecoration(), e);
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
                logger.warning("Borrado fallido, decoración no encontrada por ID: " + id);
                return false;
            }
            return true;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error borrando la decoración por ID: " + id, e);
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
            logger.log(Level.SEVERE, "Error recuperando todas las decoraciones", e);
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
            logger.log(Level.SEVERE, "Error checkeando la existencia del ID: " + id, e);
            return false;
        }
    }

    private Decoration mapResultSetToDecoration(ResultSet rs) throws SQLException {
        Decoration decoration = new Decoration(
                rs.getInt("idDecoration"),
                rs.getInt("idRoom_ref"),
                rs.getString("description"),
                rs.getString("material"),
                rs.getBigDecimal("price")
        );
        decoration.setIdDecoration(rs.getInt("idDecoration"));
        decoration.setIdRoom_ref(rs.getInt("idRoom_ref"));
        decoration.setDescription(rs.getString("description"));
        decoration.setMaterial(rs.getString("material"));
        decoration.setPrice(rs.getBigDecimal("price"));
        return decoration;
    }

    public List<Decoration> findByRoomId(int roomId) {
        List<Decoration> decorations = new ArrayList<>();
        String sql = "SELECT * FROM decoration WHERE idRoom_ref = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, roomId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                decorations.add(mapResultSetToDecoration(rs));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error obteniendo decoraciones para la sala: " + roomId, e);
        }
        return decorations;
    }



}