package com.escapeRoom.entities;

import lombok.Getter;
import lombok.Setter;
import com.escapeRoom.utils.InputUtils;

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
    private BigDecimal price;  // 2 max, gestionar

    public Decoration(int idDecoration, int idRoom_ref, String description, String material, BigDecimal price) {
        // Las validaciones de que no se sobrepasa del número máximo de caracteres o cifras tendrán que hacerse
        // fuera de aquí, usando los métodos que hay abajo en este documento
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

    private String descriptionCharacterLimit(String description) {
        Scanner scanner = new Scanner(System.in);

        while (description.length() > 100) {
            System.out.println("La descripción no puede tener más de 100 caracteres. Introduce una descripción válida");
            description = scanner.nextLine();
        }
        return description;
    }

    private String materialCharacterLimit(String material) {
        Scanner scanner = new Scanner(System.in);

        while (material.length() > 45) {
            System.out.println("El material no puede tener más de 45 caracteres. Introduce un material válido");
            material = scanner.nextLine();
        }
        return material;
    }

    public static String priceCharacterLimit(BigDecimal price) {
        if (price.scale() > 2) {
            price = price.setScale(2, RoundingMode.HALF_UP);
        }

        return price.toString();
    }

}