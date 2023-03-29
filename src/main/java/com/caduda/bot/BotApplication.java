package com.caduda.bot;

import com.caduda.bot.controller.MessengerWebhookController;
import com.caduda.bot.interfaces.Question;
import com.caduda.bot.questions.BusinessHourQuestion;
import com.caduda.bot.questions.IntentsAndAnswers;
import com.caduda.bot.questions.LocalityQuestion;
import com.caduda.bot.services.BotService;
import com.caduda.bot.services.DialogflowService;
import com.caduda.bot.services.MessengerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BotApplication {

    public static void main(String[] args) {
        SpringApplication.run(BotApplication.class, args);
    }

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public BusinessHourQuestion businessHourQuestion() {
		return new BusinessHourQuestion();
	}

	@Bean
	public LocalityQuestion localityQuestion() {
		return new LocalityQuestion();
	}

	@Bean
	public IntentsAndAnswers questionsAndAnswers(BusinessHourQuestion businessHourQuestion,
												 LocalityQuestion localityQuestion){

		List<Question> answersAndQuestions = new ArrayList<>();

		answersAndQuestions.add(businessHourQuestion);
		answersAndQuestions.add(localityQuestion);

		return new IntentsAndAnswers(answersAndQuestions);
	}

	@Bean
    public BotService botService(RestTemplate restTemplate,
								 ObjectMapper objectMapper,
								 IntentsAndAnswers intentsAndAnswers) {


        return new BotService(new DialogflowService(restTemplate, objectMapper), intentsAndAnswers);
    }

	@Bean
    public MessengerService messengerService(BotService botService, RestTemplate restTemplate) {
        return new MessengerService(botService, restTemplate);
    }
}
