package edu.java.response;

import edu.java.entity.TopicState;
import edu.java.exception.WrongEventsCountRunTimeException;
import lombok.Getter;

@Getter
public abstract class Response {
    protected TopicState data;

    abstract int getAnswersCount(String json) throws WrongEventsCountRunTimeException;

    public Response() {
    }
}
