package com.xbaychallenge.notification;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {

    @EventListener(classes = NotificationEvent.class)
    public void handle(NotificationEvent event) {
        sendMessage(event);
    }

    @Async
    void sendMessage(NotificationEvent event) {
        String message = String.format("Sending message: %s in %s", event, Thread.currentThread());
        System.out.println(message);
    }
}
