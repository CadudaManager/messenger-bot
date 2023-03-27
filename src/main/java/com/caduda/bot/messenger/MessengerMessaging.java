package com.caduda.bot.messenger;

import java.util.HashMap;
import java.util.Map;

public class MessengerMessaging {
    private Map<String, String> sender = new HashMap<>();
    private MessengerMessage message;

    public Map<String, String> getSender() {
        return sender;
    }

    public void setSender(Map<String, String> sender) {
        this.sender = sender;
    }

    public MessengerMessage getMessage() {
        return message;
    }

    public void setMessage(MessengerMessage message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "PostMessaging{" +
                "sender=" + sender +
                ", message=" + message +
                '}';
    }
}