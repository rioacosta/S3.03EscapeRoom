package com.escapeRoom.utils;

import com.escapeRoom.exceptions.EmptyInputException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputUtils {

    public static void getValidString(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new EmptyInputException("No puedes responder en blanco");
        }
    }

    public static void getValidInt(Integer input) {
        if (input < 0) {
            throw new IllegalArgumentException("El número no puede ser negativo");
        }
    }

    public static int readValidInt(Scanner scanner) {
        while (true) {
            String line = scanner.nextLine();
            try {
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Intenta nuevamente: ");
            }
        }
    }

    public static BigDecimal readValidBigDecimal(Scanner scanner) {
        while (true) {
            String line = scanner.nextLine();
            try {
                return new BigDecimal(line.trim());
            } catch (NumberFormatException e) {
                System.out.print("Precio inválido. Intenta nuevamente: ");
            }
        }
    }

    public static void getValidBigDecimal(BigDecimal input) {
        if (input.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El número no puede ser negativo o cero");
        }
    }

    public static void getValidLocalDate(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new EmptyInputException("La fecha no puede estar vacía");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate.parse(input, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Fecha inválida. El formato correcto es yyyy-MM-dd.");
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