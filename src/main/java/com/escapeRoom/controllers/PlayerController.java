package com.escapeRoom.controllers;

import com.escapeRoom.dao.DatabaseConnection;
import com.escapeRoom.dao.mysqlimp.MySQLPlayerDAO;
import com.escapeRoom.entities.EscapeRoom;
import com.escapeRoom.entities.Player;
import com.escapeRoom.entities.RoomInputCollector;
import com.escapeRoom.exceptions.NullOrEmptyException;
import com.escapeRoom.manager.MenuManager;
import com.escapeRoom.services.PlayerHandler;

import java.util.Optional;
import java.util.Scanner;

public class PlayerController {   //---------------WORK IN PROGRESS----------------------------
    /*
    private PlayerHandler playerHandler;
    private RoomInputCollector inputCollector;
    private MenuManager menuManager;
    private EscapeRoom escapeRoom = EscapeRoom.getInstance();
    private Scanner scanner = new Scanner(System.in);

    public PlayerController(Scanner scanner){
        this.playerHandler = new PlayerHandler(new MySQLPlayerDAO(DatabaseConnection.getInstance()));
        this.menuManager = new MenuManager(scanner);
        this.inputCollector = new RoomInputCollector(scanner, menuManager);
    }
    public void handlePlayerOperations() {
        int option;
        do {
            option = menuManager.showRoomMenu();

            switch (option) {
                case 1 -> playerHandler.subscribePlayer();

                case 2 -> playerHandler.assignCertificateToPlayer();
            }
        } while (option != 0);
    }
    public Player getPlayerForSuscribe(String name) {

        Optional<Player> player;
        try {
            player = playerHandler.findPlayerByName(name);
            escapeRoom.getPlayers().get()
        } catch (RuntimeException e) {
            throw new NullOrEmptyException("El jugador no ha sido encontrado y no puede suscribirlo");
        }
        return player.isPresent();
    }
    public Player getPlayerForCertificate(){
        return
    }
    */
}
