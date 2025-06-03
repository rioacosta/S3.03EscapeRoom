package org.example.model.entities;

import org.example.model.entities.enums.Difficulty;

import java.math.BigDecimal;

public class Room {

    private int idRoom;
    private int idEscaperoom_ref;
    private String name;  // 45 max, gestionar
    private Difficulty difficulty;
    private BigDecimal price;  // 2 decimales max, gestionar

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public int getIdEscaperoom_ref() {
        return idEscaperoom_ref;
    }

    public void setIdEscaperoom_ref(int idEscaperoom_ref) {
        this.idEscaperoom_ref = idEscaperoom_ref;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Id de la habitación: " + idRoom + "\nNombre: " + name + "\nDificultad: " + difficulty.getDescription()
                + "\nPrecio: " + price + " euros";
    }

    // Validaciones (que no esté vacío, que el tipo de dato sea el que toca (esto quizás utils),
    // que no se pase de los caracteres máximos que acepta la base de datos...

    // No están hechas por ahora porque no tengo 100% claro cómo se van a gestionar con el Builder

}