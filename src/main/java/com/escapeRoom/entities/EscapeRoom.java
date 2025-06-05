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

    private EscapeRoom() {
        InputUtils.getValidInt(idEscaperoom);
        // La validación de que name no se sobrepasa del número máximo de caracteres tendrá que hacerse fuera de aquí,
        // usando el method que hay abajo en este documento
        //InputUtils.getValidString(name);
        InputUtils.getValidList(rooms);
        this.idEscaperoom = 1;
        this.name = "";
        this.rooms = new ArrayList<>();
    }

    public static EscapeRoom getInstance() {
        if(instance == null) {
            instance = new EscapeRoom();
        }
        return instance;
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

    public void addRoom(Room room) {         this.rooms.add(room);    }

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