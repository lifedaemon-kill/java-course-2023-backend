package edu.java.domain.jooq;

import edu.java.domain.repository.ChatRepository;
import edu.java.entity.Chat;
import java.util.Collection;
import java.util.List;
import javax.sql.DataSource;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

@Repository
public class JooqChatRepository implements ChatRepository {
    private final DSLContext dslContext;

    public JooqChatRepository(DataSource dataSource) {
        this.dslContext = DSL.using(dataSource, SQLDialect.POSTGRES);
    }

    @Override
    public void add(Long id) {
    }

    @Override
    public void update(Long id, int state) {

    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public Collection<Chat> findAll() {
        return List.of();
    }

    @Override
    public Chat findById(Long id) {
        return null;
    }
}
