package edu.java.domain.jooq;

import edu.java.domain.repository.LinkRepository;
import edu.java.entity.Link;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;

public class JooqLinkRepository implements LinkRepository {
    @Override
    public void add(URI link) {

    }

    @Override
    public void update(URI link, int answersCount, OffsetDateTime updatedOn) {

    }

    @Override
    public void remove(URI link) {

    }

    @Override
    public Collection<Link> findAll() {
        return List.of();
    }

    @Override
    public Collection<Link> findByThreshold(OffsetDateTime thresholdTime) {
        return List.of();
    }

    @Override
    public Link findByUrl(URI link) {
        return null;
    }
}
