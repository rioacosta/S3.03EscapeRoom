package org.example.model.entities;

import java.time.LocalDate;

public class Certificate {

    private int idCertificate;
    private String name;  // no se puso límite en la base de datos
    private LocalDate dateOfDelivery;
    private byte giftCoupon;
    private int idPlayer;

    public int getIdCertificate() {
        return idCertificate;
    }

    public void setIdCertificate(int idCertificate) {
        this.idCertificate = idCertificate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfDelivery() {
        return dateOfDelivery;
    }

    public void setDateOfDelivery(LocalDate dateOfDelivery) {
        this.dateOfDelivery = dateOfDelivery;
    }

    public byte getGiftCoupon() {
        return giftCoupon;
    }

    public void setGiftCoupon(byte giftCoupon) {
        this.giftCoupon = giftCoupon;
    }

    public int getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(int idPlayer) {
        this.idPlayer = idPlayer;
    }

    // Validaciones (que no esté vacío, que el tipo de dato sea el que toca (esto quizás utils),
    // que no se pase de los caracteres máximos que acepta la base de datos...

}