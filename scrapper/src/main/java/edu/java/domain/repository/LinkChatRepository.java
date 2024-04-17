package edu.java.domain.repository;

import edu.java.entity.LinkChat;
import java.net.URI;
import java.util.Collection;

public interface LinkChatRepository {
    void add(Long urlId, Long chatId);

    void remove(Long urlId, Long chatId);

    void remove(Long chatId);

    Collection<URI> findAll();

    Collection<URI> findAllById(Long chatId);

    Collection<Long> findAllByUrlId(Long chatId);

    LinkChat findOne(Long urlId, Long tgChatId);
}
