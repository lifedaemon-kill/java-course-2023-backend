package edu.java.bot.configuration;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class RetryClientConfig {
    private static final List<HttpStatus> HTTP_STATUSES =
        Arrays.asList(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.BAD_GATEWAY);
    private static final Integer MAX_RETRIES = 3;
    private static final Duration INITIAL_BACKOFF = Duration.ofSeconds(1);
}
