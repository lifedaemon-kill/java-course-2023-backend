package edu.java.bot.httpclient;

import edu.java.bot.configuration.BotHttpClientConfig;
import org.springframework.web.reactive.function.client.WebClient;

public class BotHttpClient {
    public WebClient webClient;

    public BotHttpClient(BotHttpClientConfig config){
        this.webClient = WebClient.create(config.getBaseURL());
    }
}
