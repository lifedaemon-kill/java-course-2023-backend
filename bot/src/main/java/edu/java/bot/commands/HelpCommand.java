package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.models.DataBase;

public class HelpCommand extends Command {
    public HelpCommand(Update update) {
        super(update);
    }

    private static final String HELP_MESSAGE =
        """
            Что умеет этот бот?

            /help - Вы здесь

            /start - Регистрация пользователя (Обнуление ссылок и состояния)

            /track - Добавить ссылку для отслеживания
            Поддерживаются только сайты GitHub.com и StackOverflow.com

            /untrack - Удалить ссылку из отслеживания

            /list - Вывести все отслеживаемые ссылки
            """;

    @Override
    public SendMessage process(DataBase ignore) {
        return new SendMessage(id, HELP_MESSAGE);
    }
}
