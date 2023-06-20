package com.caduda.bot.questions;

import com.caduda.bot.interfaces.Question;

import java.time.LocalDateTime;
import java.util.Date;

public class GreetingQuestion implements Question {
    @Override
    public boolean preCondition(String intent) {
        return intent.equals("GREETING");
    }

    @Override
    public String process() {
        return getGreetingByTime();
    }

    private String getGreetingByTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        int currentTime = localDateTime.getHour();

        if (currentTime >= 0 && currentTime < 6) {
            return "Olá!! Boa madrugada!! Em que posso ajudar?? \n\n(/help para ajuda)";
        } else if (currentTime >= 6 && currentTime < 12) {
            return "Olá!! Bom dia!! Em que posso ajudar?? \n\n(/help para ajuda)";
        } else if (currentTime >= 12 && currentTime < 18) {
            return "Olá!! Boa tarde!! Em que posso ajudar?? \n\n(/help para ajuda)";
        } else {
            return "Olá!! Boa noite!! Em que posso ajudar?? \n\n(/help para ajuda)";
        }
    }
}
