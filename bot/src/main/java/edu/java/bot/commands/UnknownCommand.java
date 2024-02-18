package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.models.DataBase;

public class UnknownCommand extends Command {
    public UnknownCommand(Update update) {
        super(update);
    }

    private static final String MESSAGE = "Данная команда отсутствует, воспользуйтесь меню или командой /help";

    @Override
    public SendMessage process(DataBase ignore) {
        return new SendMessage(id, MESSAGE);
    }
}
