package edu.java.bot.configuration;

import com.pengrad.telegrambot.TelegramBot;
import edu.java.bot.httpclient.BotClientService;
import edu.java.bot.command.NotAutorizedCommand;
import edu.java.bot.command.StartCommand;
import edu.java.bot.command.WrongCommand;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandsConfig {
    @Bean
    public StartCommand startCommand(TelegramBot bot, BotClientService service) {
        return new StartCommand(bot, service);
    }

    @Bean
    public NotAutorizedCommand notAutorizedCommand(TelegramBot bot) {
        return new NotAutorizedCommand(bot);
    }

    @Bean
    public WrongCommand wrongCommand(TelegramBot bot) {
        return new WrongCommand(bot);
    }
}
