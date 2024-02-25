package edu.java.bot.configuration;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Getter
@Validated
@Configuration
//@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
//@ConfigurationPropertiesScan("application.yml")
@Setter
public class ApplicationConfig {
    @NotEmpty
    //@Value("${telegram-token}")
    private String telegramToken;
}
