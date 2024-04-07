package edu.java.domain.linksChats;

import edu.java.entity.LinkChat;
import java.net.URI;
import java.util.Collection;

public interface LinkChatRepository {
    void add(Long urlId, Long chatId);

    int remove(Long urlId, Long chatId);

    int remove(Long chatId);

    Collection<URI> findAll();

    Collection<URI> findAllById(Long chatId);

    LinkChat findOne(URI url, Long id);
}
