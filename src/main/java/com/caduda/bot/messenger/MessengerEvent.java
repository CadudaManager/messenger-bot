package com.caduda.bot.messenger;

import java.util.ArrayList;
import java.util.List;


public class MessengerEvent {
    private String object;
    private List<MessengerEntry> entry = new ArrayList<>();

    public String getObject() {
        return object;
    }

    public List<MessengerEntry> getEntry() {
        return entry;
    }

    @Override
    public String toString() {
        return "ReceiveEvent{" +
                "object='" + object + '\'' +
                ", entry=" + entry +
                '}';
    }
}
