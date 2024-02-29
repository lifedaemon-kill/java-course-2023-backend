package edu.java.client;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public abstract class Client {
    public final WebClient webClient;
    protected Client() {
        this.webClient = WebClient.builder().baseUrl("http://localhost:").build();
    }
    protected Client(String baseURL) {
        this.webClient = WebClient.builder().baseUrl(baseURL).build();
    }

    public abstract Mono<String> getRepository(String repository);
}
