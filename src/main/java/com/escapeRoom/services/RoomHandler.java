package com.escapeRoom.services;

import com.escapeRoom.dao.interfaces.IGenericDAO;
import com.escapeRoom.exceptions.InvalidSearchName;
import com.escapeRoom.entities.Room;
import com.escapeRoom.exceptions.NullOrEmptyException;

import java.util.List;
import java.util.Optional;

public class RoomHandler {
    private final IGenericDAO<Room, Integer> roomDAO;

    public RoomHandler(IGenericDAO<Room, Integer>roomDAO){
        this.roomDAO = roomDAO;

    }

    public boolean createRoom(Room room){
        if(room == null || room.getName() == null || room.getIdRoom() == 0) {
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



