package com.caduda.bot.questions;

import com.caduda.bot.interfaces.Question;

public class LocalityQuestion implements Question {
    @Override
    public boolean preCondition(String intent) {
        return intent.equals("LOCALITY");
    }

    @Override
    public String process() {
        return "Temos uma unidade do Espetinho Caduda no Orlando Dantas na rua Maria Rosa Lima, número 117. " +
                "E temos também a unidade da Atalaia, localizada na rua Niceu Dantas, número 50.";
    }
}
