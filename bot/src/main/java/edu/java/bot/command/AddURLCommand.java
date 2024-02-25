package edu.java.bot.command;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.model.DataBase;
import edu.java.bot.model.DialogState;
import edu.java.bot.utility.LinkParse;
import java.net.URI;
import lombok.extern.log4j.Log4j2;
import static edu.java.bot.utility.StringCommand.TRACK;

@Log4j2
public class AddURLCommand extends BaseCommand {
    public AddURLCommand(Update update) {
        super(update);
    }

    @Override
    public SendMessage process(DataBase dataBase) {
        if (text.equals(TRACK)) {
            return new SendMessage(id, "Введите ссылку");
        }
        SendMessage message = isPossibleToAdd(dataBase); //Производится проверка на Wait
        dataBase.getDialogState().put(id, DialogState.WaitMessage);

        if (message == null) {
            //если прошла валидация
            try {
                URI url = new URI(text);
                //Добавление ссылки в базу
                dataBase.getUrlList().get(id).add(url);
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

    @SuppressWarnings("ReturnCount")
    public SendMessage isPossibleToAdd(DataBase dataBase) {
        Long id = update.message().chat().id();
        DialogState state = dataBase.getDialogState().get(id);

        if (!dataBase.getDialogState().containsKey(id) || !dataBase.getUrlList().containsKey(id)) {
            return new SendMessage(id, "Пользователь не зарегистрирован, используйте /start");
        }

        if (state != DialogState.WaitURLToAdd) {
            return new SendMessage(id, "Что-то пошло не так");
        }
        if (!LinkParse.isURL(text)) {
            return new SendMessage(id, "Сообщение не является ссылкой");
        }
        if (!LinkParse.isURLSupports(text)) {
            return new SendMessage(id, "Данный ресурс не поддерживается");
        }
        if (!LinkParse.isResourceAvailable(text)) {
            return new SendMessage(id, "Не удалось подключиться к данному ресурсу");
        }
        return null;
    }
}
