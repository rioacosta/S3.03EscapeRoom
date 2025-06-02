package org.example.model.entities;

import java.util.List;

public class EscapeRoom {

    // FALTA USAR UN SINGLETON PARA ESTO !!!!!!!!

    private int idEscaperoom; // esto cómo lo conectamos a la base de datos?
    private String name;  // 45 max, gestionar
    private List<EscapeRoom> rooms;

    public int getIdEscaperoom() {
        return idEscaperoom;
    }

    public void setIdEscaperoom(int idEscaperoom) {
        this.idEscaperoom = idEscaperoom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EscapeRoom> getRooms() {
        return rooms;
    }

    public void setRooms(List<EscapeRoom> rooms) {
        this.rooms = rooms;
    }

    // Validaciones (que no esté vacío, que el tipo de dato sea el que toca (esto quizás utils),
    // que no se pase de los caracteres máximos que acepta la base de datos...

}