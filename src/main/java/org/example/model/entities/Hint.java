package org.example.model.entities;

import java.math.BigDecimal;

public class Hint {

    private int idHint;  // esto cómo lo conectamos a la base de datos?
    private int idRoom;  // esto cómo lo conectamos a la base de datos?
    private String description;  // 200 max, gestionar
    private String theme;  // 45 max, gestionar
    private BigDecimal price;  // 2 max, gestionar

    public int getIdHint() {
        return idHint;
    }

    public void setIdHint(int idHint) {
        this.idHint = idHint;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    // Validaciones (que no esté vacío, que el tipo de dato sea el que toca (esto quizás utils),
    // que no se pase de los caracteres máximos que acepta la base de datos...

}