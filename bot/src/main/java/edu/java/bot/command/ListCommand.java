package edu.java.bot.command;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import dto.response.ListLinksResponse;
import edu.java.bot.api.BotClientService;

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
        switch (response.getStatusCodeValue()) {
            case 200:
                bot.execute(new SendMessage(update.message().chat().id(), prettyLinks(response.getBody())));
                break;
            case 400:
                bot.execute(new SendMessage(update.message().chat().id(), "Произошла ошибка со стороны клиента"));
                break;
            case 404:
                bot.execute(new SendMessage(update.message().chat().id(), "Вы не зарегистрированы"));
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
