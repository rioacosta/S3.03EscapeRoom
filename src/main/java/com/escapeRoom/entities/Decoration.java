package com.escapeRoom.entities;

import lombok.Getter;
import lombok.Setter;
import com.escapeRoom.utils.InputUtils;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

@Getter
@Setter
public class Decoration {

    private int idDecoration;
    private int idRoom_ref;
    private String description;
    private String material;
    private BigDecimal price;

    public Decoration(int idDecoration, int idRoom_ref, String description, String material, BigDecimal price) {
        InputUtils.getValidInt(idDecoration);
        InputUtils.getValidInt(idRoom_ref);
        InputUtils.getValidString(description);
        InputUtils.getValidString(material);
        InputUtils.getValidBigDecimal(price);

        this.idDecoration = idDecoration;
        this.idRoom_ref = idRoom_ref;
        this.description = description;
        this.material = material;
        this.price = price;
    }

    public Decoration(String description, String material, BigDecimal price) {
        InputUtils.getValidString(description);
        InputUtils.getValidString(material);
        InputUtils.getValidBigDecimal(price);


        this.description = description;
        this.material = material;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Objeto de decoracion:" + description + ", hecho de: " + material + ", de valor: "+ price +"€.";
    }
}