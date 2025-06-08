package com.escapeRoom.dao.mysqlimp;

import com.escapeRoom.dao.DatabaseConnection;
import com.escapeRoom.entities.Room;
import com.escapeRoom.entities.enums.Difficulty;

import java.math.BigDecimal;

public class RoomDaoTest {

    public static void testCreateRoom() {
        DatabaseConnection dbConn = DatabaseConnection.getInstance();
        MySQLRoomDAO dao = new MySQLRoomDAO(dbConn);

        // Crea un Room mínimo válido
        Room testRoom = new Room();
        testRoom.setIdEscaperoom_ref(1); // Asegúrate que este ID existe
        testRoom.setName("TEST ROOM");
        testRoom.setDifficulty(Difficulty.EASY);
        testRoom.setPrice(new BigDecimal("50.00"));

        boolean result = dao.create(testRoom);
        System.out.println("Resultado: " + result);
    }
}
