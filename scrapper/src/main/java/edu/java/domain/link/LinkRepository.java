package edu.java.domain.link;

import dto.response.LinkResponse;
import edu.java.entity.Link;
import java.net.URI;
import java.util.Collection;

public interface LinkRepository {
    void add(URI link);

    void remove(URI link);

    Collection<Link> findAll(String where);
}
