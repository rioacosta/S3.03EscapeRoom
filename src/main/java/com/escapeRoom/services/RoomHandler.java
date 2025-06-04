package com.escapeRoom.services;

import com.escapeRoom.exceptions.InvalidSearchName;
import com.escapeRoom.entities.Room;
import com.escapeRoom.roombuilder.ConcreteBuilder;

import java.util.ArrayList;
import java.util.List;

public class RoomHandler {
    private List<Room> rooms = new ArrayList<>();

    public void addRoom(Room room) {
        this.rooms.add(room);
    }

    public Room createRoom(ConcreteBuilder builder){
                builder.setName();
                builder.addHints();
                builder.addDecorations();
                builder.setDificulty();
                builder.setPrice();
                builder.build();
    }

    public void findRoomById(int id, String name) {
        rooms.stream()
                .filter(room -> room.getIdRoom() == id && room.getName().equalsIgnoreCase(name))
                .findFirst()
                .ifPresentOrElse(room -> System.out.println("Sala encontrada: " + room.getName()),
                        () -> {
                            throw new InvalidSearchName("La sala que estas buscando no se encuentra en el listado");
                        }
                );
    }
    public Room editRoom(Room idRoom){
        return rooms.stream()
                .filter(rooms->rooms.getIdRoom() == idRoom);
    }
    public void deleteRoom(int id, String name){
        rooms.removeIf(room -> room.getIdRoom() == id && room.getName().equalsIgnoreCase(name))

                .ifPresentOrElse(room -> System.out.println("Room: " + room.getName()+ " was deleted"),
                        () -> {
                            throw new InvalidSearchName("The room you were looking for was not found");
                        }
                );
    }


}
