package edu.java.bot.handler;

import com.pengrad.telegrambot.model.Update;
import edu.java.bot.command.AddURLCommand;
import edu.java.bot.command.BaseCommand;
import edu.java.bot.command.DeleteURLCommand;
import database.DataBase;
import database.DialogState;
import org.springframework.stereotype.Service;

@Service
public class WaitActHandler extends Handler {
    public static BaseCommand handle(Update update, DataBase dataBase) {
        return switch (dataBase.getDialogState().get(update.message().chat().id())) {
            case WaitMessage -> Handler.handle(update);
            case WaitURLToAdd -> new AddURLCommand(update);
            case WaitURLToDelete -> new DeleteURLCommand(update);
        };
    }
}
