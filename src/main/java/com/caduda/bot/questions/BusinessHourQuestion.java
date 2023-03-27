package com.caduda.bot.questions;

import com.caduda.bot.interfaces.Question;

public class BusinessHourQuestion implements Question {
    @Override
    public boolean preCondition(String intent) {
        return intent.equals("BUSINESS_HOUR");
    }

    @Override
    public String process() {
        return "8h~12h - 13h~18h";
    }
}
