package edu.java.bot.api;


import model.LinkUpdate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bot")
public class BotController {
    @PostMapping("/updates")
    public String updateLink(LinkUpdate linkUpdate) {
        //if link not in db: return 201 and Created
        //if link in db: return 200 and Updated
        return "Link %s %s was updated".formatted(linkUpdate.id(), linkUpdate.url());
    }
}
