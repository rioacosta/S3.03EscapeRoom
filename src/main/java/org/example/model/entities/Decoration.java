package org.example.model.entities;

import java.math.BigDecimal;

public class Decoration {

    private int idDecoration;  // esto cómo lo conectamos a la base de datos?
    private int idRoom_ref;  // esto cómo lo conectamos a la base de datos?
    private String description;  // 100 max, gestionar
    private String material;  // 45 max, gestionar
    private BigDecimal price;  // 2 max, gestionar

    public int getIdDecoration() {
        return idDecoration;
    }

    public void setIdDecoration(int idDecoration) {
        this.idDecoration = idDecoration;
    }

    public int getIdRoom_ref() {
        return idRoom_ref;
    }

    public void setIdRoom_ref(int idRoom_ref) {
        this.idRoom_ref = idRoom_ref;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
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