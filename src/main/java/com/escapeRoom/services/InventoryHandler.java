package com.escapeRoom.services;

import com.escapeRoom.dao.DatabaseConnection;
import com.escapeRoom.dao.mysqlimp.MySQLRoomDAO;
import com.escapeRoom.entities.EscapeRoom;
import com.escapeRoom.entities.Room;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@NoArgsConstructor
public class InventoryHandler {
    public EscapeRoom escapeRoom = EscapeRoom.getInstance();
    List<Room> rooms = escapeRoom.getRooms();
    MySQLRoomDAO roomDao = new MySQLRoomDAO(DatabaseConnection.getInstance());

    public void getTotalInventory() {
        List<Room> rooms = roomDao.findAll();

        BigDecimal total = BigDecimal.ZERO;
        for (Room room : rooms) {
            total = total.add(room.getTotalFromDecorationPrice());
        }

        System.out.println("El total de inventario es de: " + total.setScale(2, RoundingMode.HALF_UP));
    }

    public void showInventory() {
        List<Room> rooms = roomDao.findAll();

        if (rooms.isEmpty()) {         System.out.println("No hay cuartos en el scaperoom\n");
        }
        for (Room room : rooms) {
            System.out.println("La sala " + room.getName() + " contiene:" +
                    "\n Objetos de decoracion: ");
                     room.showDecorationItems();
            System.out.println("Las pistas: ");
                    room.showHintItems();
        }
    }
}
