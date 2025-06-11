package com.escapeRoom.notifications.concreteObserver;

import com.escapeRoom.notifications.concreteSubject.NewNewsletter;
import com.escapeRoom.notifications.interfaces.Subscriber;
import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
public class NewSubscriber implements Subscriber {
    private String name;
    private String email;
    private NewNewsletter newsletter;

    @Override
    public void update(String newsletterUpdate) {
        newsletter.notifyObservers(newsletterUpdate);
        System.out.println("Nueva notificación de newsletter enviada :D");

    }

}