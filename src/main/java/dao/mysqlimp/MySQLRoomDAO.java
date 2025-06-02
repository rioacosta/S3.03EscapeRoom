<<<<<<< HEAD
//--------------------------------------------------------------------------------------------------------------------
=======
>>>>>>> origin/rio
package dao.mysqlimp;

import dao.DatabaseConnection;
import dao.interfaces.IGenericDAO;
<<<<<<< HEAD
import java.sql.*;
import java.util.ArrayList;
=======

import java.sql.*;
>>>>>>> origin/rio
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

<<<<<<< HEAD
public class MySQLRoomDAO implements IGenericDAO<Room, Integer> {
    private static final Logger logger = Logger.getLogger(MySQLRoomDAO.class.getName());
    private final Connection connection;
=======
public class MySQLRoomDAO implements IGenericDAO {
    private static final Logger logger = Logger.getLogger(MySQLRoomDAO.class.getName());
    private Connection connection;
>>>>>>> origin/rio

    public MySQLRoomDAO(DatabaseConnection databaseConnection) {
        this.connection = databaseConnection.getConnection();
    }

    @Override
<<<<<<< HEAD
    public boolean create(Room room) {
=======
    public boolean create(Object entity) {
>>>>>>> origin/rio
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
<<<<<<< HEAD
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
=======

    }

    @Override
    public Optional findById(Object o) {
        String sql = "SELECT * FROM Room WHERE id = ? AND activa = TRUE";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(rs);
>>>>>>> origin/rio
                }
            }
        } catch (SQLException e) {
            logger.severe("Error al buscar Sala por ID: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
<<<<<<< HEAD
    public boolean update(Room room) {
        String sql = "UPDATE salas SET escape_room_id = ?, name = ?, dificulty = ?, price = ? WHERE id = ?";
=======
    public boolean update(Object room) {
        String sql = "UPDATE salas SET name = ?, dificulty = ?, price = ? WHERE id = ?";

>>>>>>> origin/rio
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, room.getEscapeRoomId());
            stmt.setString(2, room.getName());
            stmt.setInt(3, room.getDificulty());
            stmt.setBigDecimal(4, room.getPrice());
<<<<<<< HEAD
            stmt.setInt(5, room.getId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("Sala actualizada exitosamente: " + room.getId());
=======

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("Sala actualizada exitosamente: " + room.getNombre());
>>>>>>> origin/rio
                return true;
            }
        } catch (SQLException e) {
            logger.severe("Error al actualizar Sala: " + e.getMessage());
        }
        return false;
    }

    @Override
<<<<<<< HEAD
    public boolean deleteById(Integer id) {
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
=======
    public boolean deleteById(Object o) {
>>>>>>> origin/rio
        return false;
    }

    @Override
<<<<<<< HEAD
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
=======
    public List findAll() {
        return List.of();
    }

    @Override
    public boolean existsById(Object o) {
        return false;
    }
}

>>>>>>> origin/rio
