package edu.java.api.service.jooq;

import edu.java.api.service.interfaces.UtilityService;
import edu.java.domain.jooq.JooqChatRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class JooqUtilityService implements UtilityService {
    @Autowired
    private JooqChatRepository chatRepository;

    @Override
    public boolean isChatExist(Long id) {
        return chatRepository.findById(id) != null;
    }
}
