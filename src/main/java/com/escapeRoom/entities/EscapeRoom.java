package com.escapeRoom.entities;

import lombok.Setter;
import com.escapeRoom.utils.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Setter
public final class EscapeRoom {

    private static EscapeRoom instance;
    private int idEscaperoom;
    private String name;
    private List<Room> rooms;
    private ArrayList<Player> players;

    public EscapeRoom() {
        InputUtils.getValidInt(idEscaperoom);
        this.idEscaperoom = 1;
        this.name = "";
        this.rooms = new ArrayList<>();
        this.players = new ArrayList<>();

    }

    public static EscapeRoom getInstance() {
        if(instance == null) {
            instance = new EscapeRoom();
        }
        return instance;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
    public int getIdEscaperoom() {
        return idEscaperoom;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = nameCharacterLimit(name);
    }
    public void setName(int name) { this.name = String.valueOf(name);}
    public List<Room> getRooms() {
        return rooms;
    }
    public void addRoom(Room room) {    this.rooms.add(room);    }

    @Override
    public String toString() {
        return this.name + ", " + this.rooms + ", id " + this.idEscaperoom;
    }

    private String nameCharacterLimit(String name) {
        Scanner scanner = new Scanner(System.in);

        while (name.length() > 45) {
            System.out.println("El nombre del escape room no puede tener más de 45 caracteres. Introduce un nombre válido");
            name = scanner.nextLine();
        }
        return name;
    }

}