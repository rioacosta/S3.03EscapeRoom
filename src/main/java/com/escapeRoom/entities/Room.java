package com.escapeRoom.entities;

import com.escapeRoom.entities.enums.Difficulty;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

//comento lombok me da problemas con el builder
//@NoArgsConstructor


public class Room {

    private int idRoom;
    private int idEscaperoom_ref;
    private String name;  // 45 max, gestionar
    private List<Hint> hints;
    private List<Decoration> decorationItems;
    private Difficulty difficulty;
    private BigDecimal price;  // 2 decimales max, gestionar
/*
    public Room(long id, String name, ArrayList<Hint> hints, ArrayList<Decoration> decorations,
    Difficulty dificulty, int price) {

    }


 */
    public Room(){
        //inicializo las listas para que no esten vacias
        this.hints = new ArrayList<>();
        this.decorationItems = new ArrayList<>();

    }


    Room(int idEscaperoom_ref, String name, Difficulty difficulty,
                 BigDecimal price, List<Hint> hints, List<Decoration> decorations) {
        this.idEscaperoom_ref = idEscaperoom_ref;
        this.name = name;
        this.difficulty = difficulty;
        this.price = price;
        this.hints = hints != null ? hints : new ArrayList<>();
        this.decorationItems = decorations != null ? decorations : new ArrayList<>();
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


    //añado getters y setters para las listas


    public List<Hint> getHints() {
        return hints;
    }

    public void setHints(List<Hint> hints) {
        this.hints = hints;
    }

    public List<Decoration> getDecorationItems() {
        return decorationItems;
    }

    public void setDecorationItems(List<Decoration> decorationItems) {
        this.decorationItems = decorationItems;
    }

    @Override
    public String toString() {
        return "Id de la habitación: " + idRoom + "\nNombre: " + name + "\nDificultad: " + difficulty.getDescription()
                + "\nPrecio: " + price + " euros" + "\nPistas: " + (hints != null ? hints.size() : 0) +
                "\nDecoraciones: " + (decorationItems != null ? decorationItems.size() : 0);
    }

    // No están hechas las validations por ahora porque no tengo 100% claro cómo se van a gestionar con el Builder

}