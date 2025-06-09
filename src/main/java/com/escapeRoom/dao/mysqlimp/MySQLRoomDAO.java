package com.escapeRoom.dao.mysqlimp;

import com.escapeRoom.dao.DatabaseConnection;
import com.escapeRoom.dao.interfaces.IGenericDAO;
import com.escapeRoom.dao.interfaces.IRoomDao;
import com.escapeRoom.entities.Decoration;
import com.escapeRoom.entities.Hint;
import com.escapeRoom.entities.Room;
import com.escapeRoom.entities.enums.Difficulty;
import com.escapeRoom.entities.enums.Theme;
import com.escapeRoom.services.RoomHandler;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class MySQLRoomDAO implements IRoomDao, IGenericDAO<Room, Integer> {
    private static final Logger logger = Logger.getLogger(MySQLRoomDAO.class.getName());
    private final Connection connection;

    static {
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.ALL);
        logger.addHandler(handler);
        logger.setLevel(Level.ALL);
    }

    public MySQLRoomDAO(DatabaseConnection databaseConnection) {
        this.connection = databaseConnection.getConnection();
    }

    @Override
    public boolean create(Room room) {

        if (!escaperoomExists(room.getIdEscaperoom_ref())) {

            return false;
        }

        String sql = "INSERT INTO room (idEscaperoom_ref, name, difficulty, price, theme) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {


            stmt.setInt(1, room.getIdEscaperoom_ref());
            stmt.setString(2, room.getName());
            stmt.setString(3, room.getDifficulty().name());
            stmt.setBigDecimal(4, room.getPrice());
            stmt.setString(5, room.getTheme().name());


            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                logger.warning("Creación fallida, ninguna fila afectada");
                return false;
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    room.setIdRoom(generatedKeys.getInt(1));

                } else {
                    logger.warning("No se pudo recuperar el ID generado");
                    return false;
                }
            }
            return true;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error en la inserción SQL: " + e.getSQLState() + " - " + e.getMessage(), e);
            return false;
        }

    }

    public int saveAndReturnId(Room room) {
        if (!escaperoomExists(room.getIdEscaperoom_ref())) {
            return -1;
        }

        String sql = "INSERT INTO room (idEscaperoom_ref, name, difficulty, price, theme) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, room.getIdEscaperoom_ref());
            stmt.setString(2, room.getName());
            stmt.setString(3, room.getDifficulty().name());
            stmt.setBigDecimal(4, room.getPrice());
            stmt.setString(5, room.getTheme().name());


            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1); // Acá obtenés el ID generado
                    }
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error insertando la sala y obteniendo ID", e);
        }
        return -1;
    }


    @Override
    public Optional<Room> findById(Integer id) {
        String sql = "SELECT * FROM room WHERE idRoom = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Cargando decoraciones para roomId = " + id);

                    MySQLHintDAO hintDAO = new MySQLHintDAO(DatabaseConnection.getInstance());
                    MySQLDecorationDAO decoDAO = new MySQLDecorationDAO(DatabaseConnection.getInstance());


                    System.out.println(" decoraciones encontradas" + decoDAO.findByRoomId(id).size());
                    return Optional.of(mapResultSetToRoom(rs, hintDAO, decoDAO));


                }

            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error encontrando la sala por ID: " + id, e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Room> findByName(String name) {
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
        try (Statement stmt = this.connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            MySQLHintDAO hintDAO = new MySQLHintDAO(DatabaseConnection.getInstance());
            MySQLDecorationDAO decorationDAO = new MySQLDecorationDAO(DatabaseConnection.getInstance());


            while (rs.next()) {
                Room room = mapResultSetToRoom(rs, hintDAO, decorationDAO);
                rooms.add(room);
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


    private Room mapResultSetToRoom(ResultSet rs, MySQLHintDAO hintDAO, MySQLDecorationDAO decoDAO) throws SQLException {
        Room room = new Room();
        int roomId = rs.getInt("idRoom");

        room.setIdRoom(roomId);
        room.setIdEscaperoom_ref(rs.getInt("idEscaperoom_ref"));
        room.setName(rs.getString("name"));
        room.setDifficulty(Difficulty.valueOf(rs.getString("difficulty")));
        room.setTheme(Theme.valueOf(rs.getString("theme")));
        room.setPrice(rs.getBigDecimal("price"));

        room.setHints(hintDAO.findByRoomId(roomId));
        room.setDecorations(decoDAO.findByRoomId(roomId));

        return room;
    }


    private boolean escaperoomExists(int idEscaperoom_ref) {
        String query = "SELECT COUNT(*) FROM escaperoom WHERE idEscaperoom_ref = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idEscaperoom_ref);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error verificando existencia de escaperoom", e);
        }
        return false;
    }


    public boolean updateRoomName(int roomId, String newName) {
        String sql = "UPDATE room SET name = ? WHERE idRoom = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, newName);
            stmt.setInt(2, roomId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error actualizando nombre de sala", e);
            return false;
        }
    }

    public boolean updateRoomPrice(int roomId, BigDecimal newPrice) {
        String sql = "UPDATE room SET price = ? WHERE idRoom = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setBigDecimal(1, newPrice);
            stmt.setInt(2, roomId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error actualizando precio de sala", e);
            return false;
        }
    }

    public boolean updateRoomDifficulty(int roomId, Difficulty difficulty) {
        String sql = "UPDATE room SET difficulty = ? WHERE idRoom = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, difficulty.name());
            stmt.setInt(2, roomId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error actualizando dificultad de sala", e);
            return false;
        }
    }

    public boolean updateRoomTheme(int roomId, Theme theme) {
        String sql = "UPDATE room SET theme = ? WHERE idRoom = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, theme.name());
            stmt.setInt(2, roomId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error actualizando tema de sala", e);
            return false;
        }
    }


}
