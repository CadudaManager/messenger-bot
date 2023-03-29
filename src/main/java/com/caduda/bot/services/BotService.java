package com.caduda.bot.services;

import com.caduda.bot.interfaces.NLPInterface;
import com.caduda.bot.interfaces.Question;
import com.caduda.bot.questions.IntentsAndAnswers;

import java.util.List;

public class BotService {

    private NLPInterface nlp;
    private List<Question> questions;

    public BotService(NLPInterface nlpInterface, IntentsAndAnswers intentsAndAnswers) {
        this.nlp = nlpInterface;
        this.questions = intentsAndAnswers.getQuestionsWithAnswers();
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
