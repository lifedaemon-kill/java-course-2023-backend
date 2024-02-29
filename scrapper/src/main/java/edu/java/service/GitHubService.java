package edu.java.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class GitHubService {
    private final WebClient webClient;

    public GitHubService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<String> getRepository(String repo) {
        return webClient.get()
            .uri("/repos/{repo}", repo)
            .retrieve()
            .bodyToMono(String.class);
    }
}
