package com.escapeRoom.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Tickets {
    private int idTickets;  // Campo añadido
    private int idRoom;
    private int idPlayer;
    private LocalDate boughtOn;
    private BigDecimal price;

    // Constructor para nuevos tickets (sin ID)
    public Tickets(int idRoom, int idPlayer, LocalDate boughtOn, BigDecimal price) {
        setIdRoom(idRoom);
        setIdPlayer(idPlayer);
        setBoughtOn(boughtOn);
        setPrice(price);
    }

    // Constructor para tickets existentes (con ID)
    public Tickets(int idTickets, int idRoom, int idPlayer, LocalDate boughtOn, BigDecimal price) {
        setIdTickets(idTickets);
        setIdRoom(idRoom);
        setIdPlayer(idPlayer);
        setBoughtOn(boughtOn);
        setPrice(price);
    }

    // Getters y Setters con validación
    public int getIdTickets() {
        return idTickets;
    }

    public void setIdTickets(int idTickets) {
        if (idTickets <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }
        this.idTickets = idTickets;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        if (idRoom <= 0) {
            throw new IllegalArgumentException("ID Room inválido");
        }
        this.idRoom = idRoom;
    }

    public int getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(int idPlayer) {
        if (idPlayer <= 0) {
            throw new IllegalArgumentException("ID Player inválido");
        }
        this.idPlayer = idPlayer;
    }

    public LocalDate getBoughtOn() {
        return boughtOn;
    }

    public void setBoughtOn(LocalDate boughtOn) {
        if (boughtOn == null) {
            throw new IllegalArgumentException("Fecha requerida");
        }
        if (boughtOn.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Fecha no puede ser futura");
        }
        this.boughtOn = boughtOn;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        if (price == null) {
            throw new IllegalArgumentException("Precio requerido");
        }
        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Precio debe ser positivo");
        }
        this.price = price;
    }
}