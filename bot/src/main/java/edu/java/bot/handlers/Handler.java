package edu.java.bot.handlers;

import com.pengrad.telegrambot.model.Update;
import edu.java.bot.commands.Command;

public abstract class Handler {
    public static Command handle(Update update) {
        return null;
    }
}
