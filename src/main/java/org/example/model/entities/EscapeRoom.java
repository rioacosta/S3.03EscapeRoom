package org.example.model.entities;

import lombok.Getter;
import lombok.Setter;
import org.example.utils.*;

import java.util.List;
import java.util.Scanner;

@Getter
@Setter
public final class EscapeRoom {

    private static EscapeRoom instance;

    private int idEscaperoom;
    private String name;
    private List<Room> rooms;

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

    // De los getters and setters se está encargando Lombok

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