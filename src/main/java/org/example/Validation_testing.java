package org.example;


// ESTE ARCHIVO NO SE SUBIRÁ AL PROYECTO FINAL
// ES SOLO PARA VER CÓMO USAR LAS VALIDACIONES


import org.example.exceptions.EmptyInputException;
import org.example.utils.InputUtils;

import java.math.BigDecimal;
import java.util.Scanner;

public class Validation_testing {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        // TESTING STRINGS ✓
//        boolean isValidString = false;
//
//        while (!isValidString) {
//            try {
//                System.out.println("Dime una String:");
//                String myString = scanner.nextLine();
//                InputUtils.getValidString(myString);
//                isValidString = true;
//            } catch (EmptyInputException e) {
//                System.out.println(e.getMessage());
//            }
//        }

        // TESTING BYTES ✓
//        boolean isValidBigDecimal = false;
//
//        while (!isValidBigDecimal) {
//            try {
//                System.out.println("Dime un byte");
//                String myByte = scanner.nextLine();
//
//                InputUtils.checkEmptyInput(myByte);
//                byte parsedByte = Byte.parseByte(myByte);
//                InputUtils.getValidByte(parsedByte);
//
//                isValidBigDecimal = true;
//            } catch (EmptyInputException e) {
//                System.out.println(e.getMessage());
//            } catch (NumberFormatException e) {
//                System.out.println("Error. Por favor ingrese un número válido.");
//            } catch (IllegalArgumentException e) {
//                System.out.println(e.getMessage());
//            }
//        }

        // TESTING BIG DECIMALS ✓
//        boolean isValidBigDecimal = false;
//
//        while (!isValidBigDecimal) {
//            try {
//                System.out.println("Dime un big decimal");
//                String myBigDecimal = scanner.nextLine();
//
//                if (myBigDecimal.trim().isEmpty()) {
//                    throw new EmptyInputException("No puedes responder en blanco");
//                }
//
//                BigDecimal bigDecimalValue = new BigDecimal(myBigDecimal);
//
//                InputUtils.getValidBigDecimal(bigDecimalValue);
//
//                isValidBigDecimal = true;
//            } catch (EmptyInputException e) {
//                System.out.println(e.getMessage());
//            } catch (NumberFormatException e) {
//            System.out.println("Por favor, ingresa un número válido.");
//            }
//        }

        // TESTING DATES ✓
//        boolean isValidDate = false;
//        while (!isValidDate) {
//            try {
//                System.out.println("Introduce una fecha (yyyy-MM-dd):");
//                String dateInput = scanner.nextLine();
//
//                InputUtils.getValidLocalDate(dateInput);
//                isValidDate = true;
//            } catch (EmptyInputException | IllegalArgumentException e) {
//                System.out.println(e.getMessage());
//            }
//        }


    }

}