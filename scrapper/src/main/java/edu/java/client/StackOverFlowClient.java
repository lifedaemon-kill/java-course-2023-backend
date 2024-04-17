package edu.java.client;

import edu.java.entity.TopicState;
import edu.java.response.StackResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    public String getRowData(String id) {
        return webClient.get()
            .uri("%s?order=desc&sort=activity&site=stackoverflow".formatted(id))
            .retrieve()
            .bodyToMono(String.class)
            .block();
    }

    @Override
    public String getRepositoryIDFromLink(String link) {
        Pattern pattern = Pattern.compile(".*/(\\d+).*");
        Matcher matcher = pattern.matcher(link);
        if (matcher.matches()) {
            return matcher.group(1);
        } else {
            return null;
        }
    }
}
