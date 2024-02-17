package edu.java.bot.controllers;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import edu.java.bot.commands.StartBotCmd;
import edu.java.bot.handlers.WaitActHandle;
import edu.java.bot.handlers.WaitMesHandle;
import edu.java.bot.models.DialogState;
import edu.java.bot.models.UsersDB;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import static edu.java.bot.utilities.StringCommand.START;

@Log4j2
@Component
public class BotController {
    public static void process(List<Update> updateList, TelegramBot bot) {
        for (Update update : updateList) {
            String text = update.message().text();
            Long id = update.message().chat().id();
            log.info(text);
            //Первый запуск
            if (text.equals(START)
                || !UsersDB.dialogState.containsKey(id)
                || !UsersDB.urlList.containsKey(id)) {
                var command = new StartBotCmd(update);
                bot.execute(command.process());
                continue;
            }
            //Состояние ожидания сообщения (По умолчанию)
            if (UsersDB.dialogState.get(id) == DialogState.WaitMessage) {
                var command = WaitMesHandle.handle(update);
                bot.execute(command.process());
            }
            //Особые состояния
            else {
                var command = WaitActHandle.handle(update);
                bot.execute(command.process());
            }
        }
    }
}
