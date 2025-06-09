package com.escapeRoom.controllers;

import com.escapeRoom.dao.DatabaseConnection;
import com.escapeRoom.dao.interfaces.IGenericDAO;
import com.escapeRoom.dao.interfaces.IRoomDao;
import com.escapeRoom.dao.mysqlimp.MySQLDecorationDAO;
import com.escapeRoom.dao.mysqlimp.MySQLHintDAO;
import com.escapeRoom.dao.mysqlimp.MySQLRoomDAO;
import com.escapeRoom.entities.Decoration;
import com.escapeRoom.entities.Hint;
import com.escapeRoom.entities.Room;
import com.escapeRoom.entities.RoomInputCollector;
import com.escapeRoom.entities.enums.Difficulty;
import com.escapeRoom.entities.enums.Theme;
import com.escapeRoom.manager.MenuManager;
import com.escapeRoom.services.RoomHandler;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Logger;

public class RoomController {
    private RoomHandler roomHandler;
    private RoomInputCollector inputCollector;
    private MenuManager menuManager;
    private final MySQLHintDAO hintDAO;
    private final MySQLDecorationDAO decorationDAO;
    private static final Logger logger = Logger.getLogger(RoomController.class.getName());

    public RoomController(Scanner scanner) {
        // this.roomHandler = new RoomHandler(new MySQLRoomDAO(DatabaseConnection.getInstance()));
        IRoomDao roomDAO = new MySQLRoomDAO(DatabaseConnection.getInstance());
        this.roomHandler = new RoomHandler(roomDAO);
        this.menuManager = new MenuManager(scanner);
        this.inputCollector = new RoomInputCollector(scanner, menuManager);
        this.hintDAO = new MySQLHintDAO(DatabaseConnection.getInstance());
        this.decorationDAO = new MySQLDecorationDAO(DatabaseConnection.getInstance());
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

                case 5 -> deleteElementsRoom();

            }
        } while (option != 0);
    }

    private void createRoom() {
        try {
            Room room = inputCollector.CollectNewRoomData();

            MySQLRoomDAO roomDAO = new MySQLRoomDAO(DatabaseConnection.getInstance());
            MySQLHintDAO hintDAO = new MySQLHintDAO(DatabaseConnection.getInstance());
            MySQLDecorationDAO decorationDAO = new MySQLDecorationDAO(DatabaseConnection.getInstance());

            int IdRoom = roomDAO.saveAndReturnId(room);

            if (IdRoom > 0) {
                for (Hint hint : room.getHints()) {
                    hint.setIdRoom_ref(IdRoom);
                    hintDAO.create(hint);
                }

                for (Decoration decoration : room.getDecorations()) {
                    decoration.setIdRoom_ref(IdRoom);
                    decorationDAO.create(decoration);
                }

                System.out.println("Sala creada exitosamente con ID: " + IdRoom);
            } else {
                System.out.println("Error al guardar la sala.");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void updateRoom() {
        int id = inputCollector.collectRoomIdForUpdate();
        Optional<Room> existingRoom = roomHandler.findRoomById(id);

        if (existingRoom.isPresent()) {
            int option = inputCollector.collectFieldToUpdate();

            switch (option) {
                case 1 -> {
                    String newName = inputCollector.askForNewName();
                    boolean nameUpdated = roomHandler.updateRoomName(id, newName);
                    System.out.println(nameUpdated ? "Nombre actualizado." : "Error al actualizar nombre.");
                }
                case 2 -> {
                    BigDecimal newPrice = inputCollector.askForNewPrice();
                    boolean priceUpdated = roomHandler.updateRoomPrice(id, newPrice);
                    System.out.println(priceUpdated ? "Precio actualizado." : "Error al actualizar precio.");
                }
                case 3 -> {
                    Difficulty newDifficulty = inputCollector.askForNewDifficulty();
                    boolean difficultyUpdated = roomHandler.updateRoomDifficulty(id, newDifficulty);
                    System.out.println(difficultyUpdated ? "Dificultad actualizada." : "Error al actualizar nombre.");
                }

                case 4 -> {
                    Theme newTheme = inputCollector.askForNewTheme();
                    boolean themeUpdated = roomHandler.updateRoomTheme(id, newTheme);
                    System.out.println(themeUpdated ? "Tema actualizado." : "Error al actualizar precio.");
                }


                default -> System.out.println("Opción no válida.");
            }

        } else {
            System.out.println("Sala no encontrada.");
        }
    }


    private void deleteRoom() {
        try {
            int id = inputCollector.collectRoomIdForDelete();
            boolean success = roomHandler.deleteRoom(id);

            if (success) {
                System.out.println("Sala eliminada exitosamente!");
            } else {
                System.out.println("Error al eliminar la sala.");

            }
        } catch (IllegalArgumentException e) {
            System.out.println("Atención: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }


    private void listAllRooms() {
        List<Room> rooms = roomHandler.getAllRooms();
        if (rooms.isEmpty()) {
            System.out.println("No hay salas registradas actualmente.");
        } else {
            System.out.println("===== LISTADO DE SALAS =====");
            rooms.forEach(System.out::println);
        }
    }

    public void deleteElementsRoom() {
        int roomId = inputCollector.collectRoomIdForElementDelete();
        Optional<Room> existingRoom = roomHandler.findRoomById(roomId);

        System.out.println("Selecciona el tipo de elemento que querés eliminar de la sala:");


        if (existingRoom.isEmpty()) {
            System.out.println("Sala no encontrada.");
            return;
        }

        System.out.println("===== ELIMINAR ELEMENTOS DE UNA SALA =====");
        System.out.println("1. Eliminar pista");
        System.out.println("2. Eliminar decoración");
        System.out.println("0. Volver al menú anterior");

        int opcion = menuManager.getValidatedIntegerInput();

        switch (opcion) {
            case 1 -> deleteHint(roomId);
            case 2 -> deleteDecoration(roomId);
            case 0 -> System.out.println("Volviendo al menú anterior...");
            default -> System.out.println("Opción no válida.");
        }
    }
    private void deleteHint(int roomId) {
        try {
            List<Hint> pistas = hintDAO.findByRoomId(roomId);

            if (pistas.isEmpty()) {
                System.out.println("Esta sala no tiene pistas para eliminar.");
                return;
            }

            System.out.println("Pistas disponibles:");
            for (Hint hint : pistas) {
                System.out.println("ID: " + hint.getIdHint() + " → " + hint.getDescription());
            }

            int hintId = inputCollector.collectElementIdToDelete();
            boolean deleted = hintDAO.deleteById(hintId);

            if (deleted) {
                System.out.println("Pista eliminada.");
            } else {
                System.out.println("No se pudo eliminar la pista.");
            }

        } catch (Exception e) {
            System.out.println("Error al eliminar la pista: " + e.getMessage());
        }
    }
    private void deleteDecoration(int roomId) {
        try {
            List<Decoration> decoraciones = decorationDAO.findByRoomId(roomId);

            if (decoraciones.isEmpty()) {
                System.out.println("Esta sala no tiene decoraciones para eliminar.");
                return;
            }

            System.out.println("Decoraciones disponibles:");
            for (Decoration deco : decoraciones) {
                System.out.println("ID: " + deco.getIdDecoration() + " → " + deco.getDescription());
            }

            int decorationId = inputCollector.collectElementIdToDelete();
            boolean deleted = decorationDAO.deleteById(decorationId);

            if (deleted) {
                System.out.println("Decoración eliminada.");
            } else {
                System.out.println("No se pudo eliminar la decoración.");
            }

        } catch (Exception e) {
            System.out.println("Error al eliminar la decoración: " + e.getMessage());
        }
    }




}



