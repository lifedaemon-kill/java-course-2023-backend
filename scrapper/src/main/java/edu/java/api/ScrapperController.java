package edu.java.api;

import api.exception.AlreadyRegisteredException;
import api.exception.NotFoundException;
import dto.request.AddLinkRequest;
import dto.request.ChangeDialogStateRequest;
import dto.request.RemoveLinkRequest;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScrapperController {
    private final ScrapperService service;

    public ScrapperController(ScrapperService service) {
        this.service = service;
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Chat was registered successfully"),
        @ApiResponse(responseCode = "208", description = "Chat already registered"),
        @ApiResponse(responseCode = "400", description = "Uncorrected request parameters")
    })
    @PostMapping("/tg-chat/{id}")
    public ResponseEntity<Object> registerChat(@PathVariable Long id)
        throws AlreadyRegisteredException {
        return service.registerChat(id);
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Chat was deleted successfully"),
        @ApiResponse(responseCode = "400", description = "Uncorrected request parameters"),
        @ApiResponse(responseCode = "404", description = "Chat doesn't exist")
    })
    @DeleteMapping("/tg-chat/{id}")
    public ResponseEntity<Object> deleteChat(@PathVariable Long id) throws NotFoundException {
        return service.deleteChat(id);
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Links have been successfully received"),
        @ApiResponse(responseCode = "400", description = "Invalid request parameters"),
        @ApiResponse(responseCode = "404", description = "The chat is not registered yet")
    })
    @GetMapping("/links")
    public ResponseEntity<Object> getLinks(@RequestHeader("Tg-Chat-id") Long chatId) throws NotFoundException {
        return service.getLinks(chatId);
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Link successfully added"),
        @ApiResponse(responseCode = "400", description = "Invalid request parameters"),
        @ApiResponse(responseCode = "404", description = "The chat is not registered yet")
    })
    @PostMapping("/links")
    public ResponseEntity<Object> addLinkTracking(
        @RequestHeader("Tg-Chat-Id") Long tgChatId,
        @RequestBody AddLinkRequest addLinkRequest
    ) {
        return service.addLinkTracking(tgChatId, addLinkRequest);
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Link successfully deleted"),
        @ApiResponse(responseCode = "400", description = "Invalid request parameters"),
        @ApiResponse(responseCode = "404", description = "The chat is not registered yet")
    })
    @DeleteMapping("/links")
    public ResponseEntity<Object> deleteLinkTracking(
        @RequestHeader("Tg-Chat-Id") Long tgChatId,
        @RequestBody RemoveLinkRequest request
    ) {
        return service.deleteLinkTracking(tgChatId, request);
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Dialog state successfully responded"),
        @ApiResponse(responseCode = "400", description = "Invalid request parameters"),
        @ApiResponse(responseCode = "404", description = "The chat is not registered yet")
    })
    @GetMapping("/state")
    public ResponseEntity<Object> getDialogState(@RequestHeader("Tg-Chat-Id") Long tgChatId) {
        return service.getDialogState(tgChatId);
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Dialog state successfully changed"),
        @ApiResponse(responseCode = "400", description = "Invalid request parameters"),
        @ApiResponse(responseCode = "404", description = "The chat is not registered yet")
    })
    @PostMapping("/state")
    public ResponseEntity<Object> changeDialogState(
        @RequestHeader("Tg-Chat-Id") Long tgChatId,
        @RequestBody ChangeDialogStateRequest dialogStateRequest
    ) {
        return service.changeDialogState(tgChatId, dialogStateRequest);
    }
}
