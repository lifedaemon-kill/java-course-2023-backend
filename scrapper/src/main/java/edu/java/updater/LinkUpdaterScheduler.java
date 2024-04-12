package edu.java.updater;

import edu.java.api.httpclient.ScrapperHttpClientService;
import edu.java.client.Client;
import edu.java.client.GitHubClient;
import edu.java.client.StackOverFlowClient;
import edu.java.domain.link.LinkRepository;
import edu.java.domain.linksChats.LinkChatRepository;
import edu.java.entity.Link;
import edu.java.entity.TopicState;
import java.time.OffsetDateTime;
import java.util.Collection;
import lombok.extern.log4j.Log4j2;
import model.LinkUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

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
    @Value("${app.scheduler.interval}")
    private int interval;

    @Scheduled(fixedDelayString = "${app.scheduler.interval}")
    void update() {
        log.info("Start link updater");
        Collection<Link> linkCollection =
            linkRepository.findByThreshold(OffsetDateTime.now().minusSeconds(30));
        Collection<Link> linkFullCollection = linkRepository.findAll();

        log.info("Found {} links", linkFullCollection.size());
        log.info("Found {} links with threshold", linkCollection.size());
        Client client;
        GitHubClient gitHubClient = new GitHubClient();
        StackOverFlowClient stackOverFlowClient = new StackOverFlowClient();

        for (Link link : linkCollection) {
            log.debug("Updating link {}", link.getUrl());
            String host = link.getUrl().getHost();

            TopicState newData = null;
            String identification;
            String rowData;

            switch (host) {
                case "github.com" -> {
                    client = gitHubClient;
                }
                case "stackoverflow.com", "ru.stackoverflow.com" -> {
                    client = stackOverFlowClient;
                }
                default -> {
                    log.error("This link don't match any available pattern in the database: {}", link.getUrl());
                    continue;
                }
            }

            identification = client.getRepositoryIDFromLink(link.getUrl().toString());
            if (identification == null) {
                log.error("Link {} don't match github pattern", link.getUrl());
                continue;
            }
            try {
                rowData = client.getRowData(identification);
            } catch (Exception e) {
                log.error("Server unable to obtain information about the link");
                continue;
            }
            try {
                newData = client.getPayloadData(rowData);
            } catch (Exception e) {
                log.error("Server unable to resolve json from api");
            }
            if (newData == null) {
                log.error("Something was wrong with getting data from json");
                continue;
            } else {
                log.info("\tAnswers = {}, Date = {}", newData.answersCount(), newData.responseTime());
            }
            if (newData.answersCount() > link.getAnswersCount()) {
                try {
                    linkRepository.update(link.getUrl(), newData.answersCount(), newData.responseTime());
                    log.info("Link {} updated", link.getUrl());

                    log.info("Start sending message to bot");
                    Collection<Long> chats;
                    try {
                        chats = relationRepository.findAllByUrlId(link.getId());
                    } catch (Exception e) {
                        log.error("Can't find link in db\n{}", e.getMessage());
                        continue;
                    }
                    LinkUpdate linkUpdate = new LinkUpdate(link.getId(), link.getUrl(), "ok", chats.stream().toList());
                    try {
                        WebClient.ResponseSpec response = botApiHttpClient.update(linkUpdate);
                    } catch (Exception e) {
                        log.error("Server unable to send update to bot");
                        continue;
                    }
                } catch (Exception e) {
                    log.error("Error while updating link {} in the database\n{}", link.getUrl(), e.getMessage());
                    continue;
                }
            } else {
                log.info("There is nothing new in the link {}", link.getUrl());
            }
        }
    }
}
