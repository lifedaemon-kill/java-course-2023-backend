package edu.java.bot.command;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import database.DataBase;
import database.DialogState;
import static edu.java.bot.utility.StringCommand.UNTRACK;

public class DeleteURLCommand extends BaseCommand {
    public DeleteURLCommand(Update update) {
        super(update);
    }

    @Override
    public SendMessage process(DataBase dataBase) {
        if (text.equals(UNTRACK)) {
            dataBase.getDialogState().put(id, DialogState.WaitURLToDelete);
            return new SendMessage(id, "Введите ссылку для удаления");
        }
        dataBase.getDialogState().put(id, DialogState.WaitMessage);

        List<URI> list = dataBase.getListLinks().get(id);
        try {
            URI url = new URI(text);
            if (list.contains(url)) {
                list.remove(url);
                dataBase.getListLinks().put(id, list);
                return new SendMessage(id, "Удаление успешно");
            } else {
                return new SendMessage(id, "Данной ссылки нет в списке отслеживаемых");
            }
        } catch (URISyntaxException e) {
            return new SendMessage(id, "Ссылка не валидна");
        }
    }
}
