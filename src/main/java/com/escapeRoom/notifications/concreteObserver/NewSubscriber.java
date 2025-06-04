package com.escapeRoom.notifications.concreteObserver;

import com.escapeRoom.notifications.interfaces.Subscriber;

public class NewSubscriber implements Subscriber {

    private String name;

    // Ver cómo conectar esto a Players ya creados y por lo tanto ya con un nombre
    public NewSubscriber(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void update(String newsletterUpdate) {
        System.out.println("Nueva notificación de newsletter :D");
    }

}