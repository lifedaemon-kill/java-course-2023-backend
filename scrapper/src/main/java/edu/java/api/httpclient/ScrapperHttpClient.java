package edu.java.api.httpclient;

import edu.java.configuration.ScrapperConfig;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ScrapperHttpClient {
    public final WebClient webClient;

    public ScrapperHttpClient(ScrapperConfig config) {
        this.webClient = WebClient.create(config.baseURL);
    }

}
