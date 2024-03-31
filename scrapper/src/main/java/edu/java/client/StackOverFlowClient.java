package edu.java.client;

import edu.java.entity.TopicState;
import edu.java.response.StackResponse;
import org.springframework.stereotype.Component;

@Component
public class StackOverFlowClient extends Client {
    public StackOverFlowClient() {
        super("https://api.stackexchange.com/2.3/questions/");
    }

    public StackOverFlowClient(String baseURL) {
        super(baseURL);
    }

    @Override
    public String getRepository(String id) {
        return webClient.get()
            .uri("%s?order=desc&sort=activity&site=stackoverflow".formatted(id))
            .retrieve()
            .bodyToMono(String.class)
            .block();
    }

    @Override
    public TopicState getPayload(String json) {
        StackResponse response = new StackResponse(json);
        return response.getData();
    }
}
