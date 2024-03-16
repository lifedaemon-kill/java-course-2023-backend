package edu.java.api.httpclient;

import edu.java.configuration.ScrapperApiConfig;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ScrapperHttpClient {
    public final WebClient webClient;

    public ScrapperHttpClient(ScrapperApiConfig config) {
        this.webClient = WebClient.create(config.baseURL);
    }
}
