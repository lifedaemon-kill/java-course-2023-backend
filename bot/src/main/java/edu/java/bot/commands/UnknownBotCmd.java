package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public class UnknownBotCmd extends BotCmd {
    public UnknownBotCmd(Update update) {
        super(update);
    }

    private static final String message = "Данная команда отсутствует, воспользуйтесь меню или командой /help";

    @Override
    public SendMessage process() {
        return new SendMessage(id, message);
    }
}
