package com.escapeRoom.services;

import com.escapeRoom.controllers.InventoryController;
import com.escapeRoom.controllers.PlayerController;
import com.escapeRoom.controllers.RoomController;
import com.escapeRoom.controllers.TicketController;
import com.escapeRoom.manager.MenuManager;


import java.util.Scanner;

public class EscapeRoomHandler {
    private static EscapeRoomHandler INSTANCE;
    private Scanner scanner;
    private MenuManager menuManager;
    private RoomController roomController;
    private InventoryController inventoryController;
    private PlayerController playerController;
    private TicketController ticketController;

    private EscapeRoomHandler() {
        this.scanner = new Scanner(System.in);
        this.menuManager = new MenuManager(scanner);
        this.roomController = new RoomController(scanner);
        this.playerController = new PlayerController(scanner);
        this.ticketController = new TicketController(scanner);
        this.inventoryController = new InventoryController();
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

                case 2 -> playerController.handlePlayerOperations();
              
                case 3 -> ticketController.handleTicketOperations();

                case 4 -> inventoryController.handleInventoryOperations();

            }

        } while (option != 0) ;


        scanner.close();
    }
}
