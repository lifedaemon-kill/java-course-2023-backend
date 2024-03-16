//package edu.java.bot.handler;
//
//import com.pengrad.telegrambot.model.Update;
//import database.DataBase;
//import database.DialogState;
//import edu.java.bot.command.AddURLCommand;
//import edu.java.bot.command.BaseCommand;
//import edu.java.bot.command.DeleteURLCommand;
//import edu.java.bot.command.HelpCommand;
//import edu.java.bot.command.ListCommand;
//import edu.java.bot.command.StartCommand;
//import edu.java.bot.command.UnknownCommand;
//import org.springframework.stereotype.Service;
//import static edu.java.bot.utility.CommandArchive.HELP;
//import static edu.java.bot.utility.CommandArchive.LIST;
//import static edu.java.bot.utility.CommandArchive.START;
//import static edu.java.bot.utility.CommandArchive.TRACK;
//import static edu.java.bot.utility.CommandArchive.UNTRACK;
//
//@Service
//public class WaitMesHandler extends Handler {
//
//    public static BaseCommand handle(Update update, DataBase dataBase) {
//        return switch (update.message().text()) {
//            case START:
//                yield new StartCommand(update);
//
//            case HELP:
//                yield new HelpCommand(update);
//
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
//
//            default:
//                yield new UnknownCommand(update);
//        };
//    }
//}
