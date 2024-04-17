package edu.java.response;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.java.entity.TopicState;
import edu.java.exception.WrongEventsCountRunTimeException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import model.EventType;
import model.UpdateInfo;
import org.springframework.boot.configurationprocessor.json.JSONException;

@Log4j2
public class GitResponse implements Response {
    @Override
    public TopicState getPayload(String json) throws WrongEventsCountRunTimeException, JSONException {
        List<UpdateInfo> infoList = new ArrayList<>();
        int count = -1;
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(json);

            for (JsonNode node : jsonNode) {
                String type = node.get("type").asText();
                EventType event = switch (type) {
                    case "PushEvent" -> EventType.GIT_PUSH;
                    case "PullRequestEvent" -> EventType.GIT_PULL;
                    case "CreateEvent" -> EventType.GIT_BRANCH;
                    default -> EventType.BASIC;
                };
                infoList.add(new UpdateInfo(event));
            }
        } catch (Exception e) {
            log.error(e);
            throw new WrongThreadException();
        }

        return new TopicState(OffsetDateTime.now(), infoList.size(), infoList);
    }

    private GitResponse() {
    }

    ;
}
