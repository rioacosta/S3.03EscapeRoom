package com.escapeRoom.controllers;

import com.escapeRoom.dao.DatabaseConnection;
import com.escapeRoom.dao.mysqlimp.MySQLRoomDAO;
import com.escapeRoom.entities.Room;
import com.escapeRoom.entities.RoomInputCollector;
import com.escapeRoom.manager.MenuManager;
import com.escapeRoom.services.RoomHandler;


import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class RoomController {
    private RoomHandler roomHandler;
    private RoomInputCollector inputCollector;
    private MenuManager menuManager;

    public RoomController(Scanner scanner) {
        this.roomHandler = new RoomHandler(new MySQLRoomDAO(DatabaseConnection.getInstance()));
        this.menuManager = new MenuManager(scanner);
        this.inputCollector = new RoomInputCollector(scanner, menuManager);
    }

    public void handleRoomOperations() {
        int option;
        do {
            option = menuManager.showRoomMenu();

            switch (option) {
                case 1 -> createRoom();

                case 2 -> listAllRooms();

                case 3 -> updateRoom();

                case 4 -> deleteRoom();

            }
        } while (option != 0);
    }

    private void createRoom() {
        try {
            Room room = inputCollector.CollectNewRoomData();
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
        Optional<Room> existingRoom = roomHandler.findRoomById(id);
        if (existingRoom.isPresent()) {
            Room room = existingRoom.get();
            // Lógica para modificar room...
        } else {
            System.out.println("Sala no encontrada.");
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
    private void listAllRooms(){
        List<Room> rooms = roomHandler.getAllRooms();
        if (rooms.isEmpty()) {
            System.out.println("No hay salas registradas actualmente.");
        } else {
            System.out.println("===== LISTADO DE SALAS =====");
            rooms.forEach(System.out::println);
        }
    }

}
