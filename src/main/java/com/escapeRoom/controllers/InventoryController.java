package com.escapeRoom.controllers;

import com.escapeRoom.manager.MenuManager;
import com.escapeRoom.services.InventoryHandler;

import java.util.Scanner;

public class InventoryController {
    private final InventoryHandler inventoryHandler;
    private final MenuManager menuManager;
    private final Scanner scanner = new Scanner(System.in);

    public InventoryController(){
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

            }
        } while (option != 0);

    }

}
