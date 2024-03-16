package edu.java.bot.configuration;

import edu.java.bot.api.httpclient.BotHttpClient;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Configuration
public class BotHttpClientConfig {
    @NotEmpty @NotNull
    @Value("http-client.baseURL")
    public String baseURL;

    @Bean
    public BotHttpClient botClient(BotHttpClientConfig config){return new BotHttpClient(config);}
}
