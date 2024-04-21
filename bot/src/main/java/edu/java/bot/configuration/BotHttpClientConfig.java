package edu.java.bot.configuration;

import edu.java.bot.httpclient.BotHttpClient;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class BotHttpClientConfig {
    @Value("${http-client.baseURL}")
    private String baseURL;

    @Bean
    public BotHttpClient botClient() {
        return new BotHttpClient(this.baseURL);
    }


}
