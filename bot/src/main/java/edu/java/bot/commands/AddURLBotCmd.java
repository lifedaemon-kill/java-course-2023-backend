package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.models.DialogState;
import edu.java.bot.models.UsersDB;
import edu.java.bot.utilities.LinkParse;
import java.net.URI;
import lombok.extern.log4j.Log4j2;
import static edu.java.bot.utilities.StringCommand.TRACK;

@Log4j2
public class AddURLBotCmd extends BotCmd {
    public AddURLBotCmd(Update update) {
        super(update);
    }

    @Override
    public SendMessage process() {
        if (text.equals(TRACK)) {
            return new SendMessage(id, "Введите ссылку");
        }
        SendMessage message = isPossibleToAdd(); //Производится проверка на Wait
        UsersDB.dialogState.put(id, DialogState.WaitMessage);

        if (message == null) {
            //если прошла валидация
            try {
                URI url = new URI(text);
                //Добавление ссылки в базу
                UsersDB.urlList.get(id).add(url);
            } catch (Exception e) {
                log.error("Непредвиденная ошибка в Add process");
                log.error(e.getMessage());
                log.error(e.getStackTrace());
            }
            return new SendMessage(id, "Ссылка была успешно добавлена");
        } else {
            log.warn("Ссылка не прошла валидацию в Add process");
            log.warn(text);
            return message;
        }
    }

    public SendMessage isPossibleToAdd() {
        Long id = update.message().chat().id();
        DialogState state = UsersDB.dialogState.get(id);

        if (!UsersDB.dialogState.containsKey(id) || !UsersDB.urlList.containsKey(id)) {
            return new SendMessage(id, "Пользователь не зарегистрирован, используйте /start");
        }

        if (state != DialogState.WaitURLToAdd) {
            return new SendMessage(id, "Что-то пошло не так");
        }
        if (!LinkParse.isURL(update.message().text())) {
            return new SendMessage(id, "Сообщение не является ссылкой");
        }
        if (!LinkParse.isURLSupports(update.message().text())) {
            return new SendMessage(id, "Данный ресурс не поддерживается");
        }
        return null;
    }
}
