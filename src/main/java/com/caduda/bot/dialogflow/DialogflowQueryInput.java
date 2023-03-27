package com.caduda.bot.dialogflow;

import java.util.HashMap;
import java.util.Map;

public class DialogflowQueryInput {
    private Map<String, String> text = new HashMap<>();

    public DialogflowQueryInput(String userMessage) {
        text.put("text", userMessage);
        text.put("language_code", "pt-BR");
    }
}
