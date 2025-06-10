package com.escapeRoom.notifications.concreteObserver;

import com.escapeRoom.entities.Player;
import com.escapeRoom.notifications.concreteSubject.NewNewsletter;
import com.escapeRoom.notifications.interfaces.Subscriber;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NewSubscriber implements Subscriber {
    private String name;
    private String email;
    private NewNewsletter newsletter;


    public NewSubscriber(Player player) {
        this.name = player.getName();
        this.email = player.getEmail();
    }

    @Override
    public void update(String newsletterUpdate) {
        System.out.println("Nueva notificación de newsletter enviada :D   = " + newsletterUpdate);

    }

}