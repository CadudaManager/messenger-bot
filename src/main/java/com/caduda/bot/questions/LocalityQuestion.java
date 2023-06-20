package com.caduda.bot.questions;

import com.caduda.bot.interfaces.Question;

public class LocalityQuestion implements Question {
    @Override
    public boolean preCondition(String intent) {
        return intent.equals("LOCALITY");
    }

    @Override
    public String process() {
        return "Temos duas unidades do Espetinho Caduda:\n" +
                "Uma fica localizada no Orlando Dantas, na rua Maria Rosa Lima, nº 117.\n" +
                "A outra fica na Farolândia, Praça Jornalista Orlando Dantas, nº 36, conhecida como Praça do Portela.";
    }
}
