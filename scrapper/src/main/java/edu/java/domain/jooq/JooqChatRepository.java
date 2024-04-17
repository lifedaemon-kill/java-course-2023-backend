package edu.java.domain.jooq;

import edu.java.domain.repository.ChatRepository;
import edu.java.entity.Chat;
import java.util.Collection;
import java.util.List;

public class JooqChatRepository implements ChatRepository {

    @Override
    public void add(Long id) {

    }

    @Override
    public void update(Long id, int state) {

    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public Collection<Chat> findAll() {
        return List.of();
    }

    @Override
    public Chat findById(Long id) {
        return null;
    }
}
