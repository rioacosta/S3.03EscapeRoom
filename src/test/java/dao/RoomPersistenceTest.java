package dao;


import com.escapeRoom.dao.DatabaseConnection;
import com.escapeRoom.dao.mysqlimp.MySQLHintDAO;
import com.escapeRoom.dao.mysqlimp.MySQLRoomDAO;
import com.escapeRoom.entities.Hint;
import com.escapeRoom.entities.Room;
import com.escapeRoom.entities.enums.Difficulty;
import com.escapeRoom.entities.enums.Theme;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class RoomPersistenceTest {

    @Test
    public void testInsertRoomWithHint() {
        DatabaseConnection db = DatabaseConnection.getInstance();

        MySQLRoomDAO roomDAO = new MySQLRoomDAO(db);
        MySQLHintDAO hintDAO = new MySQLHintDAO(db);

        Room room = new Room();
        room.setIdEscaperoom_ref(1);
        room.setName("Test Room");
        room.setDifficulty(Difficulty.MEDIUM);
        room.setTheme(Theme.DISNEY);
        room.setPrice(new BigDecimal("30.00"));

        int roomId = roomDAO.saveAndReturnId(room);
        assertTrue(roomId > 0, "No se pudo insertar la Room correctamente");

        Hint hint = new Hint("Pista de prueba", Theme.DISNEY, new BigDecimal("4.99"));
        hint.setIdRoom_ref(roomId);

        boolean hintSaved = hintDAO.create(hint);

        assertTrue(hintSaved, "No se pudo guardar la Hint asociada");
    }
}
