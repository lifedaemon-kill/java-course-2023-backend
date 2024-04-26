package edu.java.configuration.access;

import edu.java.api.service.jpa.JpaChatService;
import edu.java.api.service.jpa.JpaLinkService;
import edu.java.domain.jpa.JpaChatRepository;
import edu.java.domain.jpa.JpaLinkRepository;
import edu.java.domain.jpa.JpaSubscriptionRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jpa")
public class JpaAccessConfiguration {
    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JpaChatRepository jpaLinkRepository() {
        return new JpaChatRepository(this.entityManager);
    }

    @Bean JpaLinkRepository jpaLinkRepository(EntityManager em) {
        return new JpaLinkRepository(em);
    }

    @Bean
    public JpaSubscriptionRepository jpaLinkChatRepository() {
        return new JpaSubscriptionRepository(this.entityManager);
    }

    @Bean
    public JpaLinkService jpaLinkService(
        JpaChatRepository chatRepository,
        JpaLinkRepository linkRepository,
        JpaSubscriptionRepository relationRepository
    ) {
        return new JpaLinkService(chatRepository, linkRepository, relationRepository);
    }

    @Bean
    public JpaChatService jpaChatService(
        JpaChatRepository chatRepository
    ) {
        return new JpaChatService(chatRepository);
    }
}
