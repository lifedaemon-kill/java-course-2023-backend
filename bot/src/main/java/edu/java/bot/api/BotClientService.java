package edu.java.bot.api;

import database.DialogState;
import dto.request.ChangeDialogStateRequest;
import dto.response.DialogStateResponse;
import edu.java.bot.configuration.BotHttpClientConfig;
import edu.java.bot.httpclient.BotHttpClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class BotClientService {
    private final BotHttpClient client;

    public BotClientService(BotHttpClientConfig clientConfig) {
        this.client = clientConfig.botClient();
    }

    public DialogStateResponse getDialogState(Long id) {
        return client.webClient
            .get()
            .uri("/state")
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .header("Tg-Chat-Id", id.toString())
            .retrieve()
            .bodyToMono(DialogStateResponse.class)
            .doOnError(error -> {
                log.error(error.toString());
            })
            .doOnNext(response -> {
                log.debug(String.format("dialog state response 200, user: %s", response.id().toString()));
            })
            .block();
    }

    public ResponseEntity<Object> postDialogState(Long id, DialogState dialogState) {
        return client.webClient
            .post()
            .uri("/state")
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .header("Tg-Chat-Id", id.toString())
            .bodyValue((new ChangeDialogStateRequest(dialogState)))
            .retrieve()
            .toEntity(Object.class)
            .block();
    }

    public ResponseEntity<Object> postRegisterChat(Long id) {
        return client.webClient
            .post()
            .uri("/tg-chat/{id}", id)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .retrieve()
            .toEntity(Object.class)
            .block();
    }
}
