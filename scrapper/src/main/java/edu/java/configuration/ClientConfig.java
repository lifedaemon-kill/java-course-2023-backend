package edu.java.configuration;

import edu.java.client.GitHubClient;
import edu.java.client.StackOverFlowClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Validated
@Configuration
public class ClientConfig {

    @Bean
    public GitHubClient gitHubClient() {
        return new GitHubClient();
    }

    @Bean
    public StackOverFlowClient stackOverFlowClient() {
        return new StackOverFlowClient();
    }
}
