package edu.java.domain.jooq;

import edu.java.domain.interfaces.LinkChatRepository;
import edu.java.domain.jooq.codegen.Tables;
import edu.java.entity.LinkChat;
import java.net.URI;
import java.util.Collection;
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
        dslContext.insertInto(Tables.LINKSCHATS)
            .set(Tables.LINKSCHATS.URL_ID, urlId)
            .set(Tables.LINKSCHATS.TG_CHAT_ID, chatId)
            .execute();
    }

    @Override
    public void remove(Long urlId, Long chatId) {
        dslContext.deleteFrom(Tables.LINKSCHATS)
            .where(Tables.LINKSCHATS.URL_ID.eq(urlId)
                .and(Tables.LINKSCHATS.TG_CHAT_ID.eq(chatId)))
            .execute();
    }

    @Override
    public void remove(Long chatId) {
        dslContext.deleteFrom(Tables.LINKSCHATS)
            .where(Tables.LINKSCHATS.TG_CHAT_ID.eq(chatId))
            .execute();
    }

    @Override
    public Collection<URI> findAll() {
        try {
            return dslContext.select(Tables.LINK.URL)
                .from(Tables.LINK)
                .join(Tables.LINKSCHATS)
                .on(Tables.LINK.ID.eq(Tables.LINKSCHATS.URL_ID))
                .fetch()
                .map(recordUri -> URI.create(recordUri.value1()));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Collection<URI> findAllById(Long chatId) {
        try {
            return dslContext.select(Tables.LINK.URL)
                .from(Tables.LINK)
                .join(Tables.LINKSCHATS)
                .on(Tables.LINK.ID.eq(Tables.LINKSCHATS.URL_ID))
                .where(Tables.LINKSCHATS.TG_CHAT_ID.eq(chatId))
                .fetch()
                .map(recordUri -> URI.create(recordUri.value1()));

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Collection<Long> findAllByUrlId(Long urlId) {
        return dslContext.select(Tables.LINKSCHATS.TG_CHAT_ID)
            .from(Tables.LINKSCHATS)
            .where(Tables.LINKSCHATS.URL_ID.eq(urlId))
            .fetchInto(Long.class);
    }

    @Override
    public LinkChat findOne(Long urlId, Long tgChatId) {
        var recordLinkChat = dslContext.select()
            .from(Tables.LINKSCHATS)
            .where(Tables.LINKSCHATS.TG_CHAT_ID.eq(tgChatId)
                .and(Tables.LINKSCHATS.URL_ID.eq(urlId)))
            .fetchOne();

        if (recordLinkChat != null) {
            return recordLinkChat.into(LinkChat.class);
        }
        return null;
    }
}
