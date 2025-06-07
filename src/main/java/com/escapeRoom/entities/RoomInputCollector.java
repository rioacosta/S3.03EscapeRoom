package com.escapeRoom.entities;

import com.escapeRoom.entities.enums.Difficulty;
import com.escapeRoom.manager.MenuManager;
import com.escapeRoom.roombuilder.interfaces.IRoomBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RoomInputCollector {
    private Scanner scanner;
    private MenuManager menuManager;

    public RoomInputCollector(Scanner scanner, MenuManager menuManager) {
        this.scanner = scanner;
        this.menuManager = menuManager;
    }

    public Room CollectNewRoomData() {
        System.out.println("=====CREAR NUEVA SALA=====");

        System.out.println("ID del escapeRoom");
        int idEscapeRoom_ref = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Nombre de la sala");
        String name = scanner.next();

        System.out.print("Precio: ");
        BigDecimal price = BigDecimal.valueOf(scanner.nextDouble());

        Difficulty difficulty = menuManager.selectDifficulty();
        scanner.nextLine();

        List<Hint> hintsList = new ArrayList<>();
        System.out.print("¿Cuántas pistas querés agregar?: ");
        int cantidadHints = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < cantidadHints; i++) {
            System.out.print("Descripción de la pista #" + (i + 1) + ": ");
            String desc = scanner.nextLine();
            hintsList.add(new Hint(desc));
        }


        List<Decoration> decorations = new ArrayList<>();
        System.out.print("¿Cuántas decoraciones querés agregar?: ");
        int cantidadDecoraciones = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < cantidadDecoraciones; i++) {
            System.out.print("Descripción de la decoración #" + (i + 1) + ": ");
            String desc = scanner.nextLine();

            System.out.print("Precio de la decoración: ");
            BigDecimal decoPrice = new BigDecimal(scanner.nextLine());

            decorations.add(new Decoration(desc, decoPrice));
        }
        return new ConcreteBuilder()
                .setIdEscapeRoom_ref(idEscapeRoom_ref)
                .setName(name)
                .setHints(hintsList)
                .setDecorations(decorations)
                .setDificulty(difficulty)
                .setPrice(price)
                .build();
    }
    public int collectRoomIdForUpdate() {
        System.out.print("ID de la sala a modificar: ");
        return scanner.nextInt();
    }

    public int collectRoomIdForDelete() {
        System.out.print("ID de la sala a eliminar: ");
        return scanner.nextInt();
    }


}
