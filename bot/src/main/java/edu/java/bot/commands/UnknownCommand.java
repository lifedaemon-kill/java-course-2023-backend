package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public class UnknownCommand extends Command {
    public UnknownCommand(Update update) {
        super(update);
    }

    private static final String MESSAGE = "Данная команда отсутствует, воспользуйтесь меню или командой /help";

    @Override
    public SendMessage process() {
        return new SendMessage(id, MESSAGE);
    }
}
