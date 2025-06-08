package com.escapeRoom.entities;

import com.escapeRoom.entities.enums.Difficulty;
import com.escapeRoom.exceptions.*;
import com.escapeRoom.roombuilder.interfaces.IRoomBuilder;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ConcreteBuilder implements IRoomBuilder {
    private int idRoom;
    private int idEscaperoom_ref;
    private String name;
    private List<Hint> hints;
    private List<Decoration>decorations;
    private Difficulty difficulty;
    private BigDecimal price;

    public ConcreteBuilder() {
        this.hints = new ArrayList<>();
        this.decorations = new ArrayList<>();
    }

    @Override
    public IRoomBuilder setIdEscaperoom_ref(int idEscaperoom_ref){
        this.idEscaperoom_ref = idEscaperoom_ref;
        return this;
    }


    @Override
    public IRoomBuilder setName(String name){
        if(name == null || name.trim().isEmpty()){
            throw new NullOrEmptyException("El nombre de la sala no puede estar vacio");
        }
        this.name = name;
        return this;
    }

    @Override
    public IRoomBuilder setHints(List<Hint> hints) {

        this.hints = hints != null ? hints : new ArrayList<>();
        return this;
    }

    @Override
    public IRoomBuilder setDecorations(List<Decoration> decorations) {

        this.decorations = decorations != null ? decorations : new ArrayList<>();
        return this;
    }


    @Override
    public IRoomBuilder setDifficulty(Difficulty difficulty){
        if(difficulty == null){
            throw new InvalidDificultyException("Debe tener una dificultad añadida");
        }
        this.difficulty = difficulty;
        return this;
    }

    @Override
    public IRoomBuilder setPrice(BigDecimal price){
        if(price.compareTo(BigDecimal.ZERO) == 0){
            throw new InvalidPriceException("El precio no puede ser cero o negativo");
        }
        this.price = price;
        return this;
    }

    @Override
    public Room build(){

        return new Room(idEscaperoom_ref, name, difficulty,
                price, hints, decorations);

    }
}
