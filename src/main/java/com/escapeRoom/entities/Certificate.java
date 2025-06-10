package com.escapeRoom.entities;

import lombok.*;
import com.escapeRoom.utils.InputUtils;

import java.time.LocalDate;
import java.util.Scanner;

@Getter
@Setter
@NoArgsConstructor
public class Certificate {

    private int idCertificate;
    private String name;
    private String description;
    private LocalDate dateOfDelivery;
    private int idPlayer;

    public Certificate(String name, String description, LocalDate dateOfDelivery) {
        InputUtils.getValidInt(idCertificate);
        InputUtils.getValidString(name);
        InputUtils.getValidLocalDate(String.valueOf(dateOfDelivery));
        InputUtils.getValidInt(idPlayer);
        this.name = name;
        this.description = "";
        this.dateOfDelivery = dateOfDelivery;
        this.idPlayer = idPlayer;
    }

    public Certificate(int idCertificate, String name, String description, LocalDate dateOfDelivery, int idPlayer) {
        InputUtils.getValidInt(idCertificate);
        InputUtils.getValidString(name);
        InputUtils.getValidLocalDate(String.valueOf(dateOfDelivery));
        InputUtils.getValidInt(idPlayer);
        this.idCertificate = idCertificate;
        this.name = name;
        this.dateOfDelivery = dateOfDelivery;
        this.idPlayer = idPlayer;
    }

    private String nameCharacterLimit(String name) {
        Scanner scanner = new Scanner(System.in);

        while (name.length() > 45) {
            System.out.println("El nombre del escape room no puede tener más de 45 caracteres. Introduce un nombre válido");
            name = scanner.nextLine();
        }
        return name;
    }

    @Override
    public String toString() {
        return "El jugador " + name + ", ha superado todos los retos y ha ganado el reconocimiento por "
                + description + " el dia, " + dateOfDelivery + "/n";
    }
}