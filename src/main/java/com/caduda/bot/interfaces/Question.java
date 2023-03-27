package com.caduda.bot.interfaces;

public interface Question {
    boolean preCondition(String intent);
    String process();
}
