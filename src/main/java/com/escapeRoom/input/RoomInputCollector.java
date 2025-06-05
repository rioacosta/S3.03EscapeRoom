package com.escapeRoom.input;

import com.escapeRoom.entities.Room;
import com.escapeRoom.manager.MenuManager;

import java.math.BigDecimal;
import java.util.Scanner;

public class RoomInputCollector {
    private Scanner scanner;
    private MenuManager menuManager;

    public RoomInputCollector (Scanner scanner, MenuManager menuManager){
        this.scanner = scanner;
        this.menuManager = menuManager;
    }
    public Room CollectNewRoomData(){
        System.out.println("=====CREAR NUEVA SALA=====");

        System.out.println("ID de la sala");
        int idRoom = scanner.nextInt();

        System.out.println("ID del escapeRoom");
        int idEscapeRoom_ref = scanner.nextInt();

        System.out.println("Nombre de la sala");
        String name  = scanner.next();

        System.out.print("Precio: ");
        BigDecimal price = BigDecimal.valueOf(scanner.nextDouble());

        // Usar Builder directamente (sin Director)
        return new Room.Builder()
                .idRoom(idRoom)
                .idEscaperoom(idEscaperoom)
                .name(name)
                .difficulty(difficulty)
                .price(price)
                .build();


    }
}
