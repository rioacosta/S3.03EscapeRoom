package org.example.model.entities;

import org.example.utils.*;

import java.util.List;
import java.util.Scanner;

public final class EscapeRoom {

    private static EscapeRoom instance;

    private int idEscaperoom;
    private String name;
    private List<Room> rooms;

    private EscapeRoom(int idEscaperoom, String name, List<Room> rooms) {
        InputUtils.getValidInt(idEscaperoom);
        name = nameCharacterLimit(name);
        InputUtils.getValidList(rooms);

        this.idEscaperoom = idEscaperoom;
        this.name = name;
        this.rooms = rooms;
    }

    public static EscapeRoom getInstance(int idEscaperoom, String name, List<Room> rooms) {
        if (instance == null) {
            instance = new EscapeRoom(idEscaperoom, name, rooms);
        }
        return instance;
    }

    public int getIdEscaperoom() {
        return idEscaperoom;
    }

    public void setIdEscaperoom(int idEscaperoom) {
        this.idEscaperoom = idEscaperoom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

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