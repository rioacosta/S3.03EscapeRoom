package com.escapeRoom.services;

import com.escapeRoom.dao.interfaces.IRoomDao;
import com.escapeRoom.entities.Decoration;
import com.escapeRoom.entities.Hint;
import com.escapeRoom.entities.Room;
import com.escapeRoom.entities.enums.Difficulty;
import com.escapeRoom.entities.enums.Theme;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RoomHandler {

    private final IRoomDao roomDAO;

    public RoomHandler(IRoomDao roomDAO) {
        this.roomDAO = roomDAO;
    }

    public Optional<Room> findRoomById(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException("ID de jugador inválido");
        }
        return roomDAO.findById(id);
    }

    public List<Room> getAllRooms(){
        return roomDAO.findAll();
    }

    public List<String> getAllRoomsFormatted() {
        List<Room> rooms = getAllRooms();
        return rooms.stream()
                .map(this::formatRoom)
                .collect(Collectors.toList());
    }

    private String formatRoom(Room room) {
        return String.format("""
                    Habitación: %s
                    ID: %d
                    Dificultad: %s
                    Tema: %s
                    Precio: %.2f €
                    Pistas:
                    %s
                    Decoraciones:
                    %s
                """,
                room.getName(),
                room.getIdRoom(),
                room.getDifficulty(),
                room.getTheme(),
                room.getPrice(),
                formatHints(room.getHints()),
                formatDecorations(room.getDecorations()));
    }

    private String formatHints(List<Hint> hints) {
        return hints.stream()
                .map(hint -> String.format("- \"%s\" Tema: %s)", hint.getDescription(), hint.getTheme()))
                .collect(Collectors.joining("\n", "", ""));
    }

    private String formatDecorations(List<Decoration> decorations) {
        return decorations.stream()
                .map(deco -> String.format("- \"%s\" Material: %s, Valor: %.2f €)", deco.getDescription(),
                 deco.getMaterial(), deco.getPrice()))
                .collect(Collectors.joining("\n", "", ""));
    }

    public boolean updateRoomName(int roomId, String newName) {
        return roomDAO.updateRoomName(roomId, newName);
    }

    public boolean updateRoomPrice(int roomId, BigDecimal newPrice) {
        return roomDAO.updateRoomPrice(roomId, newPrice);
    }

    public boolean updateRoomDifficulty(int roomId, Difficulty difficulty) {
        return roomDAO.updateRoomDifficulty(roomId, difficulty);
    }

    public boolean updateRoomTheme(int roomId, Theme theme) {
        return roomDAO.updateRoomTheme(roomId, theme);
    }

    public boolean deleteRoom(int id) {
        if (!roomDAO.existsById(id)) {
            throw new IllegalArgumentException("La sala con ID " + id + " no existe");
        }
        return roomDAO.deleteById(id);
    }

}