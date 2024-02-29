package edu.java.client;

import org.springframework.stereotype.Component;

@Component
public class GitHubClient extends Client {
    public GitHubClient() {
        super("https://api.github.com");
    }
}
