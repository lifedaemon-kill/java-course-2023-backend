package edu.java.api.controller;

import api.exception.AlreadyRegisteredException;
import api.exception.NotFoundException;
import dto.request.AddLinkRequest;
import dto.request.ChangeDialogStateRequest;
import dto.response.DialogStateResponse;
import dto.response.LinkResponse;
import dto.response.ListLinksResponse;
import edu.java.api.service.jdbc.JdbcChatService;
import edu.java.api.service.jdbc.JdbcLinkService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScrapperApiController {
    private final JdbcChatService chatService;
    private final JdbcLinkService linkService;

    public ScrapperApiController(JdbcChatService chatService, JdbcLinkService linkService) {
        this.chatService = chatService;
        this.linkService = linkService;
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Chat was registered successfully"),
        @ApiResponse(responseCode = "208", description = "Chat already registered"),
        @ApiResponse(responseCode = "400", description = "Uncorrected request parameters")
    })
    @PostMapping("/tg-chat/{id}")
    public ResponseEntity<Object> registerChat(@PathVariable Long id)
        throws AlreadyRegisteredException {
        return chatService.registerChat(id);
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Chat was deleted successfully"),
        @ApiResponse(responseCode = "400", description = "Uncorrected request parameters"),
        @ApiResponse(responseCode = "404", description = "Chat doesn't exist")
    })
    @DeleteMapping("/tg-chat/{id}")
    public ResponseEntity<Object> deleteChat(@PathVariable Long id) throws NotFoundException {
        return chatService.deleteChat(id);
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Dialog state successfully responded"),
        @ApiResponse(responseCode = "400", description = "Invalid request parameters"),
        @ApiResponse(responseCode = "404", description = "The chat is not registered yet")
    })
    @GetMapping("/state")
    public DialogStateResponse getDialogState(@RequestHeader("Tg-Chat-Id") Long tgChatId) {
        return chatService.getDialogState(tgChatId);
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Dialog state successfully changed"),
        @ApiResponse(responseCode = "400", description = "Invalid request parameters"),
        @ApiResponse(responseCode = "404", description = "The chat is not registered yet")
    })
    @PostMapping("/state")
    public DialogStateResponse changeDialogState(
        @RequestHeader("Tg-Chat-Id") Long tgChatId,
        @RequestBody ChangeDialogStateRequest dialogStateRequest
    ) {
        return chatService.changeDialogState(tgChatId, dialogStateRequest);
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Links have been successfully received"),
        @ApiResponse(responseCode = "400", description = "Invalid request parameters"),
        @ApiResponse(responseCode = "404", description = "The chat is not registered yet")
    })
    @GetMapping("/links")
    public ListLinksResponse getLinks(@RequestHeader("Tg-Chat-Id") Long chatId) throws NotFoundException {
        return linkService.getLinks(chatId);
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Link successfully added"),
        @ApiResponse(responseCode = "400", description = "Invalid request parameters"),
        @ApiResponse(responseCode = "404", description = "The chat is not registered yet"),
        @ApiResponse(responseCode = "208", description = "Already exist")
    })
    @PostMapping("/links")
    public LinkResponse addLinkTracking(
        @RequestHeader("Tg-Chat-Id") Long tgChatId,
        @RequestBody AddLinkRequest addLinkRequest
    ) {
        return linkService.addLinkTracking(tgChatId, addLinkRequest);
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Link successfully deleted"),
        @ApiResponse(responseCode = "400", description = "Invalid request parameters"),
        @ApiResponse(responseCode = "404", description = "The chat is not registered yet")
    })
    @DeleteMapping("/links")
    public LinkResponse deleteLinkTracking(
        @RequestHeader("Tg-Chat-Id") Long tgChatId,
        @RequestHeader("Link") URI link
    ) {
        return linkService.deleteLinkTracking(tgChatId, link);
    }
}
