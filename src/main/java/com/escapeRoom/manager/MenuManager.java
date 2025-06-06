package com.escapeRoom.manager;

import com.escapeRoom.entities.enums.Difficulty;

import java.util.Scanner;

public class MenuManager {
    private Scanner scanner;

    public MenuManager(Scanner scanner){
        this.scanner = scanner;
    }

    public int showMainMenu(){
        System.out.println("======ESCAPE ROOM MANAGEMENT=====");
        System.out.println("1- GESTIONAR SALAS");
        System.out.println("2- GESTIONAR JUGADORES");
        System.out.println("3- GESTIONAR TICKETS");
        System.out.println("0- SALIR");
        System.out.println("Seleccionar una opción: ");

        return scanner.nextInt();
    }

    public int showRoomMenu() {
        System.out.println("===== GESTIÓN DE SALAS =====");
        System.out.println("1. Crear nueva sala");
        System.out.println("2. Listar salas");
        System.out.println("3. Modificar sala");
        System.out.println("4. Eliminar sala");
        System.out.println("0. Volver al menú principal");
        System.out.print("Selecciona opción: ");
        return scanner.nextInt();
    }
    public Difficulty selectDifficulty() {
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
