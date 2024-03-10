package edu.java.bot.command;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import database.DataBase;

public abstract class BaseCommand {
    protected Update update;
    protected Long id;
    protected String text;

    public abstract SendMessage process(DataBase dataBase);

    public BaseCommand(Update update) {
        this.update = update;
        this.id = update.message().chat().id();
        this.text = update.message().text();
    }
}
