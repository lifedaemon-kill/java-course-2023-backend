package edu.java.bot.configuration;

import com.pengrad.telegrambot.ExceptionHandler;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import edu.java.bot.api.httpclient.BotHttpClient;
import edu.java.bot.controller.UpdatesController;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Log4j2
@Configuration
public class BotConfig {
    @Bean
    public UpdatesListener updatesListener() {
        return updates -> {
            //UpdatesController.process(updates);
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        };
    }

    @Bean
    public ExceptionHandler exceptionHandler() {
        return e -> {
            if (e.response() != null) {
                e.response().errorCode();
                e.response().description();
            } else {
                log.error(e.getMessage(), e);
            }
        };
    }

    @Bean
    public TelegramBot telegramBot(
        ApplicationConfig appConfig,
        UpdatesListener updatesListener,
        ExceptionHandler exceptionHandler
    ) {
        TelegramBot bot = new TelegramBot(appConfig.getTelegramToken());
        bot.setUpdatesListener(updatesListener, exceptionHandler);
        return bot;
    }
}
