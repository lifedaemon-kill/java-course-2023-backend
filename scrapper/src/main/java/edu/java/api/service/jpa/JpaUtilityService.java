package edu.java.api.service.jpa;

import edu.java.api.service.interfaces.UtilityService;
import edu.java.domain.jpa.JpaChatRepository;
import org.springframework.stereotype.Service;

@Service
public class JpaUtilityService implements UtilityService {
    private JpaChatRepository chatRepository;

    @Override
    public boolean isChatExist(Long id) {
        return chatRepository.findById(id) != null;
    }
}
