package edu.java.bot.api;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import model.LinkUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class BotServerService {
    private final TelegramBot bot;

    public BotServerService(TelegramBot bot) {
        this.bot = bot;
    }

    public ResponseEntity<Object> linkContentUpdate(LinkUpdate linkUpdate) {
        for (Long id : linkUpdate.tgChatIds()) {
            bot.execute(new SendMessage(id, String.format("Новый ответ по ссылке:\n%s", linkUpdate.url().toString())));
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
