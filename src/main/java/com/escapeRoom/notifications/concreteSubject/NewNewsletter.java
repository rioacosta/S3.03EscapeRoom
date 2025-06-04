package com.escapeRoom.notifications.concreteSubject;

import com.escapeRoom.notifications.interfaces.Newsletter;
import com.escapeRoom.notifications.interfaces.Subscriber;

import java.util.ArrayList;
import java.util.List;

public class NewNewsletter implements Newsletter {

    private List<Subscriber> subscribers = new ArrayList<>();

    @Override
    public void addObserver(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void removeObserver(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void notifyObservers(String newsletterUpdate) {
        for (Subscriber subscriber : subscribers) {
            subscriber.update(newsletterUpdate);
        }
    }

}