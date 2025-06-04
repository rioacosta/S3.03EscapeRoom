package com.escapeRoom.dao.mysqlimp;

import com.escapeRoom.dao.DatabaseConnection;
import com.escapeRoom.dao.interfaces.IGenericDAO;
import com.escapeRoom.entities.Room;
import com.escapeRoom.entities.enums.Difficulty;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLRoomDAO implements IGenericDAO<Room, Integer> {
    private static final Logger logger = Logger.getLogger(MySQLRoomDAO.class.getName());
    private final Connection connection;

    public MySQLRoomDAO(DatabaseConnection databaseConnection) {
        this.connection = databaseConnection.getConnection();
    }

    @Override
    public boolean create(Room room) {
        String sql = "INSERT INTO room (idEscaperoom_ref, name, dificulty, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, room.getIdEscaperoom_ref());
            stmt.setString(2, room.getName());
            stmt.setString(3, room.getDifficulty().name()); // Manejo correcto de ENUM
            stmt.setBigDecimal(4, room.getPrice());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                logger.warning("Creación fallida, ninguna fila afectada");
                return false;
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    room.setIdRoom(generatedKeys.getInt(1));
                    logger.log(Level.INFO, "Room created with ID: {0}", room.getIdRoom());
                } else {
                    logger.warning("No se pudo recuperar el ID generado");
                    return false;
                }
            }
            return true;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error creando la sala", e);
            return false;
        }
    }

    @Override
    public Optional<Room> findById(Integer id) {
        String sql = "SELECT * FROM room WHERE idRoom = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToRoom(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error encontrando la sala por ID: " + id, e);
        }
        return Optional.empty();
    }

    @Override
    public boolean update(Room room) {
        String sql = "UPDATE room SET idEscaperoom_ref = ?, name = ?, dificulty = ?, price = ? WHERE idRoom = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, room.getIdEscaperoom_ref());
            stmt.setString(2, room.getName());
            stmt.setString(3, room.getDifficulty().name());
            stmt.setBigDecimal(4, room.getPrice());
            stmt.setInt(5, room.getIdRoom());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                logger.warning("Modificiación fallida, ninguna fila ha sido afectada en su ID: " + room.getIdRoom());
                return false;
            }
            return true;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error modificando el sala ID: " + room.getIdRoom(), e);
            return false;
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        String sql = "DELETE FROM room WHERE idRoom = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                logger.warning("Borrado fallido, sala no encontrada ID: " + id);
                return false;
            }
            return true;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error borrando sala ID: " + id, e);
            return false;
        }
    }

    @Override
    public List<Room> findAll() {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM room";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                rooms.add(mapResultSetToRoom(rs));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error recuperando todas las salas", e);
        }
        return rooms;
    }

    @Override
    public boolean existsById(Integer id) {
        String sql = "SELECT 1 FROM room WHERE idRoom = ?";
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

    private Room mapResultSetToRoom(ResultSet rs) throws SQLException {
        Room room = new Room(//
           //  rs.getInt("idRoom"),
           // rs.getInt("idEscaperoom_ref"),
           // rs.getString("name"),
            //Difficulty.valueOf(rs.getString("difficulty")),
          //  rs.getBigDecimal("price")
        );
        room.setIdRoom(rs.getInt("idRoom"));
        room.setIdEscaperoom_ref(rs.getInt("idEscaperoom_ref"));
        room.setName(rs.getString("name"));

        String difficultyStr = rs.getString("difficulty");
        try {
            room.setDifficulty(Difficulty.valueOf(difficultyStr));
        } catch (IllegalArgumentException e) {
            logger.log(Level.WARNING, "Valor de dificultad invalido: {0}", difficultyStr);
            room.setDifficulty(Difficulty.MEDIUM);
        }

        room.setPrice(rs.getBigDecimal("price"));
        return room;
    }
}
