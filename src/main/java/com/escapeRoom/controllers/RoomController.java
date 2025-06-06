package com.escapeRoom.controllers;

import com.escapeRoom.entities.Room;
import com.escapeRoom.input.RoomInputCollector;
import com.escapeRoom.manager.MenuManager;
import com.escapeRoom.services.RoomHandler;

import java.util.Scanner;

public class RoomController {
    private RoomHandler roomHandler;
    private RoomInputCollector inputCollector;
    private MenuManager menuManager;

    public RoomController(Scanner scanner) {
        this.roomHandler = new RoomHandler(scanner);
        this.menuManager = new MenuManager(scanner);
        this.inputCollector = new RoomInputCollector(scanner, menuManager);
    }

    public void handleRoomOperations() {
        int option;
        do {
            option = menuManager.showRoomMenu();

            switch (option) {
                case 1:
                    createRoom();
                    break;
                case 2:
                    roomHandler.listAllRooms();
                    break;
                case 3:
                    updateRoom();
                    break;
                case 4:
                    deleteRoom();
                    break;
            }
        } while (option != 0);
    }

    private void createRoom() {
        try {
            Room room = inputCollector.collectNewRoomData();
            boolean success = roomHandler.createRoom(room);

            if (success) {
                System.out.println("Sala creada exitosamente!");
            } else {
                System.out.println("Error al crear la sala.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void updateRoom() {
        int id = inputCollector.collectRoomIdForUpdate();
        Room existingRoom = roomHandler.getRoomById(id);

        if (existingRoom != null) {
            // Lógica para modificar...
        } else {
            System.out.println("ala no encontrada.");
        }
    }

    private void deleteRoom() {
        int id = inputCollector.collectRoomIdForDelete();
        boolean success = roomHandler.deleteRoom(id);

        if (success) {
            System.out.println("Sala eliminada exitosamente!");
        } else {
            System.out.println("Error al eliminar la sala.");
        }
    }
}
