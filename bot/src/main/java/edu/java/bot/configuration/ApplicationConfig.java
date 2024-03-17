package edu.java.bot.configuration;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import edu.java.bot.controller.UpdatesController;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Log4j2
@Getter
@Validated
@Configuration
public class ApplicationConfig {
    @NotEmpty @NotNull
    @Value("${app.telegram-token}")
    private String telegramToken;

    @Bean
    public TelegramBot telegramBot(ApplicationConfig appConfig) {
        TelegramBot bot = new TelegramBot(appConfig.getTelegramToken());
        log.debug("Bot created!");

        bot.setUpdatesListener(updates -> {
            UpdatesController.process(updates, bot);
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        }, e -> {
            if (e.response() != null) {
                e.response().errorCode();
                e.response().description();
            } else {
                log.error(e.getMessage(), e);
            }
        });
        return bot;
    }
}
