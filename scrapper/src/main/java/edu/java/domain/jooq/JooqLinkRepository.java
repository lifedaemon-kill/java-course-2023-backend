package edu.java.domain.jooq;

import edu.java.domain.interfaces.LinkRepository;
import edu.java.domain.jooq.codegen.Tables;
import edu.java.entity.Link;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.Collection;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

public class JooqLinkRepository implements LinkRepository {
    private final DSLContext dslContext;

    public JooqLinkRepository(DSLContext manager) {
        this.dslContext = manager;
    }

    @Override
    public void add(URI link) {
        dslContext.insertInto(Tables.LINK)
            .set(Tables.LINK.URL, link.toString())
            .set(Tables.LINK.ANSWERS_COUNT, 0)
            .set(Tables.LINK.LAST_UPDATE_AT, OffsetDateTime.now())
            .execute();
    }

    @Override
    public void update(URI link, int answersCount, OffsetDateTime updatedOn) {
        dslContext.update(Tables.LINK)
            .set(Tables.LINK.ANSWERS_COUNT, answersCount)
            .set(Tables.LINK.LAST_UPDATE_AT, updatedOn)
            .where(Tables.LINK.URL.eq(link.toString()))
            .execute();
    }

    @Override
    public void remove(URI link) {
        dslContext.delete(Tables.LINK)
            .where(Tables.LINK.URL.eq(link.toString()))
            .execute();
    }

    @Override
    public Collection<Link> findAll() {
        return findByThreshold(OffsetDateTime.now());
    }

    @Override
    public Collection<Link> findByThreshold(OffsetDateTime thresholdTime) {
        return dslContext.selectFrom(Tables.LINK)
            .where(Tables.LINK.LAST_UPDATE_AT.lt(thresholdTime))
            .fetchInto(Link.class);
    }

    @Override
    public Link findByUrl(URI link) {
        return dslContext.selectFrom(Tables.LINK)
            .where(Tables.LINK.URL.eq(link.toString()))
            .fetchOneInto(Link.class);
    }
}
