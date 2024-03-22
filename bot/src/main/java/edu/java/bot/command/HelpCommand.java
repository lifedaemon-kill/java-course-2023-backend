package edu.java.bot.command;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.extern.log4j.Log4j2;
import static edu.java.bot.utility.CommandArchive.HELP;

@Log4j2
public class HelpCommand implements Command {
    private final TelegramBot bot;

    public HelpCommand(TelegramBot bot) {
        this.bot = bot;
    }

    private static final String HELP_MESSAGE =
        """
            Что умеет этот бот?

            /help - Вы здесь

            /start - Регистрация пользователя (Обнуление ссылок и состояния)

            /track - Добавить ссылку для отслеживания
            Поддерживаются только сайты github.com и stackoverflow.com

            /untrack - Удалить ссылку из отслеживания

            /list - Вывести все отслеживаемые ссылки
            """;

    @Override
    public void execute(Update update) {
        log.info(HELP + " %d".formatted(update.message().chat().id()));
        bot.execute(new SendMessage(update.message().chat().id(), HELP_MESSAGE));
    }
}
