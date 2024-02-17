package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.models.DataBase;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

public class ListCommand extends Command {
    public ListCommand(Update update) {
        super(update);
    }

    @Override
    public SendMessage process() {
        List<URI> list = DataBase.urlList.get(id);

        if (list.isEmpty()) {
            return new SendMessage(id, "Список адресов пуст, вы можете добавить ссылки используя /track");
        } else {
            return new SendMessage(id, Arrays.toString(new List[] {list}));
        }
    }
}
