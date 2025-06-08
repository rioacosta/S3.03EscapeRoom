package dao;

import com.escapeRoom.dao.DatabaseConnection;
import com.escapeRoom.dao.mysqlimp.MySQLRoomDAO;
import com.escapeRoom.entities.Room;
import com.escapeRoom.entities.RoomInputCollector;
import com.escapeRoom.manager.MenuManager;

import java.util.Scanner;

public class RoomCreationDiagnosticTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MenuManager menuManager = new MenuManager(scanner);
        RoomInputCollector inputCollector = new RoomInputCollector(scanner, menuManager);

        // 1. Primera prueba: Creación básica
        System.out.println("\n=== PRUEBA DE CONSTRUCCIÓN ===");
        Room room = inputCollector.CollectNewRoomData();

        // Mostrar datos recolectados
        System.out.println("\n=== DATOS RECOLECTADOS ===");
        System.out.println("ID Ref: " + room.getIdEscaperoom_ref());
        System.out.println("Nombre: " + room.getName());
        System.out.println("Dificultad: " + room.getDifficulty());
        System.out.println("Precio: " + room.getPrice());
        System.out.println("Cantidad de hints: " + room.getHints().size());
        System.out.println("Cantidad de decoraciones: " + room.getDecorationItems().size());

        // 2. Segunda prueba: Persistencia en BD
        System.out.println("\n=== PRUEBA DE PERSISTENCIA ===");
        MySQLRoomDAO dao = new MySQLRoomDAO(DatabaseConnection.getInstance());
        boolean success = dao.create(room);

        System.out.println("\n=== RESULTADO ===");
        System.out.println("Resultado de creación: " + (success ? "ÉXITO" : "FALLO"));

        if(success) {
            System.out.println("ID generado: " + room.getIdRoom());
        }
    }
}