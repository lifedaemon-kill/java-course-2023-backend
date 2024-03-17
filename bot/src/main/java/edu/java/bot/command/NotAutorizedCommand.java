package edu.java.bot.command;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public class NotAutorizedCommand implements Command {
    private TelegramBot bot;

    public NotAutorizedCommand(TelegramBot bot) {
        this.bot = bot;
    }

    @Override
    public void execute(Update update) {
        bot.execute(new SendMessage(
            update.message().chat().id(),
            "Вы не авторизованы в системе, воспользуйтесь командой\n\\start"
        ));
    }
}
