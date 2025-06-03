package org.example.model.entities;

import org.example.utils.InputUtils;

import java.time.LocalDate;

public class Certificate {

    private int idCertificate;
    private String name;
    private LocalDate dateOfDelivery;
    private byte giftCoupon;
    private int idPlayer;

    public Certificate(int idCertificate, String name, LocalDate dateOfDelivery, byte giftCoupon, int idPlayer) {
        InputUtils.getValidInt(idCertificate);
        InputUtils.getValidString(name);
        // TODO validar date
        InputUtils.checkEmptyInput(String.valueOf(giftCoupon));
        InputUtils.getValidByte(giftCoupon);
        InputUtils.getValidInt(idPlayer);

        this.idCertificate = idCertificate;
        this.name = name;
        this.dateOfDelivery = dateOfDelivery;
        this.giftCoupon = giftCoupon;
        this.idPlayer = idPlayer;
    }

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

}