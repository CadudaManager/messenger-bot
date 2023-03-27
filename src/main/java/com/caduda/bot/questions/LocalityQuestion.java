package com.caduda.bot.questions;

import com.caduda.bot.interfaces.Question;

public class LocalityQuestion implements Question {
    @Override
    public boolean preCondition(String intent) {
        return intent.equals("LOCALITY");
    }

    @Override
    public String process() {
        return "Atalaia";
    }
}
