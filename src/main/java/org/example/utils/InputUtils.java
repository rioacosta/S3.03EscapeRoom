package org.example.utils;

import org.example.exceptions.EmptyInputException;

import java.util.List;
import java.util.regex.Pattern;

public class InputUtils {

    public static void getValidString(String input) {
        // POR CHECKEAR
        if (input == null || input.trim().isEmpty()) {
            throw new EmptyInputException("No puedes responder en blanco");
        }
    }

    public static void getValidInt(Integer input) {
        // POR CHECKEAR
        if (input == null) {
            throw new EmptyInputException("No puedes responder en blanco");
        }
        if (input <= 0) {
            throw new IllegalArgumentException("El número no puede ser negativo");
        }
    }

    public static void getValidList(List<?> input) {
        // POR CHECKEAR
        if (input == null) {
            throw new EmptyInputException("La lista no puede ser nula");
        }
        if (input.isEmpty()) {
            throw new EmptyInputException("La lista no puede estar vacía");
        }
    }

    public static void getValidEmail(String input) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);

        do {
            if (input.isEmpty()) {
                System.out.println("No puedes responder en blanco");
            } else if (!pattern.matcher(input).matches()) {
                System.out.println("Error. Por favor, introduce un correo electrónico válido");
            }
        } while (input.isEmpty() || !pattern.matcher(input).matches());
    }

}