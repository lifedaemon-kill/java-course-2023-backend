package edu.java.client;

import org.springframework.web.reactive.function.client.WebClient;

public abstract class Client {
    protected final WebClient webClient;

    protected Client(String baseURL) {
        this.webClient = WebClient.builder().baseUrl(baseURL).build();
    }
}
