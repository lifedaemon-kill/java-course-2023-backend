package edu.java.bot.httpclient;

import dto.request.AddLinkRequest;
import dto.request.ChangeDialogStateRequest;
import dto.response.DialogStateResponse;
import dto.response.LinkResponse;
import dto.response.ListLinksResponse;
import edu.java.bot.configuration.BotHttpClientConfig;
import java.net.URI;
import lombok.extern.log4j.Log4j2;
import model.DialogState;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class BotClientService {
    private final BotHttpClient client;
    private final String tgChatIdHeader = "Tg-Chat-Id";
    private final String stateRepo = "/state";
    private final String tgChatRepo = "/tg-chat/{id}";
    private final String linksRepo = "/links";

    public BotClientService(BotHttpClientConfig clientConfig) {
        this.client = clientConfig.botClient();
    }

    public ResponseEntity<DialogStateResponse> getDialogState(Long id) {
        return client.webClient
            .get()
            .uri(stateRepo)
            .header(tgChatIdHeader, id.toString())
            .retrieve()
            .toEntity(DialogStateResponse.class)
            .block();
    }

    public ResponseEntity<Object> postDialogState(Long id, DialogState dialogState) {
        return client.webClient
            .post()
            .uri(stateRepo)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .header(tgChatIdHeader, id.toString())
            .bodyValue((new ChangeDialogStateRequest(dialogState)))
            .retrieve()
            .toEntity(Object.class)
            .block();
    }

    public ResponseEntity<Object> registerChat(Long id) {
        return client.webClient
            .post()
            .uri(tgChatRepo, id)
            .retrieve()
            .toEntity(Object.class)
            .block();
    }

    public ResponseEntity<Object> deleteChat(Long id) {
        return client.webClient
            .delete()
            .uri(tgChatRepo, id)
            .retrieve()
            .toEntity(Object.class)
            .block();
    }

    public ResponseEntity<ListLinksResponse> getLinks(Long id) {
        return client.webClient
            .get()
            .uri(linksRepo)
            .header(tgChatIdHeader, id.toString())
            .retrieve()
            .toEntity(ListLinksResponse.class)
            .block();
    }

    public ResponseEntity<LinkResponse> addLink(Long id, URI link) {
        return client.webClient
            .post()
            .uri(linksRepo)
            .header(tgChatIdHeader, id.toString())
            .bodyValue(new AddLinkRequest(link))
            .retrieve()
            .toEntity(LinkResponse.class)
            .block();
    }

    public ResponseEntity<LinkResponse> deleteLink(Long id, URI link) {
        return client.webClient
            .delete()
            .uri(linksRepo)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .header(tgChatIdHeader, id.toString())
            .header("Link", link.toString())
            .retrieve()
            .toEntity(LinkResponse.class)
            .block();
    }
}
