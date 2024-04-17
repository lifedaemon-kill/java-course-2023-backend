package edu.java.domain.jooq;

import edu.java.domain.repository.LinkChatRepository;
import edu.java.entity.LinkChat;
import java.net.URI;
import java.util.Collection;
import java.util.List;
import javax.sql.DataSource;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

@Repository
public class JooqLinkChatRepository implements LinkChatRepository {
    private final DSLContext dslContext;

    public JooqLinkChatRepository(DataSource dataSource) {
        this.dslContext = DSL.using(dataSource, SQLDialect.POSTGRES);
    }

    @Override
    public void add(Long urlId, Long chatId) {

    }

    @Override
    public void remove(Long urlId, Long chatId) {

    }

    @Override
    public void remove(Long chatId) {

    }

    @Override
    public Collection<URI> findAll() {
        return List.of();
    }

    @Override
    public Collection<URI> findAllById(Long chatId) {
        return List.of();
    }

    @Override
    public Collection<Long> findAllByUrlId(Long chatId) {
        return List.of();
    }

    @Override
    public LinkChat findOne(Long urlId, Long tgChatId) {
        return null;
    }
}
