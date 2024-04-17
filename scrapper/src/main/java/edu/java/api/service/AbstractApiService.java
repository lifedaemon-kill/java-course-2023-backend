package edu.java.api.service;

import edu.java.domain.jdbc.JdbcChatRepository;
import edu.java.domain.jdbc.JdbcLinkRepository;
import edu.java.domain.repository.LinkChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class AbstractApiService {
    @Autowired
    protected JdbcChatRepository chatRepository;
    @Autowired
    protected JdbcLinkRepository linkRepository;
    @Autowired
    protected LinkChatRepository relationRepository;

    public boolean isChatExist(Long id) {
        return chatRepository.findById(id) != null;
    }
}
