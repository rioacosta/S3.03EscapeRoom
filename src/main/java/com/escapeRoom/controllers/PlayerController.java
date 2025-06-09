package com.escapeRoom.controllers;

import com.escapeRoom.dao.DatabaseConnection;
import com.escapeRoom.dao.mysqlimp.MySQLPlayerDAO;
import com.escapeRoom.entities.EscapeRoom;
import com.escapeRoom.entities.Player;
import com.escapeRoom.exceptions.NullOrEmptyException;
import com.escapeRoom.manager.MenuManager;
import com.escapeRoom.services.PlayerHandler;

import java.util.Optional;
import java.util.Scanner;

public class PlayerController {
    private PlayerHandler playerHandler;
    private MenuManager menuManager;
    private final EscapeRoom escapeRoom = EscapeRoom.getInstance();
    private Scanner scanner = new Scanner(System.in);

    public PlayerController(Scanner scanner){
        this.playerHandler = new PlayerHandler(new MySQLPlayerDAO(DatabaseConnection.getInstance()));
        this.menuManager = new MenuManager(scanner);
    }

    public void handlePlayerOperations() {
        int option;
        do {
            option = menuManager.showRoomMenu();

            switch (option) {
                case 1 -> playerHandler.subscribePlayer(getPlayer());

                case 2 -> playerHandler.assignCertificateToPlayer(getPlayer().getName());

                case 3 -> playerHandler.unsbscribePlayer(getPlayer());

                case 4 -> playerHandler.notifySubscribers(scanner.nextLine());
            }
        } while (option != 0);
    }

    public Player getPlayer(){
        System.out.print("Introduzca el nombre del jugador: ");
        String playerName = scanner.nextLine();
        //scanner.nextLine(); ------------sera necesario limpiar buffer?

        Optional<Player> playerOpt = playerHandler.findPlayerByName(playerName);
        if (playerOpt.isEmpty()) {
            throw new NullOrEmptyException("Jugador no encontrado por nombre: " + playerName);
        }
        return playerOpt.get();
    }

}
