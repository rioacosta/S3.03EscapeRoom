package com.escapeRoom.entities;

import com.escapeRoom.entities.enums.Difficulty;
import com.escapeRoom.entities.enums.Theme;
import com.escapeRoom.manager.MenuManager;
import com.escapeRoom.roombuilder.interfaces.IRoomBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/*
public class RoomInputCollector {
    private Scanner scanner;
    private MenuManager menuManager;


    public RoomInputCollector(Scanner scanner, MenuManager menuManager) {
        this.scanner = scanner;
        this.menuManager = menuManager;
    }


    }



 */
    public class RoomInputCollector {
        private final Scanner scanner;
        private final MenuManager menuManager;

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
        String name = scanner.nextLine();

        BigDecimal price;
        while (true) {
            try {
                scanner.nextLine();
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


        Theme theme;
        do{
            theme = menuManager.selectTheme();
            if (theme == null){
                System.out.println("Selecciona un tema de sala valido");
            }
        } while (theme == null);

        List<Hint> hintsList = new ArrayList<>();
        System.out.print("¿Cuántas pistas querés agregar?: ");
        int cantidadHints = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < cantidadHints; i++) {
            System.out.print("Descripción de la pista #" + (i + 1) + ": ");
            String desc = scanner.nextLine();


            System.out.print("Precio de la pista: ");
            BigDecimal hintPrice = new BigDecimal(scanner.nextLine());

            hintsList.add(new Hint(0, 0, desc, theme, hintPrice));
        }

        List<Decoration> decorations = new ArrayList<>();
        System.out.print("¿Cuántas decoraciones querés agregar?: ");
        int cantidadDecoraciones = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < cantidadDecoraciones; i++) {
            System.out.print("Descripción de la decoración #" + (i + 1) + ": ");
            String desc = scanner.nextLine();

            System.out.println("Que tipo de material tiene la decoración?");
            String deco = scanner.nextLine();

            System.out.print("Precio de la decoración: ");
            BigDecimal decoPrice = new BigDecimal(scanner.nextLine());


            decorations.add(new Decoration(desc, deco, decoPrice));
        }
        return new ConcreteBuilder()
                .setIdEscaperoom_ref(idEscaperoom_ref)
                .setName(name)
                .setDifficulty(difficulty)
                .setPrice(price)
                .setTheme(theme)
                .setHints(hintsList)
                .setDecorations(decorations)
                .build();
    }
    public int collectRoomIdForUpdate(){
        System.out.println("Cual es el ID de la sala a modificar");
        int roomId = scanner.nextInt();
        scanner.nextLine();

        return roomId;
    }

    public int collectFieldToUpdate() {
        int updateOption;

        do {
            updateOption = menuManager.showUpdateMenu();
            if (updateOption != 0) {
                System.out.println("Selecciona un campo para modificar");
            }
        } while (updateOption == 0);

        return updateOption;
    }
    public String askForNewName(){
        System.out.println("Cual es el nuevo nombre");
        scanner.nextLine();
        return scanner.nextLine();
    }
    public BigDecimal askForNewPrice() {
        System.out.print("¿Nuevo precio? ");
        return scanner.nextBigDecimal();
    }

    public Difficulty askForNewDifficulty() {
        System.out.print("¿Nueva dificultad (EASY, MEDIUM, HARD)? ");
        String input = scanner.next().toUpperCase();
        return Difficulty.valueOf(input);
    }

    public Theme askForNewTheme() {
        System.out.print("¿Nuevo tema (DISNEY, HORROR, TERROR)? ");
        String input = scanner.next().toUpperCase();
        return Theme.valueOf(input);
    }

    public int collectRoomIdForDelete() {
        System.out.print("ID de la sala a eliminar: ");
        return scanner.nextInt();
    }

    public int collectRoomIdForElementDelete() {
        System.out.print("ID de la sala a eliminar elementos: ");
        return menuManager.getValidatedIntegerInput();
    }

    public int collectElementIdToDelete() {
        System.out.print("ID del elemento a eliminar: ");
        return menuManager.getValidatedIntegerInput();
    }
}



