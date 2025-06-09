package com.escapeRoom.manager;

import com.escapeRoom.entities.enums.Difficulty;
import com.escapeRoom.entities.enums.Theme;

import java.util.Scanner;

public class MenuManager {
    private Scanner scanner;

    public MenuManager(Scanner scanner){
        this.scanner = scanner;
    }

    public int showMainMenu(){
        System.out.println("""
                ======ESCAPE ROOM MANAGEMENT=====
                    1- GESTIONAR SALAS
                    2- GESTIONAR JUGADORES
                    3- GESTIONAR TICKETS
                    4- GESTIONAR EL INVENTARIO
                    0- SALIR
                Seleccionar una opción:
                """);
        return scanner.nextInt();
    }

    public int showRoomMenu() {
        System.out.println("""
                    ===== GESTIÓN DE SALAS =====
                        1. Crear nueva sala
                        2. Listar salas
                        3. Modificar sala
                        4. Eliminar sala
                        0. Volver al menú principal
                        Selecciona opción:
                      """);
        
        return scanner.nextInt();
    }

    public int showUpdateMenu() {

        System.out.println("""
                ===== MODIFICAR SALAS =====
                    1. Cambiar nombre
                    2. Cambiar precio
                    3. Cambiar dificultad
                    4. Cambiar decoración
                    5. Cambiar tema
                    0. Volver al menú principal
                    Selecciona opción:
                """);

        return scanner.nextInt();

    }

    public int showPlayersMenu(){
    System.out.println("""
            ====GESTION DE JUGADORES====
                1.Otorgar certificados
                2.Subscribir a la newsletter
            """);
       return scanner.nextInt();
    }

    public String showTicketMenu() {
        System.out.println("""
                        1 - Crear ticket
                        2 - Borrar ticket
                        3 - Calcular beneficio por venta de tickets
                        4 - Volver al menú principal""");

        return scanner.nextLine();
    }

    public int showInventoryMenu(){
        System.out.println("""
            ====GESTION DE INVENTARIO====
                1.Mostrar el inventario
                2.Mostrar el  valor total del inventario
            """);
        return scanner.nextInt();
    }
    public Difficulty selectDifficulty() {   //----------------Este metodo deberia ir aqui o en el controller de room???
        System.out.println("Selecciona dificultad:");
        Difficulty[] difficulties = Difficulty.values();
        for (int i = 0; i < difficulties.length; i++) {
            System.out.println((i + 1) + ". " + difficulties[i]);
        }
        System.out.print("Opción: ");
        int choice = scanner.nextInt() - 1;
        return difficulties[choice];
    }

    public Theme selectTheme(){
        System.out.println("Selecciona el tema");
        Theme[] themes = Theme.values();
        for (int i = 0; i <themes.length; i++){
            System.out.println((i + 1) + ". "+themes[i]);
        }
        System.out.println("Opcion: ");
        int choice = scanner.nextInt() - 1;
        return themes[choice];
    }
}
