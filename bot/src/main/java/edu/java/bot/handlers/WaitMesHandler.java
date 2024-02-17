package edu.java.bot.handlers;

import com.pengrad.telegrambot.model.Update;
import edu.java.bot.commands.AddURLBotCmd;
import edu.java.bot.commands.BotCmd;
import edu.java.bot.commands.DeleteURLBotCmd;
import edu.java.bot.commands.HelpBotCmd;
import edu.java.bot.commands.ListBotCmd;
import edu.java.bot.commands.StartBotCmd;
import edu.java.bot.commands.UnknownBotCmd;
import edu.java.bot.models.DialogState;
import edu.java.bot.models.UsersDB;
import static edu.java.bot.utilities.StringCommand.HELP;
import static edu.java.bot.utilities.StringCommand.LIST;
import static edu.java.bot.utilities.StringCommand.START;
import static edu.java.bot.utilities.StringCommand.TRACK;
import static edu.java.bot.utilities.StringCommand.UNTRACK;

public class WaitMesHandler extends Handler {

    public static BotCmd handle(Update update) {
        return switch (update.message().text()) {
            case START:
                yield new StartBotCmd(update);

            case HELP:
                yield new HelpBotCmd(update);

            case TRACK:
                UsersDB.dialogState.put(
                    update.message().chat().id(),
                    DialogState.WaitURLToAdd
                );
                yield new AddURLBotCmd(update);

            case UNTRACK:
                UsersDB.dialogState.put(
                    update.message().chat().id(),
                    DialogState.WaitURLToDelete
                );
                yield new DeleteURLBotCmd(update);

            case LIST:
                yield new ListBotCmd(update);

            default:
                yield new UnknownBotCmd(update);
        };
    }
}
