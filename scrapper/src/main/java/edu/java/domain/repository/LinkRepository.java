package edu.java.domain.repository;

import edu.java.entity.Link;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.Collection;

public interface LinkRepository {
    void add(URI link);

    void update(URI link, int answersCount, OffsetDateTime updatedOn);

    void remove(URI link);

    Collection<Link> findAll();

    Collection<Link> findByThreshold(OffsetDateTime thresholdTime);

    Link findByUrl(URI link);
}
