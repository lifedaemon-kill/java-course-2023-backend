package edu.java.domain.linksChats;

import edu.java.entity.LinkChat;
import java.net.URI;
import java.util.Collection;
import model.LinkUpdate;

public interface LinkChatRepository {
    void add(Long urlId, Long chatId);

    void remove(Long urlId, Long chatId);

    void remove(Long chatId);

    Collection<URI> findAll();

    Collection<URI> findAllById(Long chatId);

    Collection<Long> findAllByUrlId(Long chatId);

    LinkChat findOne(Long url_id, Long tg_chat_id);
}
