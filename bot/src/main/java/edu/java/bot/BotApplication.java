package edu.java.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import edu.java.bot.configurations.ApplicationConfig;
import edu.java.bot.controllers.Controller;
import edu.java.bot.models.DataBase;
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

        var bot = new TelegramBot(config.telegramToken());
        log.debug(bot.getToken());
        DataBase dataBase = new DataBase();

        bot.setUpdatesListener(updates -> {
            //log.info(updates);
            Controller.process(updates, bot, dataBase);

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
