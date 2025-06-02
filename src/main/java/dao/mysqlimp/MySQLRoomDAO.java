package dao.mysqlimp;

import dao.DatabaseConnection;
import dao.interfaces.IGenericDAO;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class MySQLRoomDAO implements IGenericDAO {
    private static final Logger logger = Logger.getLogger(MySQLRoomDAO.class.getName());
    private Connection connection;

    public MySQLRoomDAO(DatabaseConnection databaseConnection) {
        this.connection = databaseConnection.getConnection();
    }

    @Override
    public boolean create(Object entity) {
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
    public Optional findById(Object o) {
        String sql = "SELECT * FROM Room WHERE id = ? AND activa = TRUE";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(rs);
                }
            }
        } catch (SQLException e) {
            logger.severe("Error al buscar Sala por ID: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public boolean update(Object room) {
        String sql = "UPDATE salas SET name = ?, dificulty = ?, price = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, room.getEscapeRoomId());
            stmt.setString(2, room.getName());
            stmt.setInt(3, room.getDificulty());
            stmt.setBigDecimal(4, room.getPrice());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("Sala actualizada exitosamente: " + room.getNombre());
                return true;
            }
        } catch (SQLException e) {
            logger.severe("Error al actualizar Sala: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteById(Object o) {
        return false;
    }

    @Override
    public List findAll() {
        return List.of();
    }

    @Override
    public boolean existsById(Object o) {
        return false;
    }
}

