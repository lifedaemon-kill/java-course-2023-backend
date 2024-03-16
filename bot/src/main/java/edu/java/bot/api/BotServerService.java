package edu.java.bot.api;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import model.LinkUpdate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BotServerService {
    TelegramBot bot;

    BotServerService(TelegramBot bot) {
        this.bot = bot;
    }

    public ResponseEntity<Object> linkContentUpdate(LinkUpdate linkUpdate) {
        bot.execute(new SendMessage(linkUpdate.id(), "Новое сообщение по ссылке\n"));
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
