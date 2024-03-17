package edu.java.bot;

import edu.java.bot.configuration.ApplicationConfig;
import edu.java.bot.configuration.BotHttpClientConfig;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Log4j2
@SpringBootApplication
public class BotApplication {
    private static ApplicationConfig config;
    private static BotHttpClientConfig clientConfig;

    BotApplication(ApplicationConfig config, BotHttpClientConfig clientConfig) {
        BotApplication.config = config;
        BotApplication.clientConfig = clientConfig;
    }

    public static void main(String[] args) {
        SpringApplication.run(BotApplication.class, args);
        log.info("CODE: " + config.getTelegramToken());
        log.info("CODE2: " + clientConfig.getBaseURL());
    }
}
