package com.escapeRoom.notifications.interfaces;

public interface Newsletter {

    void addObserver(Subscriber subscriber);
    void removeObserver(Subscriber subscriber);
    String notifyObservers(String newsletterUpdate);

}