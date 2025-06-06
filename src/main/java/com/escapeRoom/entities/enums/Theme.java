package com.escapeRoom.entities.enums;

import lombok.Getter;

@Getter
public enum Theme {
    HORROR("Ambiente oscuro con elementos aterradores"),
    DISNEY("Atmósfera colorida inspirada en películas animadas de Disney"),
    TERROR("Sensación de suspenso y miedo psicológico");

    private final String description;

    Theme(String description){
        this.description = description;
    }
    public String getDescription(){
        return description;
    }
}

