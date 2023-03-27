package com.caduda.bot.messenger;

public class MessengerMessage {
    private String text;

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "PostMessage{" +
                "text='" + text + '\'' +
                '}';
    }
}
