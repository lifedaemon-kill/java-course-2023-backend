package edu.java.domain.jooq;

import edu.java.domain.interfaces.ChatRepository;
import edu.java.domain.jooq.codegen.Tables;
import edu.java.domain.jooq.codegen.tables.records.ChatRecord;
import edu.java.entity.Chat;
import java.time.OffsetDateTime;
import java.util.Collection;
import javax.sql.DataSource;
import org.jetbrains.annotations.Nullable;
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
        dslContext.insertInto(Tables.CHAT)
            .set(Tables.CHAT.TG_CHAT_ID, id)
            .set(Tables.CHAT.STATE, 0)
            .set(Tables.CHAT.LAST_UPDATE_AT, OffsetDateTime.now())
            .execute();
    }

    @Override
    public void update(Long id, int state) {
        dslContext.update(Tables.CHAT)
            .set(Tables.CHAT.STATE, state)
            .set(Tables.CHAT.LAST_UPDATE_AT, OffsetDateTime.now())
            .where(Tables.CHAT.TG_CHAT_ID.eq(id))
            .execute();
    }

    @Override
    public void remove(Long id) {
        dslContext.delete(Tables.CHAT)
            .where(Tables.CHAT.TG_CHAT_ID.eq(id))
            .execute();
    }

    @Override
    public Collection<Chat> findAll() {
        return dslContext.selectFrom(Tables.CHAT)
            .fetchInto(Chat.class);
    }

    @Override
    public Chat findById(Long id) {
        @Nullable ChatRecord chatRecord = dslContext.selectFrom(Tables.CHAT)
            .where(Tables.CHAT.TG_CHAT_ID.eq(id))
            .fetchOne();

        if (chatRecord != null) {
            return chatRecord.into(Chat.class);
        } else {
            return null;
        }
    }
}
