package edu.java.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class JdbcConfiguration {
    @Value("spring.datasource.url")
    private String url;
    @Value("spring.datasource.username")
    private String username;
    @Value("spring.datasource.password")
    private String password;
    @Value("spring.datasource.driver-class-name")
    private String driver;
}
