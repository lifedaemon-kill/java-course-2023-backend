package edu.java.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import edu.java.bot.configurations.ApplicationConfig;
import edu.java.bot.controllers.BotController;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import static edu.java.bot.utilities.Token.getTelegramToken;

@Log4j2
@SpringBootApplication
//@EnableConfigurationProperties(ApplicationConfig.class)
public class BotApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(BotApplication.class, args);

        ApplicationConfig config = new ApplicationConfig(getTelegramToken());
        log.debug(config.telegramToken());

        var bot = new TelegramBot(config.telegramToken());

        bot.setUpdatesListener(updates -> {
            log.info(updates);
            BotController.process(updates, bot);

            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        }, e -> {
            if (e.response() != null) {
                // got bad response from telegram
                e.response().errorCode();
                e.response().description();
            } else {
                // probably network error
                log.info(e.getStackTrace());
            }
        });
    }
}
