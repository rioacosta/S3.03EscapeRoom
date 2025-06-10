package com.escapeRoom.entities;

import com.escapeRoom.dao.interfaces.IGenericDAO;
import com.escapeRoom.dao.mysqlimp.MySQLPlayerDAO;
import com.escapeRoom.notifications.interfaces.Subscriber;
import lombok.Getter;
import lombok.Setter;
import com.escapeRoom.utils.InputUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Getter
@Setter
public class Player implements Subscriber {
    private static int playersCount = 0;
    private int idPlayer;
    private String name;
    private String email;
    private List<Certificate> certificates;
    private MySQLPlayerDAO playerDAO;

    public Player(int idPlayer, String name, String email) {
        InputUtils.getValidInt(idPlayer);
        InputUtils.getValidString(name);
        InputUtils.getValidEmail(email);
        playersCount++;
        this.idPlayer = playersCount;
        this.name = name;
        this.email = email;
        this.certificates = new ArrayList<>();
    }

    public Player( String name, String email) {
        InputUtils.getValidString(name);
        InputUtils.getValidEmail(email);
        playersCount++;
        this.idPlayer = playersCount;
        this.name = name;
        this.email = email;
        this.certificates = new ArrayList<>();
    }

    public void setCertificate(Certificate certificate) {
        if (certificate != null) {
            if (certificates == null) {
                certificates = new ArrayList<>();
            }
            certificates.add(certificate);
        }
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

    /*@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Jugador: ").append(name)
                .append(" con ID: ").append(idPlayer)
                .append(", email: ").append(email);

        if (certificates != null && !certificates.isEmpty()) {
            sb.append("\nCertificados:\n");
            for (Certificate cert : certificates) {
                sb.append(" - ").append(cert.toString()).append("\n");
            }
        } else {
            sb.append("\nNo tiene certificados asignados\n");
        }
        return sb.toString();
    }*/

    @Override
    public String toString() {
     return "Jugador: " + name + " con ID: " + idPlayer + ", email: " + email + "\ncertificados: " + certificates + "\n";
    }
}