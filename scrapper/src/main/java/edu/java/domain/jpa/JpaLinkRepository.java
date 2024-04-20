package edu.java.domain.jpa;

import edu.java.domain.interfaces.LinkRepository;
import edu.java.entity.Link;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.Collection;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

@Log4j2
@Repository
public class JpaLinkRepository implements LinkRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void add(URI url) {
        Link link = new Link();
        link.setUrl(url);
        link.setAnswersCount(0);
        link.setLastUpdateAt(OffsetDateTime.now());
        em.persist(link);
    }

    @Override
    public void update(URI url, int answersCount, OffsetDateTime updatedOn) {
        Link link = em.find(Link.class, url.toString());
        if (link != null) {
            link.setAnswersCount(answersCount);
            link.setLastUpdateAt(updatedOn);
            em.merge(link);
        } else {
            log.error("Jpa link update did not found url {}", url.toString());
        }
    }

    @Override
    public void remove(URI url) {
        Link link = em.find(Link.class, url.toString());
        if (link != null) {
            em.remove(link);
        } else {
            log.error("Jpa link remove did not found url {}", url.toString());
        }
    }

    @Override
    public Collection<Link> findAll() {
        return findByThreshold(OffsetDateTime.now());
    }

    @Override
    public Collection<Link> findByThreshold(OffsetDateTime thresholdTime) {
        String sql = "SELECT l FROM Link l WHERE l.lastUpdateAt < :thresholdTime";

        TypedQuery<Link> query = em.createQuery(sql, Link.class);
        query.setParameter("thresholdTime", thresholdTime);

        try {
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Link findByUrl(URI link) {
        return em.find(Link.class, link.toString());
    }
}
