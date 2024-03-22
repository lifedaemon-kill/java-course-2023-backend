package edu.java.bot.handler;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import edu.java.bot.api.BotClientService;
import edu.java.bot.command.DeleteLinkSetUpCommand;
import edu.java.bot.command.HelpCommand;
import edu.java.bot.command.ListCommand;
import edu.java.bot.command.TrackSetUpCommand;
import edu.java.bot.command.WrongCommand;
import static edu.java.bot.utility.CommandArchive.HELP;
import static edu.java.bot.utility.CommandArchive.LIST;
import static edu.java.bot.utility.CommandArchive.TRACK;
import static edu.java.bot.utility.CommandArchive.UNTRACK;

public class BasicCommandsHandler {
    private final HelpCommand help;
    private final WrongCommand wrong;
    private final TrackSetUpCommand track;
    private final DeleteLinkSetUpCommand deleteLink;
    private final ListCommand listLinks;

    public BasicCommandsHandler(TelegramBot bot, BotClientService service) {
        this.help = new HelpCommand(bot);
        this.wrong = new WrongCommand(bot);
        this.track = new TrackSetUpCommand(bot, service);
        this.deleteLink = new DeleteLinkSetUpCommand(bot, service);
        this.listLinks = new ListCommand(bot, service);
    }

    public void execute(Update update) {
        switch (update.message().text()) {
            case HELP:
                help.execute(update);
                break;
            case TRACK:
                track.execute(update);
                break;
            case UNTRACK:
                deleteLink.execute(update);
                break;
            case LIST:
                listLinks.execute(update);
                break;
            default:
                wrong.execute(update);
                break;
        }
    }
}

