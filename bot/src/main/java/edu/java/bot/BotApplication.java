package edu.java.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import edu.java.bot.configuration.ApplicationConfig;
import edu.java.bot.controller.Controller;
import edu.java.bot.model.DataBase;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import static edu.java.bot.utility.Token.getFromFileTelegramToken;

@Log4j2
@Component
@SpringBootApplication
//@EnableConfigurationProperties(ApplicationConfig.class)
public class BotApplication {
    @Autowired
    private static ApplicationConfig appConfig = new ApplicationConfig();

    static {
        appConfig.setTelegramToken(getFromFileTelegramToken());
    }

    @Autowired
    private static TelegramBot bot = new TelegramBot(appConfig.getTelegramToken());
    @Autowired
    private static DataBase dataBase = new DataBase();

    public static void main(String[] args) {
        SpringApplication.run(BotApplication.class, args);

        log.debug(bot.getToken());
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
                log.error(e.getStackTrace());
            }
        });
    }
}
