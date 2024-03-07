package edu.java.response;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.java.model.TopicState;
import java.time.OffsetDateTime;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class StackResponse extends Response {
    @Override
    public int getAnswersCount(String json) throws WrongThreadException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(json);
            return jsonNode.get("answer_count").asInt();
        } catch (Exception e) {
            log.error(e);
            throw new WrongThreadException();
        }
    }

    public StackResponse(String json) {
        this.data = new TopicState(
            OffsetDateTime.now(),
            this.getAnswersCount(json)
        );
    }
}
