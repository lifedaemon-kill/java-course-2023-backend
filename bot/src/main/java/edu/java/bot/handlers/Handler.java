package edu.java.bot.handlers;

import com.pengrad.telegrambot.model.Update;
import edu.java.bot.commands.BotCmd;

public abstract class Handler {
    public static BotCmd handle(Update update) {
        return null;
    }
}
