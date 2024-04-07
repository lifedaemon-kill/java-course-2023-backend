package edu.java.domain.link;

import edu.java.entity.Link;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.Collection;

public interface LinkRepository {
    void add(URI link);

    void remove(URI link);

    Collection<Link> findAll();

    Collection<Link> findByThreshold(OffsetDateTime thresholdTime);

    Link findByUrl(URI link);
}
