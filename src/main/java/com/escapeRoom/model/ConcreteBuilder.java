package com.escapeRoom.model;

import com.escapeRoom.exceptions.*;
import com.escapeRoom.model.interfaces.IRoomBuilder;

import java.util.ArrayList;
import java.util.List;

public class ConcreteBuilder implements IRoomBuilder {
    private long id;
    private String name;
    private List<Hint> hints;
    private List<Decoration>decorations;
    private Dificulty dificulty;
    private int price;



    @Override
    public IRoomBuilder setName(String name){
        if(name == null || name.trim().isEmpty()){
            throw new InvalidRoomNameException("The room name cannot be empty");
        }
        this.name = name;
        return this;
    };

    @Override
    public IRoomBuilder addHints(Hint hints){
        if(hints == null || hints.getDescription().trim().isEmpty()){
            throw new InvalidHintException("Hint cannot be null or empty.");
        }
        this.hints.add(hints);
        return this;
    };

    @Override
    public IRoomBuilder addDecorations(Decoration decorations){
        if(decorations == null || decorations.getDescription().trim().isEmpty()){
            throw new InvalidDecorationException("Decoration cannot be null or empty");
        }
        this.decorations.add(decorations);
        return this;
    };

    @Override
    public IRoomBuilder setDificulty(Dificulty dificulty){
        if(dificulty == null){
            throw new InvalidDificultyException("A valid difficulty must be specified");
        }
        this.dificulty = dificulty;
        return this;
    };

    @Override
    public IRoomBuilder setPrice(int price){
        if(price <= 0){
            throw new InvalidPriceException("The price cannot be negative");
        }
        this.price = price;
        return this;
    };

    @Override
    public Room build(){

        return new Room(id, name, new ArrayList<>(hints), new ArrayList<>(decorations), dificulty, price);

    };
}
