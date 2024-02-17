package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public abstract class Command {
    protected Update update;
    protected Long id;
    protected String text;

    public abstract SendMessage process();

    public Command(Update update) {
        this.update = update;
        this.id = update.message().chat().id();
        this.text = update.message().text();
    }
}
