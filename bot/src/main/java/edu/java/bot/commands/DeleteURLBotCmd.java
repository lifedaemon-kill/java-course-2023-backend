package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.models.DialogState;
import edu.java.bot.models.UsersDB;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import static edu.java.bot.utilities.StringCommand.UNTRACK;

public class DeleteURLBotCmd extends BotCmd {
    public DeleteURLBotCmd(Update update) {
        super(update);
    }

    @Override
    public SendMessage process() {
        if (text.equals(UNTRACK)) {
            UsersDB.dialogState.put(id, DialogState.WaitURLToDelete);
            return new SendMessage(id, "Введите ссылку для удаления");
        }
        UsersDB.dialogState.put(id, DialogState.WaitMessage);

        List<URI> list = UsersDB.urlList.get(id);
        try {
            URI url = new URI(text);
            if (list.contains(url)) {
                list.remove(url);
                UsersDB.urlList.put(id, list);
                return new SendMessage(id, "Удаление успешно");
            } else {
                return new SendMessage(id, "Данной ссылки нет в списке отслеживаемых");
            }
        } catch (URISyntaxException e) {
            return new SendMessage(id, "Ссылка не валидна");
        }
    }
}
