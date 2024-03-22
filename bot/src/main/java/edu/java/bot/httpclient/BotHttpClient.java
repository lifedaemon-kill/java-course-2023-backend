package edu.java.bot.httpclient;

import org.springframework.web.reactive.function.client.WebClient;

public class BotHttpClient {
    public final WebClient webClient;

    public BotHttpClient(String baseURL) {
        this.webClient = WebClient.create(baseURL);
    }
}
