package com.escapeRoom.entities;

import com.escapeRoom.entities.enums.Difficulty;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class Room {

    private int idRoom;
    private int idEscaperoom_ref;
    private String name;  // 45 max, gestionar
    private List<Hint> hints;
    private List<Decoration> decorationItems;
    private Difficulty difficulty;
    private BigDecimal price;  // 2 decimales max, gestionar

    public Room(long id, String name, ArrayList<Hint> hints, ArrayList<Decoration> decorations,
    Difficulty dificulty, int price) {

    }

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

    // No están hechas las validations por ahora porque no tengo 100% claro cómo se van a gestionar con el Builder

}