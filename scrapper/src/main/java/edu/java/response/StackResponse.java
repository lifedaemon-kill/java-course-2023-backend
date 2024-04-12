package edu.java.response;

import edu.java.entity.TopicState;
import java.time.OffsetDateTime;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

@Log4j2
public class StackResponse extends Response {
    @Override
    public int getAnswersCount(String json_string) {
        try {
            JSONObject jsonObject = new JSONObject(json_string);
            JSONArray items = jsonObject.getJSONArray("items");
            JSONObject item = items.getJSONObject(0);
            return item.getInt("answer_count");

        } catch (JSONException e) {
            return -1;
        }
    }

    public StackResponse(String json) {
        this.data = new TopicState(
            OffsetDateTime.now(),
            this.getAnswersCount(json)
        );
    }
}
