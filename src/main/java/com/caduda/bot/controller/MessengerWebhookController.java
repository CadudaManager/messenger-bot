package com.caduda.bot.controller;

import com.caduda.bot.messenger.MessengerEvent;
import com.caduda.bot.messenger.ReceivedEventFormatted;
import com.caduda.bot.services.MessengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messenger/webhook")
public class MessengerWebhookController {

    @Value("${validationToken}")
    private String VALIDATION_TOKEN;

    @Autowired
    private MessengerService messengerService;

    @GetMapping
    ResponseEntity<Integer> validateWebhook(@RequestParam(value = "hub.verify_token") String verify_token,
                                            @RequestParam(value = "hub.challenge") Integer challenge) {
        if (verify_token.equals(VALIDATION_TOKEN)) {
            return ResponseEntity.ok(challenge);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }

    @PostMapping
    ResponseEntity<String> receiveEvent(MessengerEvent messengerEvent) {
        ReceivedEventFormatted receivedEventFormatted = new ReceivedEventFormatted(messengerEvent);

        messengerService.answerUser(receivedEventFormatted);

        return ResponseEntity.ok().body(null);
    }
}
