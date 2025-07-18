package com.escapeRoom.dao.mysqlimp;


import com.escapeRoom.dao.DatabaseConnection;
import com.escapeRoom.dao.interfaces.IGenericDAO;
import com.escapeRoom.entities.EscapeRoom;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLEscaperoomDAO implements IGenericDAO<EscapeRoom, Integer> {
    private static final Logger logger = Logger.getLogger(MySQLEscaperoomDAO.class.getName());
    private final Connection connection;

    public MySQLEscaperoomDAO(DatabaseConnection databaseConnection) {
        this.connection = databaseConnection.getConnection();
    }

    @Override
    public boolean create(EscapeRoom escaperoom) {
        String sql = "INSERT INTO escaperoom (name) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, escaperoom.getName());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                logger.warning("Creación fallida, ninguna fila afectada");
                return false;
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    escaperoom.setIdEscaperoom(generatedKeys.getInt(1));
                    logger.log(Level.INFO, "Escaperoom creado con ID: {0}", escaperoom.getIdEscaperoom());
                } else {
                    logger.warning("No se pudo recuperar el ID generado.");
                    return false;
                }
            }
            return true;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error creando el escaperoom", e);
            return false;
        }
    }

    @Override
    public Optional<EscapeRoom> findById(Integer id) {
        String sql = "SELECT * FROM escaperoom WHERE idEscaperoom = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToEscaperoom(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error encontrando el escaperoom por ID: " + id, e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<EscapeRoom> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public boolean update(EscapeRoom escaperoom) {
        String sql = "UPDATE escaperoom SET name = ? WHERE idEscaperoom = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, escaperoom.getName());
            stmt.setInt(2, escaperoom.getIdEscaperoom());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                logger.warning("Modificiacion fallida, ninguna fila ha sido afectada en su ID: " + escaperoom.getIdEscaperoom());
                return false;
            }
            return true;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error modificando el escaperoom ID: " + escaperoom.getIdEscaperoom(), e);
            return false;
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        String sql = "DELETE FROM escaperoom WHERE idEscaperoom = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                logger.warning("Borrado fallido, escaperoom nono encontrado ID: " + id);
                return false;
            }
            return true;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error borrando el escaperoom ID: " + id, e);
            return false;
        }
    }

    @Override
    public List<EscapeRoom> findAll() {
        List<EscapeRoom> escaperooms = new ArrayList<>();
        String sql = "SELECT * FROM escaperoom";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                escaperooms.add(mapResultSetToEscaperoom(rs));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error recuperando todos los escaperooms", e);
        }
        return escaperooms;
    }

    @Override
    public boolean existsById(Integer id) {
        String sql = "SELECT 1 FROM escaperoom WHERE idEscaperoom = ?";
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

    private EscapeRoom mapResultSetToEscaperoom(ResultSet rs) throws SQLException {
        EscapeRoom escaperoom = new EscapeRoom();

        escaperoom.setIdEscaperoom(rs.getInt("idEscaperoom"));
        escaperoom.setName(rs.getString("name"));
        return escaperoom;
    }
}