package com.escapeRoom.entities;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class Tickets {
    private int idTickets;
    private int idRoom;
    private int idPlayer;
    private LocalDate boughtOn;
    private BigDecimal price;

    public Tickets(int idRoom, int idPlayer, LocalDate boughtOn, BigDecimal price) {
        setIdRoom(idRoom);
        setIdPlayer(idPlayer);
        setBoughtOn(boughtOn);
        setPrice(price);
    }

    public Tickets(int idTickets, int idRoom, int idPlayer, LocalDate boughtOn, BigDecimal price) {
        setIdTickets(idTickets);
        setIdRoom(idRoom);
        setIdPlayer(idPlayer);
        setBoughtOn(boughtOn);
        setPrice(price);
    }

    public void setIdTickets(int idTickets) {
        if (idTickets <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }
        this.idTickets = idTickets;
    }

    public void setIdRoom(int idRoom) {
        if (idRoom <= 0) {
            throw new IllegalArgumentException("ID Room inválido");
        }
        this.idRoom = idRoom;
    }

    public void setIdPlayer(int idPlayer) {
        if (idPlayer <= 0) {
            throw new IllegalArgumentException("ID Player inválido");
        }
        this.idPlayer = idPlayer;
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