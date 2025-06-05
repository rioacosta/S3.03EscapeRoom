package com.escapeRoom.entities;

import com.escapeRoom.entities.enums.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class Room {

    private int idRoom;
    private int idEscaperoom_ref;
    private String name;  // 45 max, gestionar
    private Difficulty difficulty;
    private BigDecimal price;  // 2 decimales max, gestionar
    private ArrayList<Hint> hints;
    private ArrayList<Decoration> decorations;

    public Room(long id, String name, Difficulty difficulty,  int price) {
        this.idRoom = (int) id;
        this.name = name;
        this.difficulty = difficulty;
        this.price = BigDecimal.valueOf(price);
        this.hints = new ArrayList<>();
        this.decorations = new ArrayList<>();
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
            return BigDecimal.ZERO;        }

        BigDecimal price = BigDecimal.ZERO;
        for (Decoration deco : decorations) {
            if (deco.getPrice() != null) {
                price = price.add(deco.getPrice());            }
        }
        return price.setScale(2, RoundingMode.HALF_UP);

    }
    @Override
    public String toString() {
        return "Id de la habitación: " + idRoom + "\nNombre: " + name + "\nDificultad: " + difficulty.getDescription()
                + "\nPrecio: " + price + " euros";
    }

    // Validaciones (que no esté vacío, que el tipo de dato sea el que toca (esto quizás utils),
    // que no se pase de los caracteres máximos que acepta la base de datos...

    // No están hechas por ahora porque no tengo 100% claro cómo se van a gestionar con el Builder

}