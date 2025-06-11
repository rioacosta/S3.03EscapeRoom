package com.escapeRoom.controllers;

import com.escapeRoom.dao.DatabaseConnection;
import com.escapeRoom.services.EscapeRoomHandler;

import static com.escapeRoom.dao.DatabaseConnection.logger;

public class AppController {

    private static AppController instance;

    public static AppController getInstance() {
        if(instance == null) {
            instance = new AppController();
        }
        return instance;
    }

    public void startApp() {
        try {
            DatabaseConnection.getInstance().getConnection();
            logger.info("Sistema iniciado correctamente");

            EscapeRoomHandler handler = EscapeRoomHandler.getINSTANCE();
            handler.start();

        } catch (Exception e) {
            logger.severe("Error al iniciar la aplicación: " + e.getMessage());
            System.err.println("❌ Error al conectar con la base de datos.");
            System.err.println("Verifique que MySQL esté ejecutándose y que las credenciales sean correctas.");
            System.err.println("Error: " + e.getMessage());
        } finally {
            try {
                DatabaseConnection.getInstance().closeConnection();
            } catch (Exception e) {
                logger.severe("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}


