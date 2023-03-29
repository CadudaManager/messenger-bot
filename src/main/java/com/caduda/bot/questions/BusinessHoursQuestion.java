package com.caduda.bot.questions;

import com.caduda.bot.interfaces.Question;

public class BusinessHoursQuestion implements Question {
    @Override
    public boolean preCondition(String intent) {
        return intent.equals("BUSINESS_HOURS");
    }

    @Override
    public String process() {
        return "Funcionamos das 17h às 2h de quarta a sábado!";
    }
}
