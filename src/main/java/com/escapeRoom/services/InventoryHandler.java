package com.escapeRoom.services;

import com.escapeRoom.entities.EscapeRoom;
import com.escapeRoom.entities.Room;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

//@NoArgsConstructor
public class InventoryHandler {
    public EscapeRoom escapeRoom = EscapeRoom.getInstance();
    List<Room> rooms = escapeRoom.getRooms();

    public void getTotalInventory() {
     BigDecimal total = new BigDecimal(0);
        for (Room room : rooms) {
            total = total.add(room.getTotalFromDecorationPrice());
        }
     total.setScale(2,RoundingMode.HALF_UP);
        System.out.println("El total de inventario es de: " + total);
    }
    public void showInventory() {
        if (escapeRoom.getRooms().isEmpty()) {         System.out.println("No hay cuartos en el scaperoom");
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
