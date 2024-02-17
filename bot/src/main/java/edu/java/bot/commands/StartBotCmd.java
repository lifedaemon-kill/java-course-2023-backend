package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.models.DialogState;
import edu.java.bot.models.UsersDB;
import java.util.ArrayList;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class StartBotCmd extends BotCmd {
    public StartBotCmd(Update update) {
        super(update);
    }

    private final String message =
        """
            Доброго времени суток, %s!
            Вы успешно зарегистрированы!"""
            .formatted(update.message().chat().firstName());

    @Override
    public SendMessage process() {
        UsersDB.dialogState.put(id, DialogState.WaitMessage);
        UsersDB.urlList.put(id, new ArrayList<>());

        log.info("Зарегистрирован пользователь %s".formatted(id));
        return new SendMessage(id, this.message);
    }
}
