package edu.java.configuration.access;

import edu.java.api.service.jdbc.JdbcChatService;
import edu.java.api.service.jdbc.JdbcLinkService;
import edu.java.domain.jdbc.JdbcChatRepository;
import edu.java.domain.jdbc.JdbcLinkRepository;
import edu.java.domain.jdbc.JdbcSubscriptionRepository;
import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jdbc")
public class JdbcAccessConfiguration {
    @Bean JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public JdbcChatRepository jdbcChatRepository(JdbcTemplate manager) {
        return new JdbcChatRepository(manager);
    }

    @Bean
    public JdbcLinkRepository jdbcLinkRepository(JdbcTemplate manager) {
        return new JdbcLinkRepository(manager);
    }

    @Bean
    public JdbcSubscriptionRepository jdbcLinkChatRepository(JdbcTemplate manager) {
        return new JdbcSubscriptionRepository(manager);
    }

    @Bean
    public JdbcLinkService jdbcLinkService(
        JdbcChatRepository chatRepository,
        JdbcLinkRepository linkRepository,
        JdbcSubscriptionRepository relationRepository
    ) {
        return new JdbcLinkService(chatRepository, linkRepository, relationRepository);
    }

    @Bean
    public JdbcChatService jdbcChatService(
        JdbcChatRepository chatRepository
    ) {
        return new JdbcChatService(chatRepository);
    }
}
