package edu.java.bot.command;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.extern.log4j.Log4j2;
import static edu.java.bot.utility.CommandArchive.HELP;

@Log4j2
public class WrongCommand implements Command {
    private final TelegramBot bot;

    public WrongCommand(TelegramBot bot) {
        this.bot = bot;
    }

    private static final String MESSAGE = "Данная команда отсутствует, воспользуйтесь меню или командой /help";

    @Override
    public void execute(Update update) {
        log.info(HELP + " " + update.message().chat().id().toString());
        bot.execute(new SendMessage(update.message().chat().id(), MESSAGE));
    }
}
