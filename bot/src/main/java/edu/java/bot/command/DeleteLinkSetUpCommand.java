package edu.java.bot.command;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import model.DialogState;
import edu.java.bot.api.BotClientService;
import org.springframework.http.ResponseEntity;
import static org.springframework.http.HttpStatus.OK;

public class DeleteLinkSetUpCommand implements Command {
    private final TelegramBot bot;
    private final BotClientService service;

    public DeleteLinkSetUpCommand(TelegramBot bot, BotClientService service) {
        this.bot = bot;
        this.service = service;
    }

    @Override
    public void execute(Update update) {
        Long id = update.message().chat().id();

        ResponseEntity<Object> response = service.postDialogState(id, DialogState.WaitURLToDelete);
        if (response.getStatusCode().equals(OK)) {
            bot.execute(new SendMessage(id, "Введите ссылку для удаления"));
        } else {
            bot.execute(new SendMessage(id, "Произошла ошибка при подключении к базе данных"));
        }
    }
}
