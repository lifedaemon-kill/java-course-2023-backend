package edu.java.configuration;

import database.DataBase;
import edu.java.api.httpclient.ScrapperHttpClient;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class ScrapperApiConfig {
    @NotEmpty @NotNull
    @Value("http-client.baseURL")
    public String baseURL;

    @Bean
    public ScrapperHttpClient getScrapperHttpClient(ScrapperApiConfig config) {
        return new ScrapperHttpClient(config);
    }
    @Bean
    public DataBase getDataBase() {
        return new DataBase();
    }
}
