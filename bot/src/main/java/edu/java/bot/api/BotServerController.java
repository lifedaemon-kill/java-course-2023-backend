package edu.java.bot.api;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import model.LinkUpdate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bot")
public class BotServerController {
    BotServerService service;

    public BotServerController(BotServerService service) {
        this.service = service;
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Link processed successfully"),
        @ApiResponse(responseCode = "400", description = "Wrong request parameters")
    })
    @PostMapping("/updates")
    public ResponseEntity<Object> updateLink(@RequestBody LinkUpdate linkUpdate) {
        return service.linkContentUpdate(linkUpdate);
    }
}
