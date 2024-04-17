package edu.java.updater;

import edu.java.api.httpclient.ScrapperHttpClientService;
import edu.java.client.Client;
import edu.java.client.GitHubClient;
import edu.java.client.StackOverFlowClient;
import edu.java.domain.repository.LinkRepository;
import edu.java.domain.repository.LinkChatRepository;
import edu.java.entity.Link;
import edu.java.entity.TopicState;
import edu.java.response.GitResponse;
import edu.java.response.Response;
import edu.java.response.StackResponse;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import model.LinkUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@EnableScheduling
public class LinkUpdaterScheduler {
    @Autowired
    private GitHubClient gitHubClient;
    @Autowired
    private StackOverFlowClient stackOverFlowClient;
    @Autowired
    private ScrapperHttpClientService botApiHttpClient;
    @Autowired
    private LinkRepository linkRepository;
    @Autowired
    private LinkChatRepository relationRepository;
    @Value("${app.scheduler.force-check-delay}")
    private int forceCheckDelay;

    private Response response;
    private GitResponse gitResponse;
    private StackResponse stackResponse;

    @Scheduled(fixedDelayString = "${app.scheduler.interval}")
    void update() {
        log.info("Start link updater");
        Collection<Link> linkCollection =
            linkRepository.findByThreshold(OffsetDateTime.now().minusSeconds(forceCheckDelay));

        log.info("Found {} links with threshold", linkCollection.size());

        Client client;

        for (Link link : linkCollection) {
            log.info("Updating link {}", link.getUrl());
            String host = link.getUrl().getHost();

            TopicState newData = null;
            String identification;
            String rowData;

            switch (host) {
                case "github.com" -> {
                    client = gitHubClient;
                    response = gitResponse;
                }
                case "stackoverflow.com", "ru.stackoverflow.com" -> {
                    client = stackOverFlowClient;
                    response = stackResponse;
                }
                default -> {
                    log.error("This link don't match any available pattern in the database: {}", link.getUrl());
                    continue;
                }
            }

            identification = client.getRepositoryIDFromLink(link.getUrl().toString());
            //GET IDENTIFICATION
            if (identification == null) {
                log.error("Link {} don't match github pattern", link.getUrl());
                continue;
            }

            //ROW DATA
            try {
                rowData = client.getRowData(identification);
            } catch (Exception e) {
                log.error("Server unable to obtain information about the link");
                continue;
            }

            //GETTING PAYLOAD FROM ROW DATA
            try {
                newData = response.getPayload(rowData);
            } catch (Exception e) {
                log.error("Server unable to resolve json from api");
            }

            //check is new data was found
            if (newData == null) {
                log.error("Something was wrong with getting data from json");
                continue;
            } else {
                log.info("\tAnswers = {}, Date = {}", newData.answersCount(), newData.responseTime());
            }

            //check is something new there
            if (newData.answersCount() > link.getAnswersCount()) {
                try {
                    //update database
                    linkRepository.update(link.getUrl(), newData.answersCount(), newData.responseTime());
                    log.info("Link {} updated", link.getUrl());

                    log.info("Start sending message to bot");
                    //collect chat IDs
                    Collection<Long> chats;
                    try {
                        chats = relationRepository.findAllByUrlId(link.getId());
                    } catch (Exception e) {
                        log.error("Can't find link in db\n{}", e.getMessage());
                        continue;
                    }

                    //collect info

                    List<LinkUpdate> linkUpdate = new ArrayList<>();

                    for (var event : newData.events()) {
                        linkUpdate.add(new LinkUpdate(
                            link.getId(),
                            link.getUrl(),
                            "ok",
                            chats.stream().toList(),
                            event
                        ));
                    }

                    //send info
                    for (var update : linkUpdate) {
                        try {
                            botApiHttpClient.update(update);
                        } catch (Exception e) {
                            log.error("Server unable to send update to bot");
                        }
                    }
                } catch (Exception e) {
                    log.error("Error while updating link {} in the database\n{}", link.getUrl(), e.getMessage());
                }
            } else {
                log.info("There is nothing new in the link {}", link.getUrl());
            }
        }
    }
}
