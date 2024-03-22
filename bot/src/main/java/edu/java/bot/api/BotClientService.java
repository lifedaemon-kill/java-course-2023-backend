package edu.java.bot.api;

import database.DialogState;
import dto.request.AddLinkRequest;
import dto.request.ChangeDialogStateRequest;
import dto.response.DialogStateResponse;
import dto.response.LinkResponse;
import dto.response.ListLinksResponse;
import edu.java.bot.configuration.BotHttpClientConfig;
import edu.java.bot.httpclient.BotHttpClient;
import java.net.URI;
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

    public ResponseEntity<DialogStateResponse> getDialogState(Long id) {
        return client.webClient
            .get()
            .uri("/state")
            //.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .header("Tg-Chat-Id", id.toString())
            .retrieve()
            .toEntity(DialogStateResponse.class)
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

    public ResponseEntity<Object> registerChat(Long id) {
        return client.webClient
            .post()
            .uri("/tg-chat/{id}", id)
            .retrieve()
            .toEntity(Object.class)
            .block();
    }

    public ResponseEntity<Object> deleteChat(Long id) {
        return client.webClient
            .delete()
            .uri("/tg-chat/{id}", id)
            .retrieve()
            .toEntity(Object.class)
            .block();
    }

    public ResponseEntity<ListLinksResponse> getLinks(Long id) {
        return client.webClient
            .get()
            .uri("/links")
            .header("Tg-Chat-Id", id.toString())
            .retrieve()
            .toEntity(ListLinksResponse.class)
            .block();
    }

    public ResponseEntity<LinkResponse> addLink(Long id, URI link) {
        return client.webClient
            .post()
            .uri("/links")
            .header("Tg-Chat-Id", id.toString())
            .bodyValue(new AddLinkRequest(link))
            .retrieve()
            .toEntity(LinkResponse.class)
            .block();
    }

    public ResponseEntity<LinkResponse> deleteLink(Long id, URI link) {
        return client.webClient
            .delete()
            .uri("/links")
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .header("Tg-Chat-Id", id.toString())
            .header("Link", link.toString())
            .retrieve()
            .toEntity(LinkResponse.class)
            .block();
    }
}
