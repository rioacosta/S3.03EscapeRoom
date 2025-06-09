package dao;


import com.escapeRoom.dao.DatabaseConnection;
import com.escapeRoom.dao.mysqlimp.MySQLPlayerDAO;
import com.escapeRoom.entities.Player;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerPersistenceTest {

    @Test
    public void testInsertPlayer() {
        DatabaseConnection db = DatabaseConnection.getInstance();

        MySQLPlayerDAO playerDAO = new MySQLPlayerDAO(db);


        Player player = new Player("Poncio Pilatos", "laLocadeGrisales@mimail.com");
        player.setName("Juan garcia");
        player.setEmail("laLocadeGrisales@mimail.com");

        boolean playerId = playerDAO.create(player);
        assertTrue(true, "No se pudo insertar el jugador correctamente");

    }
}

