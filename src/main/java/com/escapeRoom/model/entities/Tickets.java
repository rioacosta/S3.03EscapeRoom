package com.escapeRoom.model.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Tickets(int idTickets, int idRoom, int idPlayer, LocalDate boughtOn, BigDecimal price) {

    // Ver cómo validar en una clase record, que ahora mismo ni idea

    // Capar "double price" a 2 decimales

}