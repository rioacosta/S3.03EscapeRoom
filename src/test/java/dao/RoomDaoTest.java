package dao;

import com.escapeRoom.dao.DatabaseConnection;
import com.escapeRoom.dao.mysqlimp.MySQLRoomDAO;
import com.escapeRoom.entities.Room;
import com.escapeRoom.entities.enums.Difficulty;
import com.escapeRoom.entities.enums.Theme;

import java.math.BigDecimal;

public class RoomDaoTest {

    public static void main(String[] args) {
        testCreateRoom();
    }

    public static void testCreateRoom() {
        DatabaseConnection dbConn = DatabaseConnection.getInstance();
        MySQLRoomDAO dao = new MySQLRoomDAO(dbConn);


        Room testRoom = new Room();
        testRoom.setIdEscaperoom_ref(1);
        testRoom.setName("TEST ROOM");
        testRoom.setDifficulty(Difficulty.EASY);
        testRoom.setPrice(new BigDecimal("50.00"));
        testRoom.setTheme(Theme.HORROR);

        boolean result = dao.create(testRoom);
        System.out.println("Resultado: " + result);
    }
}
