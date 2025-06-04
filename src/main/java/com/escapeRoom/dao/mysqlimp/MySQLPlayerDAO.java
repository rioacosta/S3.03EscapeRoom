package com.escapeRoom.dao.mysqlimp;

import org.example.dao.DatabaseConnection;
import org.example.dao.interfaces.IGenericDAO;
import org.example.model.entities.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

class MySQLPlayerDAO implements IGenericDAO<Player, Integer> {
    private static final Logger logger = Logger.getLogger(MySQLPlayerDAO.class.getName());
    private final Connection connection;

    public MySQLPlayerDAO(DatabaseConnection databaseConnection) {
        this.connection = databaseConnection.getConnection();
    }

    @Override
    public boolean create(org.example.model.entities.Player player) {
        String sql = "INSERT INTO player (idPlayer, name, email) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, player.getIdPlayer());
            stmt.setString(2, player.getName());
            stmt.setString(3, player.getEmail());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error creando el jugador", e);
            return false;
        }
    }

    @Override
    public Optional<Player> findById(Integer id) {
        String sql = "SELECT * FROM player WHERE idPlayer = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(mapResultSetToPlayer(rs));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error encontrando jugador por ID: " + id, e);
        }
        return Optional.empty();
    }

    @Override
    public boolean update(Player player) {
        String sql = "UPDATE player SET name = ?, email = ? WHERE idPlayer = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, player.getName());
            stmt.setString(2, player.getEmail());
            stmt.setInt(3, player.getIdPlayer());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error modificando el jugador ID: " + player.getIdPlayer(), e);
            return false;
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        String sql = "DELETE FROM player WHERE idPlayer = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error borrando el jugador ID: " + id, e);
            return false;
        }
    }

    @Override
    public List<Player> findAll() {
        List<Player> players = new ArrayList<>();
        String sql = "SELECT * FROM player";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                players.add(mapResultSetToPlayer(rs));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error recuperando todos los jugadores", e);
        }
        return players;
    }

    @Override
    public boolean existsById(Integer id) {
        String sql = "SELECT 1 FROM player WHERE idPlayer = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error checkeando la existencia del ID: " + id, e);
            return false;
        }
    }

    private Player mapResultSetToPlayer(ResultSet rs) throws SQLException {
        Player player = new Player();
        player.setIdPlayer(rs.getInt("idPlayer"));
        player.setName(rs.getString("name"));
        player.setEmail(rs.getString("email"));
        return player;
    }


}
