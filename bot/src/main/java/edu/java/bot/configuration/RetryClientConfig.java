package edu.java.bot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@Configuration
public class RetryClientConfig {
    private static final List<HttpStatus> retriableStatuses = Arrays.asList(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.BAD_GATEWAY);
    private static final Integer maxRetries = 3;
    private static final Duration initialBackoff = Duration.ofSeconds(1);
}
