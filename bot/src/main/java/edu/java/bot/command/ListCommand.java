package edu.java.bot.command;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import dto.response.ListLinksResponse;
import edu.java.bot.api.BotClientService;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

public class ListCommand implements Command {

    private final TelegramBot bot;
    private final BotClientService service;

    public ListCommand(TelegramBot bot, BotClientService service) {
        this.bot = bot;
        this.service = service;
    }

    @Override
    public void execute(Update update) {
        var response = service.getLinks(update.message().chat().id());
        switch (response.getStatusCode()) {
            case OK:
                bot.execute(new SendMessage(update.message().chat().id(), prettyLinks(response.getBody())));
                break;
            case BAD_REQUEST:
                bot.execute(new SendMessage(update.message().chat().id(), "Произошла ошибка со стороны клиента"));
                break;
            case NOT_FOUND:
                bot.execute(new SendMessage(update.message().chat().id(), "Вы не зарегистрированы"));
            default:
                bot.execute(new SendMessage(update.message().chat().id(), "Непредвиденный код ответа от сервера"));
        }
    }

    public String prettyLinks(ListLinksResponse list) {
        if (list.size() == 0) {
            return "Ни одна ссылка не отслеживается";
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            builder.append("%d. %s\n".formatted(list.links().get(i).id() + 1, list.links().get(i).url()));
        }
        return builder.toString();
    }
}
