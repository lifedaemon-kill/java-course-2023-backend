package edu.java.api;

import dto.response.LinkResponse;
import dto.response.ListLinksResponse;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import jdk.jfr.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScrapperController {
    @Description("Register chat")
    @PostMapping("/tg-chat/{id}")
    public HttpStatus registerChat(@PathVariable String id) {
        //if db has no this id return 200(or 201)
        //if db has this id return 401
        //if wrong id return 400
        return HttpStatus.OK;
    }

    @Description("Delete chat")
    @DeleteMapping("/tg-chat/{id}")
    public HttpStatus deleteChat(@PathVariable String id) {
        //if db has this id return 200
        //if wrong id return 400
        //return 404 if chat doesnt exist
        return HttpStatus.OK;
    }

    @Description("Get all tracked links")
    @GetMapping("/links")
    public ResponseEntity<ListLinksResponse> getTrackedLinks(@RequestHeader("Tg-Chat-Id") Long tgChatId) {
        //from db get links for this id
        //return 400 if wrong
        //return 404 if chat doesnt exist
        List<LinkResponse> links = new ArrayList<>();
        links.add(new LinkResponse(0, URI.create("https://github.com/lifedaemon-kill/java-course-2023-backend")));

        return ResponseEntity.ok(new ListLinksResponse(links, links.size()));
    }

    @Description("Add link tracking")
    @PostMapping("/links")
    public HttpStatus addLinkTracking(@RequestHeader("Tg-Chat-Id") Long tgChatId) {
        //return 200
        //return 400 if wrong
        //return 404 if chat doesnt exist
        return HttpStatus.OK;
    }

    @Description("Delete link tracking")
    @DeleteMapping("/links")
    public HttpStatus deleteLinkTracking(@RequestHeader("Tg-Chat-Id") Long tgChatId) {
        //return 200
        //return 400 if wrong
        //return 404 if chat doesnt exist
        return HttpStatus.OK;
    }
}
