package com.escapeRoom.services;

import com.escapeRoom.entities.Room;
import com.escapeRoom.manager.MenuManager;


import java.util.Scanner;

public class EscapeRoomHandler {
    private static EscapeRoomHandler INSTANCE;
    private Scanner scanner;
    private MenuManager menuManager;
    private RoomHandler roomHandler;
    private PlayerHandler playerHandler;
    private TicketHandler ticketHandler;

    private EscapeRoomHandler() {
        this.scanner = new Scanner(System.in);
        this.menuManager = new MenuManager(scanner);
        this.roomHandler = new RoomHandler(scanner);
        this.playerHandler = new PlayerHandler(scanner);
        this.ticketHandler = new TicketHandler(scanner);
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
                case 1 -> roomHandler.handleRoomOperations();
                break;
                case 2 -> playerHandler.handleRoomOperations();
                break;
                case 3 -> ticketHandler.handleRoomOperations();

            }

        } while (option != 0) ;


        scanner.close();
    }
}
