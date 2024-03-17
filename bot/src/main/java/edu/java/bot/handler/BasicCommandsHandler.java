package edu.java.bot.handler;

import com.pengrad.telegrambot.model.Update;
import edu.java.bot.command.HelpCommand;
import edu.java.bot.command.TrackCommand;
import edu.java.bot.command.WrongCommand;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import static edu.java.bot.utility.CommandArchive.HELP;
import static edu.java.bot.utility.CommandArchive.TRACK;

@Component
public class BasicCommandsHandler implements Handler {
    private final HelpCommand help;
    private final WrongCommand wrong;
    private final TrackCommand track;

    public BasicCommandsHandler(HelpCommand help, WrongCommand wrong, TrackCommand track) {
        this.help = help;
        this.wrong = wrong;
        this.track = track;
    }

    public void execute(Update update) {
        switch (update.message().text()) {
            case HELP:
                help.execute(update);
                break;
            case TRACK:
                track.execute(update);
                break;
            default:
                wrong.execute(update);
                break;
        }
    }
}
//            case TRACK:
//                dataBase.getDialogState().put(
//                    update.message().chat().id(),
//                    DialogState.WaitURLToAdd
//                );
//                yield new AddURLCommand(update);
//
//            case UNTRACK:
//                dataBase.getDialogState().put(
//                    update.message().chat().id(),
//                    DialogState.WaitURLToDelete
//                );
//                yield new DeleteURLCommand(update);
//
//            case LIST:
//                yield new ListCommand(update);

