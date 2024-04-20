package edu.java.domain.jpa;

import edu.java.domain.interfaces.LinkChatRepository;
import edu.java.entity.LinkChat;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.net.URI;
import java.util.Collection;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

@Log4j2
@SuppressWarnings("MultipleStringLiterals")
@Repository
public class JpaLinkChatRepository implements LinkChatRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void add(Long urlId, Long chatId) {
        LinkChat linkChat = new LinkChat();
        linkChat.setUrlId(urlId);
        linkChat.setTgChatId(chatId);
        em.persist(linkChat);
    }

    @Override
    public void remove(Long urlId, Long chatId) {
        String jpql = "DELETE FROM LinkChat lc WHERE lc.urlId = :urlId AND lc.tgChatId = :chatId";
        em.createQuery(jpql)
            .setParameter("urlId", urlId)
            .setParameter("chatId", chatId)
            .executeUpdate();
    }

    @Override
    public void remove(Long chatId) {
        String jpql = "DELETE FROM LinkChat lc WHERE lc.tgChatId = :chatId";
        em.createQuery(jpql)
            .setParameter("chatId", chatId)
            .executeUpdate();
    }

    @Override public Collection<URI> findAll() {
        String jpql = "SELECT l.url FROM Link l "
                      + "JOIN LinkChat r ON l.id = r.urlId";
        try {
            TypedQuery<String> query = em.createQuery(jpql, String.class);
            List<String> resultList = query.getResultList();
            return resultList.stream().map(URI::create).toList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override public Collection<URI> findAllById(Long chatId) {
        String jpql = "SELECT l.url FROM Link l "
                      + "JOIN LinkChat r ON l.id = r.urlId "
                      + "WHERE r.tgChatId = :chatId";
        try {
            TypedQuery<String> query = em.createQuery(jpql, String.class);
            query.setParameter("chatId", chatId);
            List<String> resultList = query.getResultList();
            return resultList.stream().map(URI::create).toList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Collection<Long> findAllByUrlId(Long urlId) {
        String jpql = "SELECT l.tgChatId FROM LinkChat l WHERE l.urlId = :urlId";
        TypedQuery<Long> query = em.createQuery(jpql, Long.class)
            .setParameter("urlId", urlId);
        return query.getResultList();
    }

    @Override
    public LinkChat findOne(Long urlId, Long tgChatId) {
        String jpql = "SELECT lc FROM LinkChat lc WHERE lc.urlId = :urlId AND lc.tgChatId = :chatId";
        TypedQuery<LinkChat> query = em.createQuery(jpql, LinkChat.class)
            .setParameter("urlId", urlId)
            .setParameter("chatId", tgChatId);
        return query.getSingleResult();
    }
}
