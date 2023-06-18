package com.caduda.bot.telegram;

import com.caduda.bot.services.BotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class CadudaBot extends TelegramLongPollingBot {

    private BotService botService;

    public CadudaBot(BotService botService) {
        this.botService = botService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        String botAnswer = botService.getBotAnswer(message.getText());

        SendMessage response = new SendMessage();
        response.setChatId(message.getChatId());
        response.setText(botAnswer);
        sendResponse(response);
    }

    public void sendResponse(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "CadudaBot";
    }

    public String getBotToken() {
        return "6001730095:AAHg6VDgOpoOyG2inYRM11hKcp_DLr6l0r4";
    }
}
