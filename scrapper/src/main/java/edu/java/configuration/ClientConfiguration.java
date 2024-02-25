package edu.java.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.reactive.function.client.WebClient;

@Validated
@Configuration
public class ClientConfiguration {
    @Bean
    public WebClient gitHubWebClient() {
        return WebClient.builder()
            .baseUrl("https://api.github.com")
            .build();
    }

    @Bean
    public WebClient stackOverflowWebClient() {
        return WebClient.builder()
            .baseUrl("https://api.stackexchange.com/2.3")
            .build();
    }

    @Bean
    public WebClient anyWebClient(String url) {
        return WebClient.builder()
            .baseUrl(url)
            .build();
    }
}
