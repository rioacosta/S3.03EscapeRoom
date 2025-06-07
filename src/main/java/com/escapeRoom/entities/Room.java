package com.escapeRoom.entities;

import com.escapeRoom.entities.enums.Difficulty;
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
    private String name;  // 45 max, gestionar
    private List<Hint> hints;
    private List<Decoration> decorationItems;
    private Difficulty difficulty;
    private BigDecimal price;  // 2 decimales max, gestionar


    public Room() {
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

    public void showDecorationItems() {
        for (Decoration deco : decorationItems) {
            System.out.println(deco.toString());
        }
    }

    public void showHintItems() {
        for (Hint hint : hints) {
            System.out.println(hint.toString());
        }
    }

    public BigDecimal getTotalFromDecorationPrice() {
        if (decorationItems.isEmpty()) {
            return BigDecimal.ZERO;
        }
    BigDecimal price = BigDecimal.ZERO;
        for(Decoration deco :decorationItems)    {
        if (deco.getPrice() != null) {
            price = price.add(deco.getPrice());
        }
    }
        return price.setScale(2,RoundingMode.HALF_UP);

    }

        @Override
        public String toString () {
            return "Id de la habitación: " + idRoom + "\nNombre: " + name + "\nDificultad: " + difficulty.getDescription()
                    + "\nPrecio: " + price + " euros" + "\nPistas: " + (hints != null ? hints.size() : 0) +
                    "\nDecoraciones: " + (decorationItems != null ? decorationItems.size() : 0);
        }

    }