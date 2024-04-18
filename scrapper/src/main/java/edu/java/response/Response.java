package edu.java.response;

import edu.java.entity.TopicState;
import edu.java.exception.WrongEventsCountRunTimeException;
import lombok.Getter;
import org.springframework.boot.configurationprocessor.json.JSONException;

@Getter
public abstract class Response {
    protected TopicState data;

    abstract int getAnswersCount(String json) throws WrongEventsCountRunTimeException, JSONException;

    public Response() {
    }
}
