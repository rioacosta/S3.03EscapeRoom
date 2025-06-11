package com.escapeRoom.manager;

import com.escapeRoom.entities.enums.Difficulty;
import com.escapeRoom.entities.enums.Theme;

import java.util.Scanner;

public class MenuManager {
    private Scanner scanner;

    public MenuManager(Scanner scanner) {
        this.scanner = scanner;
    }

    public int showMainMenu() {
        System.out.println("""
                ======ESCAPE ROOM MANAGEMENT=====
                    1- GESTIONAR SALAS
                    2- GESTIONAR JUGADORES
                    3- GESTIONAR TICKETS
                    4- GESTIONAR EL INVENTARIO
                    0- SALIR
                Seleccionar una opción:
                """);

        return getValidatedIntegerInput();
    }

    public int showRoomMenu() {
        System.out.println("""
                    ===== GESTIÓN DE SALAS =====
                        1. Crear nueva sala
                        2. Listar salas
                        3. Modificar sala
                        4. Eliminar sala
                        5. Eliminar elementos sala
                        0. Volver al menú principal
                        Selecciona opción:
                    """);

        return getValidatedIntegerInput();
    }

    public int showUpdateMenu() {
        System.out.println("""
                ===== MODIFICAR SALAS =====
                    1. Cambiar nombre
                    2. Cambiar precio
                    3. Cambiar dificultad
                    4. Cambiar tema
                    0. Volver al menú principal
                    Selecciona opción:
                """);

        return getValidatedIntegerInput();
    }

    public int showPlayersMenu() {
        System.out.println("""
                ====GESTION DE JUGADORES====
                    1. Agregar jugador
                    2. Otorgar certificados
                    3. Suscribir a la newsletter
                    4. Desuscribir jugador de la newsletter
                    5. Notificar a los jugadores de un evento
                    6. Listar jugadores
                    0. Volver al menu principal
                """);

        return getValidatedIntegerInput();
    }

    public int showTicketMenu() {
        System.out.println("""
                ====GESTION DE TICKETS====
                    1 - Crear ticket
                    2 - Borrar ticket
                    3 - Calcular beneficio por venta de tickets
                    0 - Volver al menú principal
                """);

        return getValidatedIntegerInput();
    }

    public int showInventoryMenu() {

        System.out.println("""
                ====GESTION DE INVENTARIO====
                    1. Mostrar el valor total del inventario
                    2. Mostrar el inventario
                    0. Volver al menu principal
                """);

        return getValidatedIntegerInput();
    }

    public Difficulty selectDifficulty() {
        System.out.println("Selecciona dificultad:");
        Difficulty[] difficulties = Difficulty.values();
        for (int i = 0; i < difficulties.length; i++) {
            System.out.println((i + 1) + ". " + difficulties[i]);
        }
        System.out.print("Opción: ");
        int choice = getValidatedIntegerInput();
        if (choice > 0 && choice <= difficulties.length) {
            return difficulties[choice - 1];
        } else {
            System.out.println("Opción inválida. Intente nuevamente.");
            return selectDifficulty();
        }
    }

    public Theme selectTheme() {
        System.out.println("Selecciona el tema:");
        Theme[] themes = Theme.values();
        for (int i = 0; i < themes.length; i++) {
            System.out.println((i + 1) + ". " + themes[i]);
        }
        System.out.print("Opción: ");
        int choice = getValidatedIntegerInput();
        if (choice > 0 && choice <= themes.length) {
            return themes[choice - 1];
        } else {
            System.out.println("Opción inválida. Intente nuevamente.");
            return selectTheme();
        }
    }


    public int getValidatedIntegerInput() {

        while (true) {
            System.out.print("> ");
            String line = scanner.nextLine();

            if (line.trim().isEmpty()) {
                continue;
            }

            try {
                int input = Integer.parseInt(line.trim());
                return input;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Introduzca un número entero:");
            }
        }
    }

}