package edu.java.bot.handlers;

import com.pengrad.telegrambot.model.Update;
import edu.java.bot.commands.AddURLCommand;
import edu.java.bot.commands.Command;
import edu.java.bot.commands.DeleteURLCommand;
import edu.java.bot.commands.HelpCommand;
import edu.java.bot.commands.ListCommand;
import edu.java.bot.commands.StartCommand;
import edu.java.bot.commands.UnknownCommand;
import edu.java.bot.models.DialogState;
import edu.java.bot.models.DataBase;
import static edu.java.bot.utilities.StringCommand.HELP;
import static edu.java.bot.utilities.StringCommand.LIST;
import static edu.java.bot.utilities.StringCommand.START;
import static edu.java.bot.utilities.StringCommand.TRACK;
import static edu.java.bot.utilities.StringCommand.UNTRACK;

public class WaitMesHandler extends Handler {

    public static Command handle(Update update) {
        return switch (update.message().text()) {
            case START:
                yield new StartCommand(update);

            case HELP:
                yield new HelpCommand(update);

            case TRACK:
                DataBase.dialogState.put(
                    update.message().chat().id(),
                    DialogState.WaitURLToAdd
                );
                yield new AddURLCommand(update);

            case UNTRACK:
                DataBase.dialogState.put(
                    update.message().chat().id(),
                    DialogState.WaitURLToDelete
                );
                yield new DeleteURLCommand(update);

            case LIST:
                yield new ListCommand(update);

            default:
                yield new UnknownCommand(update);
        };
    }
}
