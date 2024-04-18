package edu.java.domain.chat;

import edu.java.entity.Chat;
import java.util.Collection;

public interface ChatRepository {
    void add(Long id);

    void update(Long id, int state);

    void remove(Long id);

    Collection<Chat> findAll();

    Chat findById(Long id);
}
