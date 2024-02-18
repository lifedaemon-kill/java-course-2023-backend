package edu.java.bot.controllers;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import edu.java.bot.commands.StartCommand;
import edu.java.bot.handlers.WaitActHandler;
import edu.java.bot.handlers.WaitMesHandler;
import edu.java.bot.models.DataBase;
import edu.java.bot.models.DialogState;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import static edu.java.bot.utilities.StringCommand.START;

@Log4j2
@Component
public class Controller {
    private Controller() {
    }

    public static void process(List<Update> updateList, TelegramBot bot) {
        for (Update update : updateList) {
            if (update.message() == null
                || update.message().text() == null
                || update.message().text().isEmpty()) {
                continue;
            }
            String text = update.message().text();
            Long id = update.message().chat().id();
            log.debug(
                text + " " + update.message().chat().firstName()
                + " " + update.message().chat().username()
                + " " + update.message().chat().id());
            //Первый запуск
            if (text.equals(START)
                || !DataBase.dialogState.containsKey(id)
                || !DataBase.urlList.containsKey(id)) {
                var command = new StartCommand(update);
                bot.execute(command.process());
                continue;
            }

            if (DataBase.dialogState.get(id) == DialogState.WaitMessage) {
                //Состояние ожидания сообщения (По умолчанию)
                var command = WaitMesHandler.handle(update);
                bot.execute(command.process());
            } else {
                //Особые состояния
                var command = WaitActHandler.handle(update);
                bot.execute(command.process());
            }
        }
    }
}
