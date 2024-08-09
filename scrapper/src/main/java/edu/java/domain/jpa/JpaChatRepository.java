package edu.java.domain.jpa;

import edu.java.domain.interfaces.ChatRepository;
import edu.java.entity.Chat;
import jakarta.persistence.EntityManager;
import java.time.OffsetDateTime;
import java.util.Collection;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class JpaChatRepository implements ChatRepository {
    private final EntityManager em;

    public JpaChatRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void add(Long id) {
        Chat chat = new Chat();
        chat.setId(id);
        chat.setState(0);
        chat.setLastUpdateAt(OffsetDateTime.now());
        em.persist(chat);
    }

    @Override
    public void update(Long id, int state) {
        Chat chat = em.find(Chat.class, id);
        if (chat != null) {
            chat.setState(state);
            chat.setLastUpdateAt(OffsetDateTime.now());
            em.merge(chat);
        } else {
            log.error("Jpa chat update did not find chat with id {}", id);
        }
    }

    @Override
    public void remove(Long id) {
        Chat chat = em.find(Chat.class, id);
        if (chat != null) {
            em.remove(chat);
        } else {
            log.error("Jpa chat remove did not find chat with id {}", id);
        }
    }

    @Override
    public Collection<Chat> findAll() {
        String query = "SELECT c FROM Chat c";
        return em.createQuery(query, Chat.class).getResultList();
    }

    @Override
    public Chat findById(Long id) {
        return em.find(Chat.class, id);
    }
}
