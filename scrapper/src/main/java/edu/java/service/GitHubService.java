package edu.java.service;

import edu.java.client.GitHubClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GitHubService extends edu.java.service.Service {
    public GitHubService(GitHubClient client) {
        super(client);
    }

    @Override
    public Mono<String> getRepository(String repository) {
        return client
            .webClient.get()
            .uri("/repos/{repository}", repository)
            .retrieve()
            .bodyToMono(String.class);
    }
}
