package edu.java.bot.command;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import model.DialogState;
import edu.java.bot.api.BotClientService;
import java.net.URI;
import lombok.extern.log4j.Log4j2;
import static edu.java.bot.utility.LinkParse.isLinkValidToUse;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@Log4j2
public class TrackCommand implements Command {

    private final TelegramBot bot;
    private final BotClientService service;

    public TrackCommand(TelegramBot bot, BotClientService clientService) {
        this.bot = bot;
        this.service = clientService;
    }

    @Override
    public void execute(Update update) {
        Long id = update.message().chat().id();
        String[] possibleLinks = update.message().text().split(" ");
        service.postDialogState(id, DialogState.WaitMessage);
        for (String link : possibleLinks) {
            String text = switch (isLinkValidToUse(link)) {
                case ACCEPT -> {
                    var response = service.addLink(id, URI.create(link));
                    yield switch (response.getStatusCode()) {
                        case OK -> "Ссылка\n%s\nУспешно добавлена".formatted(link);
                        case BAD_REQUEST -> "Неправильные параметры запроса";
                        case NOT_FOUND -> "Вы не зарегистрированы\n/start";
                        default -> "Непредвиденный код ответа от сервера";
                    };
                }
                case NOT_URL -> "Данное сообщение не является ссылкой:%s".formatted(link);
                case NOT_SUPPORTED_RESOURCE ->
                    "Данный ресурс не поддерживается\n%s\n/help для информации".formatted(link);
                case NO_CONNECTION -> "Не удалось сделать запрос по ссылке\n%s".formatted(link);
            };

            bot.execute(new SendMessage(id, text));
        }
    }
}
