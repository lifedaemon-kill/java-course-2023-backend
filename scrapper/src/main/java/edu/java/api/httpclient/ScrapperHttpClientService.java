package edu.java.api.httpclient;

import model.LinkUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ScrapperHttpClientService {
    @Autowired
    ScrapperHttpClient client;

   public WebClient.ResponseSpec update(LinkUpdate link) {
        return client.webClient
            .post()
            .uri("/updates")
            .bodyValue(link)
            .retrieve();
    }
}
