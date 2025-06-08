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
        int idEscaperoom_ref = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Nombre de la sala");
        String name = scanner.next();

        BigDecimal price;
        while (true) {
            try {
                System.out.print("Precio base: ");
                String input = scanner.nextLine().trim();

                if (input.isEmpty()) {
                    throw new IllegalArgumentException("El precio no puede estar vacío.");
                }


                input = input.replace(",", ".");

                price = new BigDecimal(input);

                if (price.compareTo(BigDecimal.ZERO) <= 0) {
                    throw new IllegalArgumentException("El precio debe ser mayor a 0.");
                }

                break;
            } catch (NumberFormatException e) {
                System.out.println("Formato inválido. Usá números como 12.5");
            } catch (IllegalArgumentException e) {
                System.out.println("  " + e.getMessage());
            }
        }



        Difficulty difficulty;
        do {
            difficulty = menuManager.selectDifficulty();
            if (difficulty == null) {
                System.out.println("Seleccione una dificultad válida");
            }
        } while (difficulty == null);




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
                .setIdEscaperoom_ref(idEscaperoom_ref)
                .setName(name)
                .setDifficulty(difficulty)
                .setPrice(price)
                .setHints(hintsList)
                .setDecorations(decorations)
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
