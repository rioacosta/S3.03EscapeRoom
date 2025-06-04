package com.escapeRoom.services;

import com.escapeRoom.entities.Room;

import java.util.Scanner;

public class EscapeRoomHandler {
    private static EscapeRoomHandler INSTANCE;
    private Scanner scanner;
    private MenuManager menuManager;
    private RoomController roomController;
    private PlayerController playerController;
    private TicketController ticketController;

    private EscapeRoomHandler() {
        this.scanner = new Scanner(System.in);
        this.menuManager = new MenuManager(scanner);
        this.roomController = new RoomController(scanner);
        this.playerController = new PlayerController(scanner);
        this.ticketController = new TicketController(scanner);
    }

    public static EscapeRoomHandler getINSTANCE() {
        if (INSTANCE == null) {
            synchronized (EscapeRoomHandler.class) {
                if (INSTANCE == null) {
                    INSTANCE = new EscapeRoomHandler();
                }
            }
        }
        return INSTANCE;
    }


    public void start() {
        int option;
        do {
            option = menuManager.showMainMenu();

            switch (option) {
                case 1 -> roomController.handleRoomOperations();
                break;
                case 2 -> playerController.handleRoomOperations();
                break;
                case 3 -> ticketController.handleRoomOperations();

            }

        } while (option != 0) ;


        scanner.close();
    }
}
