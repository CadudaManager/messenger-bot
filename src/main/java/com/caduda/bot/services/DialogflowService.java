package com.caduda.bot.services;

import com.auth0.jwt.JWT;

import com.auth0.jwt.algorithms.Algorithm;
import com.caduda.bot.dialogflow.*;
import com.caduda.bot.interfaces.NLPInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.ServiceAccountCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.interfaces.RSAPrivateKey;
import java.util.Date;

public class DialogflowService implements NLPInterface {
    @Value("${serviceAccount}")
    private String serviceAccount;
    @Value("${apiEndpoint}")
    private String apiEndpoint;
    @Value("${dialogflowUrl}")
    private String dialogflowUrl;
    private final long ONE_HOUR_IN_MILLISECONDS = 3600 * 1000;
    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;

    public DialogflowService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public String getIntent(String userMessage) {
        String assignedJwt = generateSignedJWT();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + assignedJwt);

        DialogflowQueryInput dialogflowQueryInput = new DialogflowQueryInput(userMessage);
        DialogflowInputEvent dialogflowInputEvent = new DialogflowInputEvent(dialogflowQueryInput);

        HttpEntity<DialogflowInputEvent> entity = new HttpEntity<>(dialogflowInputEvent, headers);

        DialogflowResponse dialogflowResponse = restTemplate.postForObject(dialogflowUrl, entity,
                DialogflowResponse.class);


        assert dialogflowResponse != null;
        return getIntentFromResponse(dialogflowResponse);
    }

    private String getIntentFromResponse(DialogflowResponse dialogflowResponse) {
        DialogflowQueryResult dialogflowQueryResult = dialogflowResponse.queryResult();
        DialogflowIntent dialogflowIntent = dialogflowQueryResult.getIntent();
        return dialogflowIntent.displayName();
    }

    private String generateSignedJWT() {
        try {
            FileInputStream stream = new FileInputStream("src/main/resources/googleCredentials.json");
            ServiceAccountCredentials cred = ServiceAccountCredentials.fromStream(stream);
            RSAPrivateKey key = (RSAPrivateKey) cred.getPrivateKey();
            Algorithm algorithm = Algorithm.RSA256(null, key);

            Date currentDate = new Date();
            Date expirationDate = new Date(currentDate.getTime() + ONE_HOUR_IN_MILLISECONDS);

            return JWT.create()
                    .withIssuedAt(currentDate)
                    .withExpiresAt(expirationDate)
                    .withIssuer(serviceAccount)
                    .withAudience(apiEndpoint)
                    .withSubject(serviceAccount)
                    .withClaim("email", serviceAccount)
                    .sign(algorithm);

        } catch (IOException ex) {
            System.out.println("Erro ao tentar importar credenciais -> " + ex.getMessage());
            return "";
        }
    }
}
