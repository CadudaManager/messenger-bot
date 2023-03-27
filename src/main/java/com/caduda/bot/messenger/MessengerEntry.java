package com.caduda.bot.messenger;

import java.util.ArrayList;
import java.util.List;


public class MessengerEntry {
    private List<MessengerMessaging> messaging = new ArrayList<>();

    public List<MessengerMessaging> getMessaging() {
        return messaging;
    }

    @Override
    public String toString() {
        return "PostEntry{" +
                "messaging=" + messaging +
                '}';
    }
}