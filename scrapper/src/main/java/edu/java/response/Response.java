package edu.java.response;

import edu.java.entity.TopicState;
import edu.java.exception.WrongEventsCountRunTimeException;
import org.springframework.boot.configurationprocessor.json.JSONException;

public interface Response {
    TopicState getPayload(String json) throws WrongEventsCountRunTimeException, JSONException;
}
