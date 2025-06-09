package com.escapeRoom.entities;

import com.escapeRoom.notifications.interfaces.Subscriber;
import lombok.Getter;
import lombok.Setter;
import com.escapeRoom.utils.InputUtils;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Getter
@Setter
@ToString
public class Player implements Subscriber {
    private static int playersCount = 0;
    private int idPlayer;
    private String name;
    private String email;
    private List<Certificate> certificates;

    public Player(String name, String email) {   //
        // Las validaciones de que no se sobrepasa del número máximo de caracteres o cifras tendrán que hacerse
        // fuera de aquí, usando los métodos que hay abajo en este documento
        InputUtils.getValidInt(idPlayer); //comentado porque la base de datos ya esta generandoli
        InputUtils.getValidString(name);
        InputUtils.getValidEmail(email);
        playersCount++;
        this.idPlayer = playersCount;
        this.name = name;
        this.email = email;
        this.certificates = new ArrayList<>();
    }

    public void setCertificate(Certificate certificate) {
        certificates.add(certificate);
    }

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

    @Override
    public void update(String newsletterUpdate) {
        System.out.println("Nueva notificación de newsletter :D");
    }
}