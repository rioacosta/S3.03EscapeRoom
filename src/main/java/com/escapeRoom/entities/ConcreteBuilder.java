package com.escapeRoom.entities;

import com.escapeRoom.entities.enums.Difficulty;
import com.escapeRoom.exceptions.*;
import com.escapeRoom.roombuilder.interfaces.IRoomBuilder;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ConcreteBuilder implements IRoomBuilder {
    private int idRoom;
    private int idEscapeRoom_ref;
    private String name;
    private List<Hint> hints;
    private List<Decoration>decorations;
    private Difficulty dificulty;
    private BigDecimal price;

    public ConcreteBuilder() {
        this.hints = new ArrayList<>();
        this.decorations = new ArrayList<>();
    }

    @Override
    public IRoomBuilder setIdEscapeRoom_ref(int idEscapeRoom_ref){
        this.idEscapeRoom_ref = idEscapeRoom_ref;
        return this;
    }


    @Override
    public IRoomBuilder setName(String name){
        if(name == null || name.trim().isEmpty()){
            throw new NullOrEmptyException("El nombre de la sala no puede estar vacio");
        }
        this.name = name;
        return this;
    };

    @Override
    public IRoomBuilder addHints(Hint hints){
        if(hints == null || hints.getDescription().trim().isEmpty()){
            throw new NullOrEmptyException("La pista no puede estar vacia.");
        }
        this.hints.add(hints);
        return this;
    };

    @Override
    public IRoomBuilder addDecorations(Decoration decorations){
        if(decorations == null || decorations.getDescription().trim().isEmpty()){
            throw new NullOrEmptyException("La decoración no puede estar vacia");
        }
        this.decorations.add(decorations);
        return this;
    };

    @Override
    public IRoomBuilder setDificulty(Difficulty dificulty){
        if(dificulty == null){
            throw new InvalidDificultyException("Debe tener una dificultad añadida");
        }
        this.dificulty = dificulty;
        return this;
    };

    @Override
    public IRoomBuilder setPrice(BigDecimal price){
        if(price.compareTo(BigDecimal.ZERO) == 0){
            throw new InvalidPriceException("El precio no puede ser cero o negativo");
        }
        this.price = price;
        return this;
    };

    @Override
    public Room build(){

        return new Room(idEscapeRoom_ref, name, dificulty,
                price, hints, decorations);

    };
}
