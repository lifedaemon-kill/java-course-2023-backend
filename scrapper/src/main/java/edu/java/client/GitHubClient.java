package edu.java.client;

import java.util.Arrays;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class GitHubClient extends Client {
    public GitHubClient() {
        super("https://api.github.com");
    }

    public GitHubClient(String baseURL) {
        super(baseURL);
    }

    @Override
    public String getRowData(String repository) {
        String[] sub = repository.split("/");
        log.debug(repository);
        long count = Arrays.stream(sub).count();
        log.debug(count);

        if (count == 1L) {
            return webClient.get()
                .uri("/users/%s/events".formatted(repository))
                .retrieve()
                .bodyToMono(String.class)
                .block();
        } else {
            return webClient.get()
                .uri("/repos/%s/events".formatted(repository))
                .retrieve()
                .bodyToMono(String.class)
                .block();
        }
    }

    @Override
    public String getRepositoryIDFromLink(String link) {
        //https://github.com/me/myrepos/... -> /me/myrepos/...
        int start = link.indexOf("/", link.indexOf("//") + 2) + 1;
        if (start == 0) {
            return null;
        }

        int subend = link.indexOf("/", start);
        if (subend == -1) {
            subend = link.length();
        }

        int end = link.indexOf("/", subend + 1);
        if (end == -1) {
            end = link.length();
        }

        String sub = link.substring(start, end);
        log.debug("target id = {}", sub);

        if (!sub.isEmpty()) {
            return sub;
        } else {
            return null;
        }
    }
}
