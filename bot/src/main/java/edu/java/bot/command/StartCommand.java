package edu.java.bot.command;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import database.DataBase;
import database.DialogState;
import java.util.ArrayList;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class StartCommand extends BaseCommand {
    public StartCommand(Update update) {
        super(update);
    }

    private final String message =
        """
            Доброго времени суток, %s!
            Вы успешно зарегистрированы!"""
            .formatted(update.message().chat().firstName());

    @Override
    public SendMessage process(DataBase dataBase) {
        dataBase.getDialogState().put(id, DialogState.WaitMessage);
        dataBase.getListLinks().put(id, new ArrayList<>());

        log.info("Зарегистрирован пользователь %s".formatted(id));
        return new SendMessage(id, this.message);
    }
}
