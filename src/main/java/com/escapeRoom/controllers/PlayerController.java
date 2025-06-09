package com.escapeRoom.controllers;

import com.escapeRoom.dao.DatabaseConnection;
import com.escapeRoom.dao.mysqlimp.MySQLPlayerDAO;
import com.escapeRoom.entities.Player;
import com.escapeRoom.manager.MenuManager;
import com.escapeRoom.services.PlayerHandler;

import java.util.Scanner;

public class PlayerController {
    private final PlayerHandler playerHandler;
    private final MenuManager menuManager;
    private final Scanner scanner = new Scanner(System.in);

    public PlayerController(Scanner scanner){
        this.playerHandler = new PlayerHandler(new MySQLPlayerDAO(DatabaseConnection.getInstance()));
        this.menuManager = new MenuManager(scanner);
    }

    public void handlePlayerOperations() {
        int option;
        do {
            option = menuManager.showPlayersMenu();

            switch (option) {
                case 1 -> playerHandler.assignCertificateToPlayer(getPlayerData().getName());

                case 2 -> playerHandler.subscribePlayer(getPlayerData());

                case 3 -> playerHandler.unsbscribePlayer(getPlayerData());

                case 4 -> {       System.out.println("Escribe a continuacion el mensaje que quieres compartir: ");
                        playerHandler.notifySubscribers(scanner.nextLine());
                }
                case 5 -> playerHandler.showAllPlayers();

            }
        } while (option != 0);

    }

    public Player getPlayerData(){
        String playerName;
        do {
            System.out.print("Introduzca el nombre del jugador: ");
            playerName = scanner.nextLine();
            if (playerName.trim().isEmpty()) {
                System.out.println("El nombre no puede estar vacío. Intente de nuevo.");
            }
        } while (playerName.trim().isEmpty());

        String playerEmail;
        do {
            System.out.print("Introduzca el email del jugador: ");
            playerEmail = scanner.nextLine();
            if (!playerEmail.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                System.out.println("El correo electrónico no es válido. Intente de nuevo.");
            }
        } while (!playerEmail.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"));

        return new Player(playerName, playerEmail);
    }

}
