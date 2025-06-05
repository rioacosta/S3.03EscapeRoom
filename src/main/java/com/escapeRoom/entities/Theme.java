package com.escapeRoom.entities;

import lombok.Getter;

@Getter
public enum Theme {
    HORROR("Dark setting with scary elements"),
    DISNEY("Colorful atmosphere inspired by animated films from Disney"),
    TERROR("Sensación de suspenso y miedo psicológico");

    private final String description;

    Theme(String description){
        this.description = description;
    }
    public String getDescription(){
        return description;
    }
}

