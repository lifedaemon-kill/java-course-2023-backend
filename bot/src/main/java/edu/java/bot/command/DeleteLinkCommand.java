package edu.java.bot.command;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.api.BotClientService;
import java.net.URI;
import model.DialogState;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

public class DeleteLinkCommand implements Command {
    private final BotClientService service;
    private final TelegramBot bot;

    public DeleteLinkCommand(TelegramBot bot, BotClientService service) {
        this.bot = bot;
        this.service = service;
    }

    @Override
    public void execute(Update update) {
        Long id = update.message().chat().id();
        String[] possibleLinks = update.message().text().split(" ");
        service.postDialogState(id, DialogState.WaitMessage);

        for (String link : possibleLinks) {
            String text;
            try {
                var response = service.deleteLink(id, URI.create(link));
                text = switch (response.getStatusCode()) {
                    case OK -> "Ссылка\n%s\nУспешно удалена".formatted(link);
                    case BAD_REQUEST -> "Неправильные параметры запроса";
                    case NOT_FOUND -> "Этой ссылки нет в списке";
                    case INTERNAL_SERVER_ERROR -> "Сервер не работает в данный момент";
                    default -> "Непредвиденный код ответа от сервера";
                };
            } catch (Exception e) {
                text = "Этой ссылки нет в вашем списке";
            }

            bot.execute(new SendMessage(id, text));
        }
    }
}
