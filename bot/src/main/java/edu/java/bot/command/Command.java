package edu.java.bot.command;

import com.pengrad.telegrambot.model.Update;

public interface Command {
    void execute(Update update);
}
