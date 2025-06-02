//--------------------------------------------------------------------------------------------------------------------
package dao.mysqlimp;

import dao.DatabaseConnection;
import dao.interfaces.IGenericDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class MySQLRoomDAO implements IGenericDAO<Room, Integer> {
    private static final Logger logger = Logger.getLogger(MySQLRoomDAO.class.getName());
    private final Connection connection;

    public MySQLRoomDAO(DatabaseConnection databaseConnection) {
        this.connection = databaseConnection.getConnection();
    }

    @Override
    public boolean create(Room room) {
        String sql = "INSERT INTO salas (escape_room_id, name, dificulty, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, room.getEscapeRoomId());
            stmt.setString(2, room.getName());
            stmt.setInt(3, room.getDificulty());
            stmt.setBigDecimal(4, room.getPrice());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        room.setId(generatedKeys.getInt(1));
                    }
                }
                logger.info("Sala creada exitosamente: " + room.getName());
                return true;
            }
        } catch (SQLException e) {
            logger.severe("Error al crear Sala: " + e.getMessage());
        }
        return false;
    }

    @Override
    public Optional<Room> findById(Integer id) {
        String sql = "SELECT * FROM salas WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Room room = mapResultSetToRoom(rs);
                    return Optional.of(room);
                }
            }
        } catch (SQLException e) {
            logger.severe("Error al buscar Sala por ID: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public boolean update(Room room) {
        String sql = "UPDATE salas SET escape_room_id = ?, name = ?, dificulty = ?, price = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, room.getEscapeRoomId());
            stmt.setString(2, room.getName());
            stmt.setInt(3, room.getDificulty());
            stmt.setBigDecimal(4, room.getPrice());
            stmt.setInt(5, room.getId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("Sala actualizada exitosamente: " + room.getId());
                return true;
            }
        } catch (SQLException e) {
            logger.severe("Error al actualizar Sala: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        // Borrado físico (sin campo activa)
        String sql = "DELETE FROM salas WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("Sala eliminada permanentemente: " + id);
                return true;
            }
        } catch (SQLException e) {
            logger.severe("Error al eliminar Sala: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<Room> findAll() {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM salas";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                rooms.add(mapResultSetToRoom(rs));
            }
        } catch (SQLException e) {
            logger.severe("Error al obtener todas las salas: " + e.getMessage());
        }
        return rooms;
    }

    @Override
    public boolean existsById(Integer id) {
        String sql = "SELECT 1 FROM salas WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            logger.severe("Error al verificar existencia de Sala: " + e.getMessage());
        }
        return false;
    }

    // Método auxiliar para mapear ResultSet a objeto Room
    private Room mapResultSetToRoom(ResultSet rs) throws SQLException {
        Room room = new Room();
        room.setId(rs.getInt("id"));
        room.setEscapeRoomId(rs.getInt("escape_room_id"));
        room.setName(rs.getString("name"));
        room.setDificulty(rs.getInt("dificulty"));
        room.setPrice(rs.getBigDecimal("price"));
        return room;
    }
}