package com.caduda.bot.questions;

import com.caduda.bot.interfaces.Question;

public class MenuQuestion implements Question {
    @Override
    public boolean preCondition(String intent) {
        return intent.equals("MENU");
    }

    @Override
    public String process() {
        return "Ainda não sou capaz de informar o cardápio.";
    }
}
