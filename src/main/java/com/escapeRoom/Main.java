package com.escapeRoom;

import com.escapeRoom.services.EscapeRoomHandler;

public class Main {

    public static void main(String[] args) {
        EscapeRoomHandler handler = EscapeRoomHandler.getINSTANCE();
        handler.start();

    }

}