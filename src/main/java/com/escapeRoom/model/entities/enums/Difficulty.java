package com.escapeRoom.model.entities.enums;

public enum Difficulty {

    EASY ("Fácil"),
    MEDIUM ("Media"),
    HARD ("Difícil");

    private String description;

    Difficulty(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}