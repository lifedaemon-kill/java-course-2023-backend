package edu.java.bot.api;

import api.errorResponse.ApiErrorResponse;
import database.DialogState;
import dto.response.DialogStateResponse;
import edu.java.bot.api.httpclient.BotHttpClient;
import jakarta.validation.constraints.Null;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import java.util.Objects;

@Log4j2
@Service
public class BotClientService {
    private final BotHttpClient client;

    public BotClientService(BotHttpClient client) {
        this.client = client;
    }

    public Mono<DialogStateResponse> getDialogState(Long id) {
        return client.webClient
            .get()
            .uri("/state")
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .header("Tg-Chat-Id", id.toString())
            .retrieve()
            .bodyToMono(DialogStateResponse.class)
            .onErrorReturn(null)
            .doOnError(error -> {
                log.error(error.toString());
            })
            .doOnNext(response -> {
                log.debug(String.format("dialog state response 200, user: %s", response.id().toString()));
            });
    }
}
