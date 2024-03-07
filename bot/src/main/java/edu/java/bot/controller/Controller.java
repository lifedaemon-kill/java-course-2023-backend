package edu.java.bot.controller;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import edu.java.bot.command.StartCommand;
import edu.java.bot.handler.WaitActHandler;
import edu.java.bot.handler.WaitMesHandler;
import edu.java.bot.model.DataBase;
import edu.java.bot.model.DialogState;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import static edu.java.bot.utility.StringCommand.START;

@Log4j2
@Component
public class Controller {
    private Controller() {
    }

    public static void process(List<Update> updateList, TelegramBot bot, DataBase dataBase) {
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
                || !dataBase.getDialogState().containsKey(id)
                || !dataBase.getUrlList().containsKey(id)) {
                var command = new StartCommand(update);
                bot.execute(command.process(dataBase));
                continue;
            }

            if (dataBase.getDialogState().get(id) == DialogState.WaitMessage) {
                //Состояние ожидания сообщения (По умолчанию)
                var command = WaitMesHandler.handle(update, dataBase);
                bot.execute(command.process(dataBase));
            } else {
                //Особые состояния
                var command = WaitActHandler.handle(update, dataBase);
                bot.execute(command.process(dataBase));
            }
        }
    }
}
