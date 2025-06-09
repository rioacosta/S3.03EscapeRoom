package com.escapeRoom.controllers;

import com.escapeRoom.manager.MenuManager;
import com.escapeRoom.services.InventoryHandler;

import java.util.Scanner;

public class InventoryController {
    private final InventoryHandler inventoryHandler;
    private final MenuManager menuManager;
    private final Scanner scanner = new Scanner(System.in);

    public InventoryController(){ //Scanner scanner) {
        this.inventoryHandler = new InventoryHandler();
        this.menuManager = new MenuManager(scanner);
    }

    public void handleInventoryOperations() {
        int option;
        do {
            option = menuManager.showInventoryMenu();

            switch (option) {
                case 1 -> inventoryHandler.getTotalInventory();

                case 2 -> inventoryHandler.showInventory();

                /*case 3 -> playerHandler.unsbscribePlayer(getPlayerData());

                case 4 -> {       System.out.println("Escribe a continuacion el mensaje que quieres compartir: ");
                    playerHandler.notifySubscribers(scanner.nextLine());
                }
                case 5 -> playerHandler.showAllPlayers();*/

            }
        } while (option != 0);

    }

}
