package com.caduda.bot.messenger;

public class ReceivedEventFormatted {
    private String userId;
    private String userMessage;

    public ReceivedEventFormatted(MessengerEvent event){
        event.getEntry().forEach( entry -> entry.getMessaging().forEach(messaging -> {
            userId = messaging.getSender().get("id");
            userMessage = messaging.getMessage().getText();
        }));
    }

    public String getUserId() {
        return userId;
    }

    public String getUserMessage() {
        return userMessage;
    }

    @Override
    public String toString() {
        return "ReceiveMessage{" +
                "userId='" + userId + '\'' +
                ", userMessage='" + userMessage + '\'' +
                '}';
    }
}
