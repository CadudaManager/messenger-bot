package com.caduda.bot.services;

import com.caduda.bot.messenger.ReceivedEventFormatted;
import com.caduda.bot.messenger.ResponseEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

public class MessengerService {

    @Value("${replyUrl}")
    private String REPLY_URL;
    private BotService botService;
    private RestTemplate restTemplate;

    MessengerService(BotService botService, RestTemplate restTemplate) {
        this.botService = botService;
        this.restTemplate = restTemplate;
    }
    public void answerUser(ReceivedEventFormatted receivedEventFormatted) {
        String userMessage = receivedEventFormatted.getUserMessage();

        String botAnswer = botService.getBotAnswer(userMessage);

        sendResponseToFacebook(receivedEventFormatted.getUserId(), botAnswer);
    }

    private void sendResponseToFacebook(String userId, String botAnswer) {
        ResponseEvent responseEvent = new ResponseEvent(userId, botAnswer);

        HttpEntity<ResponseEvent> entity = new HttpEntity<>(responseEvent);

        restTemplate.postForEntity(REPLY_URL, entity, String.class);
    }
}
