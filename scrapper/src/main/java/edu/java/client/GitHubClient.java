package edu.java.client;

import edu.java.entity.TopicState;
import edu.java.response.GitResponse;
import org.springframework.stereotype.Component;

@Component
public class GitHubClient extends Client {
    public GitHubClient() {
        super("https://api.github.com");
    }

    public GitHubClient(String baseURL) {
        super(baseURL);
    }

    @Override
    public String getRepository(String repository) {
        return webClient.get()
            .uri("/repos/%s/events".formatted(repository))
            .retrieve()
            .bodyToMono(String.class)
            .block();
    }

    @Override
    public TopicState getPayload(String json) {
        GitResponse response = new GitResponse(json);
        return response.getData();
    }
}
