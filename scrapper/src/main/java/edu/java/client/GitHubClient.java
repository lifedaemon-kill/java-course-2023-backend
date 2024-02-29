package edu.java.client;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class GitHubClient extends Client {
    public GitHubClient() {
        super("https://api.github.com");
    }

    @Override
    public Mono<String> getRepository(String repository) {
        return webClient.get()
            .uri("/repos/%s".formatted(repository))
            .retrieve()
            .bodyToMono(String.class);
    }
}
