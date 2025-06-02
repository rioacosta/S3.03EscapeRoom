package dao.mysqlimp;

import dao.DatabaseConnection;
import dao.interfaces.IGenericDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLPlayerDAO implements IGenericDAO<Player, Integer> {
    private static final Logger logger = Logger.getLogger(MySQLPlayerDAO.class.getName());
    private final Connection connection;

    public MySQLPlayerDAO(DatabaseConnection databaseConnection) {
        this.connection = databaseConnection.getConnection();
    }

    @Override
    public boolean create(Player player) {
        String sql = "INSERT INTO player (idPlayer, name, email) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, player.getIdPlayer());
            stmt.setString(2, player.getName());
            stmt.setString(3, player.getEmail());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error creating Player", e);
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
            logger.log(Level.SEVERE, "Error finding Player by ID: " + id, e);
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
            logger.log(Level.SEVERE, "Error updating Player ID: " + player.getIdPlayer(), e);
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
            logger.log(Level.SEVERE, "Error deleting Player ID: " + id, e);
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
            logger.log(Level.SEVERE, "Error retrieving all Players", e);
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
            logger.log(Level.SEVERE, "Error checking existence for ID: " + id, e);
            return false;
        }
    }

    private Player mapResultSetToPlayer(ResultSet rs) throws SQLException {
        return new Player(rs.getInt("idPlayer"), rs.getString("name"), rs.getString("email"));
    }
}
