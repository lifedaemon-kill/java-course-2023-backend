package edu.java.bot.configuration;

import com.pengrad.telegrambot.TelegramBot;
import edu.java.bot.model.DataBase;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Getter
@Validated
@Configuration
public class ApplicationConfig {
    @NotEmpty
    private String telegramToken;

    @Bean
    public DataBase dataBase() {
        return new DataBase();
    }

    @Bean
    public TelegramBot telegramBot(ApplicationConfig appConfig) {
        return new TelegramBot(appConfig.getTelegramToken());
    }

    public void setTelegramToken(String telegramToken) {
        this.telegramToken = telegramToken;
    }

    public String getTelegramToken() {
        return telegramToken;
    }
}
