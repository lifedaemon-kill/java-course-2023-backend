package edu.java.bot.api;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import model.LinkUpdate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BotServerService {
    private final TelegramBot bot;

    public BotServerService(TelegramBot bot) {
        this.bot = bot;
    }

    public ResponseEntity<Object> linkContentUpdate(LinkUpdate linkUpdate) {
        for (Long id : linkUpdate.tgChatIds()) {
            String text = switch (linkUpdate.event().eventType()) {
                case BASIC -> String.format("Обновление по ссылке\n%s", linkUpdate.url().toString());
                case GIT_BRANCH -> String.format("Появилась новая ветка по ссылке\n%s", linkUpdate.url().toString());
                case GIT_PUSH -> String.format("Появился новый push по ссылке\n%s", linkUpdate.url().toString());
                case GIT_COMMIT -> String.format("Появился новый commit по ссылке\n%s", linkUpdate.url().toString());
                case STACK_ANSWER -> String.format("Появился новый ответ по ссылке\n%s", linkUpdate.url().toString());
            };

            bot.execute(new SendMessage(
                id,
                text
            ));
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
