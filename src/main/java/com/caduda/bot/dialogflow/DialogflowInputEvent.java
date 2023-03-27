package com.caduda.bot.dialogflow;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DialogflowInputEvent(@JsonProperty(value = "query_input") DialogflowQueryInput queryInput) {
}
