package edu.java.bot.handlers;

import com.pengrad.telegrambot.model.Update;
import edu.java.bot.commands.AddURLBotCmd;
import edu.java.bot.commands.BotCmd;
import edu.java.bot.commands.DeleteURLBotCmd;
import edu.java.bot.models.UsersDB;

public class WaitActHandler extends Handler {
    public static BotCmd handle (Update update){
        return switch (UsersDB.dialogState.get(update.message().chat().id())){
            case WaitMessage -> Handler.handle(update);
            case WaitURLToAdd -> new AddURLBotCmd(update);
            case WaitURLToDelete -> new DeleteURLBotCmd(update);
        };
    }
}
