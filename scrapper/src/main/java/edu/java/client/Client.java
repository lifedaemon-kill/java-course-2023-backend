package edu.java.client;

import edu.java.entity.TopicState;
import org.springframework.web.reactive.function.client.WebClient;

public abstract class Client {
    public final WebClient webClient;

    protected Client(String baseURL) {
        this.webClient = WebClient.builder().baseUrl(baseURL).build();
    }

    public Client() {
        this("http://localhost:");
    }

    public abstract String getRepository(String repository);

    public abstract TopicState getPayload(String json);
}
