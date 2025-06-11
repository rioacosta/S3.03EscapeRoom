package com.escapeRoom;

import com.escapeRoom.controllers.AppHandler;
import com.escapeRoom.dao.DatabaseConnection;
import com.escapeRoom.entities.EscapeRoom;
import com.escapeRoom.manager.MenuManager;
import com.escapeRoom.services.EscapeRoomHandler;

import static com.escapeRoom.dao.DatabaseConnection.logger;
import static java.awt.SystemColor.menu;


public class Main {

    public static void main(String[] args) {
        AppHandler.getInstance().startApp();
    }
}