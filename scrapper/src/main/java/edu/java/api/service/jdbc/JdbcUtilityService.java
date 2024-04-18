package edu.java.api.service.jdbc;

import edu.java.api.service.interfaces.UtilityService;
import edu.java.domain.jdbc.JdbcChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JdbcUtilityService implements UtilityService {
    @Autowired
    private JdbcChatRepository chatRepository;

    @Override
    public boolean isChatExist(Long id) {
        return chatRepository.findById(id) != null;
    }
}
