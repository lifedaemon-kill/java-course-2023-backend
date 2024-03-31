package edu.java.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.java.entity.TopicState;
import java.time.OffsetDateTime;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class GitResponse extends Response {
    @Override
    public int getAnswersCount(String json) throws WrongThreadException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readTree(json).size();
        } catch (Exception e) {
            log.error(e);
            throw new WrongThreadException();
        }
    }

    public GitResponse(String json) {
        this.data = new TopicState(
            OffsetDateTime.now(),
            this.getAnswersCount(json)
        );
    }
}
