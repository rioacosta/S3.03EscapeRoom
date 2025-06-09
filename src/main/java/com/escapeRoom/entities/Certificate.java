package com.escapeRoom.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.escapeRoom.utils.InputUtils;

import java.time.LocalDate;
import java.util.Scanner;

//@Builder
@Getter
@Setter
@NoArgsConstructor
public class Certificate {

    private int idCertificate;
    private String name;
    private LocalDate dateOfDelivery;
    private boolean giftCoupon;
    private int idPlayer;

    public Certificate(int idCertificate, String name, LocalDate dateOfDelivery, int idPlayer) {
        InputUtils.getValidInt(idCertificate);
        // La validación de que name no se sobrepasa del número máximo de caracteres tendrá que hacerse fuera de aquí,
        // usando el method que hay abajo en este documento
        InputUtils.getValidString(name);
        InputUtils.getValidLocalDate(String.valueOf(dateOfDelivery));
        InputUtils.checkEmptyInput(String.valueOf(giftCoupon));
        //InputUtils.getValidByte(giftCoupon);
        InputUtils.getValidInt(idPlayer);

        this.idCertificate = idCertificate;
        this.name = name;
        this.dateOfDelivery = dateOfDelivery;
        this.giftCoupon = true;
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

}