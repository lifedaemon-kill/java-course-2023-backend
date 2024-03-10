package edu.java.response;

import edu.java.exception.WrongEventsCountRunTimeException;
import edu.java.model.TopicState;
import lombok.Getter;

@Getter
public abstract class Response {
    protected TopicState data;

    abstract int getAnswersCount(String json) throws WrongEventsCountRunTimeException;

    public Response() {
    }
}
