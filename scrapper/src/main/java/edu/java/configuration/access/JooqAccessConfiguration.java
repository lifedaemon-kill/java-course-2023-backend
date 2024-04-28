package edu.java.configuration.access;

import edu.java.api.service.jooq.JooqChatService;
import edu.java.api.service.jooq.JooqLinkService;
import edu.java.domain.jooq.JooqChatRepository;
import edu.java.domain.jooq.JooqLinkRepository;
import edu.java.domain.jooq.JooqSubscriptionRepository;
import javax.sql.DataSource;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jooq")
public class JooqAccessConfiguration {
    @Bean
    public DSLContext dslContext(DataSource dataSource) {
        return DSL.using(dataSource, SQLDialect.POSTGRES);
    }

    @Bean
    public JooqChatRepository jooqChatRepository(DSLContext manager) {
        return new JooqChatRepository(manager);
    }

    @Bean public JooqLinkRepository jooqLinkRepository(DSLContext manager) {
        return new JooqLinkRepository(manager);
    }

    @Bean public JooqSubscriptionRepository jooqSubscriptionRepository(DSLContext manager) {
        return new JooqSubscriptionRepository(manager);
    }

    @Bean
    public JooqLinkService jooqLinkService(
        JooqChatRepository chatRepository,
        JooqLinkRepository linkRepository,
        JooqSubscriptionRepository relationRepository
    ) {
        return new JooqLinkService(chatRepository, linkRepository, relationRepository);
    }

    @Bean
    public JooqChatService jooqChatService(
        JooqChatRepository chatRepository
    ) {
        return new JooqChatService(chatRepository);
    }
}
