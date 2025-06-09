package com.escapeRoom.services;

import com.escapeRoom.controllers.RoomController;
import com.escapeRoom.dao.interfaces.IGenericDAO;
import com.escapeRoom.entities.Decoration;
import com.escapeRoom.entities.Hint;
import com.escapeRoom.entities.Room;
import com.escapeRoom.entities.enums.Difficulty;
import com.escapeRoom.entities.enums.Theme;
import com.escapeRoom.exceptions.NullOrEmptyException;
import com.escapeRoom.entities.RoomInputCollector;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class RoomHandler {
    private final IGenericDAO<Room, Integer> roomDAO;
    private static final Logger logger = Logger.getLogger(RoomController.class.getName());

    public RoomHandler(IGenericDAO<Room, Integer>roomDAO){
        this.roomDAO = roomDAO;

    }

    public boolean createRoom(Room room){

        if(room == null || room.getName() == null ) {
            throw new NullOrEmptyException("Datos de la sala inválidos");
        }

        return roomDAO.create(room);

    }

    //tema exceptions revisarlos y personalizarlos
    public Optional<Room> findRoomById(int id) {

        if(id <= 0) {
            throw new IllegalArgumentException("ID de jugador inválido");
        }
        return roomDAO.findById(id);
    }

    public List<Room> getAllRooms(){
        return roomDAO.findAll();
    }

    public boolean updateRoom(Room room) {

        if (!roomDAO.existsById(room.getIdRoom())) {
            throw new IllegalArgumentException("La sala con ID " + room.getIdRoom() + " no existe");
        }
        // Validaciones de negocio
       // validateRoom(room);

        // Actualizar en base de datos
        return roomDAO.update(room);
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

    public boolean updateRoomHint(int roomId, Hint hint) {
        return roomDAO.updateRoomHint(roomId, hint);
    }

    public boolean updateRoomDecoration(int roomId, Decoration decoration) {
        return roomDAO.updateRoomDecoration(roomId, decoration);
    }



    public boolean deleteRoom(int id){

        if (!roomDAO.existsById(id)) {
            throw new IllegalArgumentException("La sala con ID " + id + " no existe");
        }
        // Validaciones de negocio
        // validateRoom(room);

        // Actualizar en base de datos
        return roomDAO.deleteById(id);
    }
    }



