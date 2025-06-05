package com.escapeRoom.entities;

import lombok.Setter;
import com.escapeRoom.utils.*;

import java.util.List;
import java.util.Scanner;

@Setter
public final class EscapeRoom {

    private static EscapeRoom instance;

    private int idEscaperoom;
    private String name;
    private List<Room> rooms;

    // Esto lo puse como private y alguien lo cambió a public
    // No puede ser public, el constructor está en privado para que sea un Singleton. Se crea una instancia de EscapeRoom
    // con getInstance. Así nos aseguramos que solo puede haber una instancia
    private EscapeRoom(int idEscaperoom, String name, List<Room> rooms) {
        InputUtils.getValidInt(idEscaperoom);
        // La validación de que name no se sobrepasa del número máximo de caracteres tendrá que hacerse fuera de aquí,
        // usando el method que hay abajo en este documento
        InputUtils.getValidString(name);
        InputUtils.getValidList(rooms);

        this.idEscaperoom = idEscaperoom;
        this.name = name;
        this.rooms = rooms;
    }

    // Esto lo borré por error y lo necesitamos
    public static EscapeRoom getInstance(int idEscaperoom, String name, List<Room> rooms) {
        if(instance == null) {
            instance = new EscapeRoom(idEscaperoom, name, rooms);
        }
        return instance;
    }

    public int getIdEscaperoom() {
        return idEscaperoom;
    }

    public String getName() {
        return name;
    }

    public List<Room> getRooms() {
        return rooms;
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