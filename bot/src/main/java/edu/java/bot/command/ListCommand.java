package edu.java.bot.command;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import database.DataBase;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

public class ListCommand extends BaseCommand {
    public ListCommand(Update update) {
        super(update);
    }

    @Override
    public SendMessage process(DataBase dataBase) {
        List<URI> list = dataBase.getListLinks().get(id);

        if (list.isEmpty()) {
            return new SendMessage(id, "Список адресов пуст, вы можете добавить ссылки используя /track");
        } else {
            return new SendMessage(id, Arrays.toString(new List[] {list}));
        }
    }
}
