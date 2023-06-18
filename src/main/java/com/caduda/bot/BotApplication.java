package com.caduda.bot;

import com.caduda.bot.interfaces.Question;
import com.caduda.bot.questions.*;
import com.caduda.bot.services.BotService;
import com.caduda.bot.services.DialogflowService;
import com.caduda.bot.services.MessengerService;
import com.caduda.bot.telegram.CadudaBot;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.BotSession;
import org.telegram.telegrambots.meta.generics.LongPollingBot;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BotApplication {

    public static void main(String[] args) {
        SpringApplication.run(BotApplication.class, args);
    }

	@Bean
	public BotSession botSession(LongPollingBot longPollingBot) throws TelegramApiException {
		TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
		return telegramBotsApi.registerBot(longPollingBot);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public BusinessHoursQuestion businessHourQuestion() {
		return new BusinessHoursQuestion();
	}

	@Bean
	public LocalityQuestion localityQuestion() {
		return new LocalityQuestion();
	}

	@Bean
	public GreetingQuestion greetingQuestion() {
		return new GreetingQuestion();
	}

	@Bean
	public MenuQuestion menuQuestion() {
		return new MenuQuestion();
	}

	@Bean
	public IntentsAndAnswers questionsAndAnswers(BusinessHoursQuestion businessHoursQuestion,
												 LocalityQuestion localityQuestion,
												 GreetingQuestion greetingQuestion,
												 MenuQuestion menuQuestion){

		List<Question> answersAndQuestions = new ArrayList<>();

		answersAndQuestions.add(businessHoursQuestion);
		answersAndQuestions.add(localityQuestion);
		answersAndQuestions.add(greetingQuestion);
		answersAndQuestions.add(menuQuestion);

		return new IntentsAndAnswers(answersAndQuestions);
	}

	@Bean
	public DialogflowService dialogflowService(RestTemplate restTemplate, ObjectMapper objectMapper) {
		return new DialogflowService(restTemplate, objectMapper);
	}

	@Bean
    public BotService botService(DialogflowService dialogflowService,
								 IntentsAndAnswers intentsAndAnswers) {
        return new BotService(dialogflowService, intentsAndAnswers);
    }

	@Bean
    public MessengerService messengerService(BotService botService, RestTemplate restTemplate) {
        return new MessengerService(botService, restTemplate);
    }

	@Bean
	public CadudaBot cadudaBot(BotService botService) {
		return new CadudaBot(botService);
	}
}
