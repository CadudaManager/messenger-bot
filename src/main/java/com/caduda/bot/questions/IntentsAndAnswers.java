package com.caduda.bot.questions;

import com.caduda.bot.interfaces.Question;

import java.util.List;

public class IntentsAndAnswers {

    private final List<Question> listQuestionsAndAnswers;

    public IntentsAndAnswers(List<Question> listQuestionsAndAnswers){
        this.listQuestionsAndAnswers = listQuestionsAndAnswers;
    }

    public List<Question> getQuestionsWithAnswers(){

        return listQuestionsAndAnswers;
    }
}
