package com.escapeRoom.entities;

import com.escapeRoom.entities.enums.Difficulty;
import com.escapeRoom.entities.enums.Theme;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Room {
    private int idRoom;
    private int idEscaperoom_ref;
    private String name;
    private List<Hint> hints;
    private List<Decoration> decorations;
    private Difficulty difficulty;
    private Theme theme;
    private BigDecimal price;


    public Room() {
        this.hints = new ArrayList<>();
        this.decorations = new ArrayList<>();
    }

    Room(int idEscaperoom_ref, String name, Difficulty difficulty,
         BigDecimal price, Theme theme, List<Hint> hints, List<Decoration> decorations) {
        this.idEscaperoom_ref = idEscaperoom_ref;
        this.name = name;
        this.difficulty = difficulty;
        this.price = price;
        this.theme = theme;
        this.hints = hints != null ? hints : new ArrayList<>();
        this.decorations = decorations != null ? decorations : new ArrayList<>();
    }

    public void showDecorationItems() {
        for (Decoration deco : decorations) {
            System.out.println(deco.toString());
        }
    }

    public void showHintItems() {
        for (Hint hint : hints) {
            System.out.println(hint.toString());
        }
    }

    public BigDecimal getTotalFromDecorationPrice() {
        if (decorations.isEmpty()) {
            return BigDecimal.ZERO;
        }
        BigDecimal price = BigDecimal.ZERO;
        for (Decoration deco : decorations) {
            if (deco.getPrice() != null) {
                price = price.add(deco.getPrice());
            }
        }
        return price.setScale(2, RoundingMode.HALF_UP);

    }

    @Override
    public String toString() {
        return "Room{" +
                "idRoom=" + idRoom +
                ", idEscaperoom_ref=" + idEscaperoom_ref +
                ", name='" + name + '\'' +
                ", hints=" + hints +
                ", decorations=" + decorations +
                ", difficulty=" + difficulty +
                ", theme= " + theme +
                ", price=" + price +
                '}';
    }
}