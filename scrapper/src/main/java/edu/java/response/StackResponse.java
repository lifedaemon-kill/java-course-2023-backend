package edu.java.response;

import edu.java.entity.TopicState;
import edu.java.exception.WrongEventsCountRunTimeException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import model.EventType;
import model.UpdateInfo;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

@Log4j2
public class StackResponse implements Response {
    @Override
    public TopicState getPayload(String json) throws WrongEventsCountRunTimeException, JSONException {
        List<UpdateInfo> infoList = new ArrayList<>();
        int count = -1;
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray items = jsonObject.getJSONArray("items");
            JSONObject item = items.getJSONObject(0);

            count = item.getInt("answer_count");
            infoList.add(new UpdateInfo(EventType.STACK_ANSWER));
        } catch (JSONException e) {
            log.error(e);
        }

        return new TopicState(OffsetDateTime.now(), count, infoList);
    }

    private StackResponse() {
    }
}
