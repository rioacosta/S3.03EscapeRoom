package com.escapeRoom.controllers;

import com.escapeRoom.dao.DatabaseConnection;
import com.escapeRoom.dao.mysqlimp.MySQLPlayerDAO;
import com.escapeRoom.entities.Player;
import com.escapeRoom.exceptions.NullOrEmptyException;
import com.escapeRoom.manager.MenuManager;
import com.escapeRoom.services.PlayerHandler;

import java.util.Optional;
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
        int option = -1;
        do {
            try {
            option = menuManager.showPlayersMenu();

            switch (option) {
                case 1 -> playerHandler.createPlayer(getPlayerData());

                case 2 -> playerHandler.assignCertificateToPlayer(getPlayerForCertificate(), getCertification());

                case 3 -> playerHandler.subscribePlayer(getPlayerForSuscribe());

                case 4 -> playerHandler.unsbscribePlayer(getPlayerData());

                case 5 -> {       System.out.println("Escribe a continuacion el mensaje que quieres compartir: ");
                        playerHandler.notifySubscribers(scanner.nextLine());
                }
                case 6 ->  playerHandler.showAllPlayers();


            }
            } catch (RuntimeException  e) {
                System.err.println(e.getMessage());
            }
        } while (option != 0);
    }


    public Player getPlayerData(){
        String playerName;
        do {
            System.out.print("Introduzca el nombre del jugador: ");
            playerName = scanner.nextLine();
            if (playerName.trim().isEmpty()) {
                System.err.println("El nombre no puede estar vacío. Intente de nuevo.");
            }
        } while (playerName.trim().isEmpty());

        String playerEmail;
        do {
            System.out.print("Introduzca el email del jugador: ");
            playerEmail = scanner.nextLine();
            if (!playerEmail.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                System.err.println("El correo electrónico no es válido. Intente de nuevo.");
            }
        } while (!playerEmail.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"));

        return new Player(playerName, playerEmail);
    }


    public String getPlayerForCertificate() {
        System.out.print("Introduzca el nombre del jugador: ");
        String playerName = scanner.nextLine();

        Optional<Player> playerOpt = playerHandler.findPlayerByName(playerName);
        if (playerOpt.isEmpty()) {
            throw new NullOrEmptyException("Jugador no encontrado con nombre: " + playerName);
        }
        return playerOpt.get().getName();
    }

    public Player getPlayerForSuscribe() {
        System.out.print("Introduzca nombre del jugador: ");
        String name = scanner.nextLine();

        Optional<Player> playerOpt = playerHandler.findPlayerByName(name);
        if (playerOpt.isEmpty()) {
            throw new NullOrEmptyException("El jugador no ha sido encontrado y no puede suscribirlo");
        }
        return playerOpt.get();
    }

    public String getCertification() {
        System.out.print("Introduzca el merito a certificar del jugador: ");
        return scanner.nextLine();
    }
}
