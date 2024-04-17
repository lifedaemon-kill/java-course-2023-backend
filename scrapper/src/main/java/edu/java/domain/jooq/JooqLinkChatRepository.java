package edu.java.domain.jooq;

import edu.java.domain.repository.LinkChatRepository;
import edu.java.entity.LinkChat;
import java.net.URI;
import java.util.Collection;
import java.util.List;

public class JooqLinkChatRepository implements LinkChatRepository {
    @Override
    public void add(Long urlId, Long chatId) {

    }

    @Override
    public void remove(Long urlId, Long chatId) {

    }

    @Override
    public void remove(Long chatId) {

    }

    @Override
    public Collection<URI> findAll() {
        return List.of();
    }

    @Override
    public Collection<URI> findAllById(Long chatId) {
        return List.of();
    }

    @Override
    public Collection<Long> findAllByUrlId(Long chatId) {
        return List.of();
    }

    @Override
    public LinkChat findOne(Long urlId, Long tgChatId) {
        return null;
    }
}
