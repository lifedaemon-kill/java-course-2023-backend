package edu.java.configuration;

import edu.java.client.GitHubClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Validated
@Configuration
public class ClientConfiguration {
    @Bean
    public GitHubClient gitHubClient() {
        return new GitHubClient();
    }
}
