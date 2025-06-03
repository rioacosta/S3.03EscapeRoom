package org.example.model.entities;

import lombok.Getter;
import lombok.Setter;
import org.example.utils.InputUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

@Getter
@Setter
public class Hint {

    private int idHint;
    private int idRoom;
    private String description;  // 200 max, gestionar
    private String theme;  // 45 max, gestionar
    private BigDecimal price;  // 2 max, gestionar

    public Hint(int idHint, int idRoom, String description, String theme, BigDecimal price) {
        // Las validaciones de que no se sobrepasa del número máximo de caracteres o cifras tendrán que hacerse
        // fuera de aquí, usando los métodos que hay abajo en este documento
        InputUtils.getValidInt(idHint);
        InputUtils.getValidInt(idRoom);
        InputUtils.getValidString(description);
        InputUtils.getValidString(theme);
        InputUtils.getValidBigDecimal(price);

        this.idHint = idHint;
        this.idRoom = idRoom;
        this.description = description;
        this.theme = theme;
        this.price = price;
    }

    // De los getters and setters se está encargando Lombok

    // Validaciones (que no esté vacío, que el tipo de dato sea el que toca (esto quizás utils),
    // que no se pase de los caracteres máximos que acepta la base de datos...

    private String descriptionCharacterLimit(String description) {
        Scanner scanner = new Scanner(System.in);

        while (description.length() > 200) {
            System.out.println("La descripción no puede tener más de 100 caracteres. Introduce una descripción válida");
            description = scanner.nextLine();
        }
        return description;
    }

    private String themeCharacterLimit(String theme) {
        Scanner scanner = new Scanner(System.in);

        while (theme.length() > 45) {
            System.out.println("El tema no puede tener más de 45 caracteres. Introduce un tema válido");
            theme = scanner.nextLine();
        }
        return theme;
    }

    public static String priceCharacterLimit(BigDecimal price) {
        if (price.scale() > 2) {
            price = price.setScale(2, RoundingMode.HALF_UP);
        }

        return price.toString();
    }

}