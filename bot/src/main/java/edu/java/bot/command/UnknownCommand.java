package edu.java.bot.command;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import database.DataBase;
import database.DialogState;

public class UnknownCommand extends BaseCommand {
    public UnknownCommand(Update update) {
        super(update);
    }

    private static final String MESSAGE = "Данная команда отсутствует, воспользуйтесь меню или командой /help";

    @Override
    public SendMessage process(DataBase ignore) {
        return new SendMessage(id, MESSAGE);
    }
}
