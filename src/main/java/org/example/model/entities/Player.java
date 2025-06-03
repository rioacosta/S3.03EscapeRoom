package org.example.model.entities;

import lombok.Getter;
import lombok.Setter;
import org.example.utils.InputUtils;

import java.util.Scanner;

@Getter
@Setter
public class Player {

    private int idPlayer;
    private String name;  // 45 max, gestionar
    private String email;  // 45 max, gestionar

    public Player(int idPlayer, String name, String email) {
        // Las validaciones de que no se sobrepasa del número máximo de caracteres o cifras tendrán que hacerse
        // fuera de aquí, usando los métodos que hay abajo en este documento
        InputUtils.getValidInt(idPlayer);
        InputUtils.getValidString(name);
        InputUtils.getValidEmail(email);

        this.idPlayer = idPlayer;
        this.name = name;
        this.email = email;
    }

    // De los getters and setters se está encargando Lombok

    private String nameCharacterLimit(String name) {
        Scanner scanner = new Scanner(System.in);

        while (name.length() > 45) {
            System.out.println("El nombre no puede tener más de 45 caracteres. Introduce un nombre válido");
            name = scanner.nextLine();
        }
        return name;
    }

    private String emailCharacterLimit(String email) {
        Scanner scanner = new Scanner(System.in);

        while (email.length() > 45) {
            System.out.println("El mail no puede tener más de 45 caracteres. Introduce un mail válido");
            email = scanner.nextLine();
        }
        return email;
    }

}