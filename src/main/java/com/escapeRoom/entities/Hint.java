package com.escapeRoom.entities;

import com.escapeRoom.entities.enums.Theme;
import lombok.Getter;
import lombok.Setter;
import com.escapeRoom.utils.InputUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

@Getter
@Setter
public class Hint {

    private int idHint;
    private int idRoom_ref;
    private String description;
    private Theme theme;
    private BigDecimal price;

    public Hint(int idHint, int idRoom_ref, String description, Theme theme, BigDecimal price) {
        InputUtils.getValidInt(idHint);
        InputUtils.getValidInt(idRoom_ref);
        InputUtils.getValidString(description);
        InputUtils.getValidBigDecimal(price);

        this.idHint = idHint;
        this.idRoom_ref = idRoom_ref;
        this.description = description;
        this.theme = theme;
        this.price = price;
    }
    public Hint(String description, Theme theme, BigDecimal price) {
        InputUtils.getValidString(description);
        InputUtils.getValidBigDecimal(price);

        this.description = description;
        this.theme = theme;
        this.price = price;
    }

    public Hint(String description) {
        this.description = description;
        this.price = BigDecimal.ZERO;
    }

    @Override
    public String toString() {
        return "La pista: " + description + ", del tema: " + theme;
    }
}