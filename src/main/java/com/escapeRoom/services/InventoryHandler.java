package com.escapeRoom.services;

import com.escapeRoom.entities.EscapeRoom;
import com.escapeRoom.entities.Room;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class InventoryHandler {
    public EscapeRoom escapeRoom = EscapeRoom.getInstance();
    List<Room> rooms = escapeRoom.getRooms();

    public BigDecimal getTotalInventory() {
     BigDecimal total = new BigDecimal(0);
        for (Room room : rooms) {
            total = total.add(room.getTotalFromDecorationPrice());
        }
     return total.setScale(2,RoundingMode.HALF_UP);
    }
    public void showInventory() {
        for (Room room : rooms) {
            System.out.println("La sala " + room.getName() + " contiene:" +
                    "\n Objetos de decoracion: ");
                     room.showDecorationItems();
            System.out.println("Las pistas: ");
                    room.showHintItems();
        }
    }
    /*Mostrar el inventario actualizado, mostrando las cantidades disponibles de cada elemento (salas, pistas y objetos de decoración).
    Visualizar el valor total en euros del inventario del Escape Room virtual.*/
}
