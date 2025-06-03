package org.example.model.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Tickets(int idTickets, int idRoom, int idPlayer, LocalDate boughtOn, BigDecimal price) {

    // Ver cómo validar en una clase record, que ahora mismo ni idea

    // Validaciones (que no esté vacío, que el tipo de dato sea el que toca (esto quizás utils),
    // que no se pase de los caracteres máximos que acepta la base de datos...

    // Capar "double price" a 2 decimales

}