package edu.java.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Log4j2
@Service
public class StackOverflowService {

    @Autowired
    private WebClient stackOverflowWebClient;

    public void getCommentsForQuestion(String questionUrl) {
        // Обработка ответа и вывод комментариев на экран
        stackOverflowWebClient.get()
            .uri(uriBuilder -> uriBuilder.path("/questions")
                .queryParam("order", "desc")
                .queryParam("sort", "activity")
                .queryParam("site", "stackoverflow")
                .queryParam("filter", "withbody")
                .queryParam("url", questionUrl)
                .build())
            .retrieve()
            .bodyToMono(String.class)
            .subscribe(log::info);
    }
}
