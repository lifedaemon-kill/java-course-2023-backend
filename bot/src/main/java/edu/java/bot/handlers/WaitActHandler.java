package edu.java.bot.handlers;

import com.pengrad.telegrambot.model.Update;
import edu.java.bot.commands.AddURLCommand;
import edu.java.bot.commands.Command;
import edu.java.bot.commands.DeleteURLCommand;
import edu.java.bot.models.DataBase;

public class WaitActHandler extends Handler {
    public static Command handle(Update update) {
        return switch (DataBase.dialogState.get(update.message().chat().id())) {
            case WaitMessage -> Handler.handle(update);
            case WaitURLToAdd -> new AddURLCommand(update);
            case WaitURLToDelete -> new DeleteURLCommand(update);
        };
    }
}
