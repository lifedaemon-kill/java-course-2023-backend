package edu.java.configuration;

import edu.java.client.GitHubClient;
import edu.java.service.GitHubService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public GitHubService GitHubService(GitHubClient client){
        return new GitHubService(client);
    }
}
