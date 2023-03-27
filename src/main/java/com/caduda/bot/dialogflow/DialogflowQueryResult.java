package com.caduda.bot.dialogflow;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DialogflowQueryResult {
    @JsonProperty(value = "queryText")
    private String userMessage;
    @JsonProperty(value = "fulfillmentText")
    private String dialogflowAnswer;
    private DialogflowIntent intent;

    public String getUserMessage() {
        return userMessage;
    }
    public String getDialogflowAnswer() {
        return dialogflowAnswer;
    }

    public DialogflowIntent getIntent() {
        return intent;
    }

    @Override
    public String toString() {
        return "DialogflowQueryResult{" +
                "userMessage='" + userMessage + '\'' +
                ", dialogflowAnswer='" + dialogflowAnswer + '\'' +
                ", intent=" + intent +
                '}';
    }
}
