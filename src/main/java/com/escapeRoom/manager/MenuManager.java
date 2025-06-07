package com.escapeRoom.manager;

import com.escapeRoom.entities.enums.Difficulty;

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
    public int showPlayersMenu(){
    System.out.println("""
            ====GESTION DE JUGADORES====
                1.Otorgar certificados
                2.Subscribir a la newsletter
            """);
       return scanner.nextInt();
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
}
