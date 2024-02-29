package edu.java.bot.handler;

import com.pengrad.telegrambot.model.Update;
import edu.java.bot.command.AddURLCommand;
import edu.java.bot.command.BaseCommand;
import edu.java.bot.command.DeleteURLCommand;
import edu.java.bot.command.HelpCommand;
import edu.java.bot.command.ListCommand;
import edu.java.bot.command.StartCommand;
import edu.java.bot.command.UnknownCommand;
import edu.java.bot.model.DataBase;
import edu.java.bot.model.DialogState;
import org.springframework.stereotype.Service;
import static edu.java.bot.utility.StringCommand.HELP;
import static edu.java.bot.utility.StringCommand.LIST;
import static edu.java.bot.utility.StringCommand.START;
import static edu.java.bot.utility.StringCommand.TRACK;
import static edu.java.bot.utility.StringCommand.UNTRACK;

@Service
public class WaitMesHandler extends Handler {

    public static BaseCommand handle(Update update, DataBase dataBase) {
        return switch (update.message().text()) {
            case START:
                yield new StartCommand(update);

            case HELP:
                yield new HelpCommand(update);

            case TRACK:
                dataBase.getDialogState().put(
                    update.message().chat().id(),
                    DialogState.WaitURLToAdd
                );
                yield new AddURLCommand(update);

            case UNTRACK:
                dataBase.getDialogState().put(
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
