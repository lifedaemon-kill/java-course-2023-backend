package edu.java.bot.configuration;

import database.DataBase;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Getter
@Validated
@Configuration
public class ApplicationConfig {
    @NotEmpty @NotNull
    @Value("${app.telegram-token}")
    private String telegramToken;


}
