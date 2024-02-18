package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.models.DataBase;
import edu.java.bot.models.DialogState;
import java.util.ArrayList;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class StartCommand extends Command {
    public StartCommand(Update update) {
        super(update);
    }

    private final String message =
        """
            Доброго времени суток, %s!
            Вы успешно зарегистрированы!"""
            .formatted(update.message().chat().firstName());

    @Override
    public SendMessage process() {
        DataBase.dialogState.put(id, DialogState.WaitMessage);
        DataBase.urlList.put(id, new ArrayList<>());

        log.info("Зарегистрирован пользователь %s".formatted(id));
        return new SendMessage(id, this.message);
    }
}
