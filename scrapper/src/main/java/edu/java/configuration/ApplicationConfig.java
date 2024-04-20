package edu.java.configuration;

import edu.java.configuration.access.AccessType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Validated
@Configuration
public class ApplicationConfig {
    @Value("${app.database-access-type}")
    public AccessType databaseAccessType;
}
