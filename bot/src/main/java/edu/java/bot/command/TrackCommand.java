package edu.java.bot.command;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import database.DialogState;
import edu.java.bot.api.BotClientService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import static edu.java.bot.utility.CommandArchive.TRACK;

@Log4j2
@Component
public class TrackCommand implements Command {
    private final TelegramBot bot;
    private final BotClientService service;

    public TrackCommand(TelegramBot bot, BotClientService clientService) {
        this.bot = bot;
        this.service = clientService;
    }

    @Override
    public void execute(Update update) {
        log.info(TRACK + " " + update.message().chat().id() + " Changing dialog state to WaitAdd");
        ResponseEntity<Object> response =
            service.postDialogState(update.message().chat().id(), DialogState.WaitURLToAdd);

        if (response.getStatusCode().is2xxSuccessful()) {
            log.info(TRACK + " " + update.message().chat().id() + " Response successful");
            bot.execute(new SendMessage(update.message().chat().id(), "Введите ссылку"));

        } else {
            log.error(TRACK + " " + update.message().chat().id() + " Response unsuccessful code: " +
                      response.getStatusCode());
            bot.execute(new SendMessage(update.message().chat().id(), "Произошла ошибка, попробуйте снова\n" + TRACK));
        }
    }
}
