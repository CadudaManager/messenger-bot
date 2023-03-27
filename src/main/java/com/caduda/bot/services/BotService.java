package com.caduda.bot.services;

import com.caduda.bot.interfaces.NLPInterface;
import com.caduda.bot.interfaces.Question;

import java.util.List;

public class BotService {

    private NLPInterface nlp;
    private List<Question> questions;

    BotService(NLPInterface nlpInterface, List<Question> questions) {
        this.nlp = nlpInterface;
        this.questions = questions;
    }

    public String getBotAnswer(String userMessage) {
        String intent = nlp.getIntent(userMessage);
        return getRightAnswerFromUserMessage(intent);
    }

    public String getRightAnswerFromUserMessage(String intent) {
        for (Question question : questions) {
            if (question.preCondition(intent)) {
                return question.process();
            }
        }
        return "Não entendi";
    }
}
