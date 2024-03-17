package edu.java.bot.controller;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import java.util.List;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class UpdatesController {
    public UpdatesController() {
    }

    private static boolean isMessageValid(Update update) {
        return update.message() == null
               || update.message().text() == null
               || update.message().text().isEmpty();
    }

    public static void process(List<Update> updateList, TelegramBot bot) {
        for (Update update : updateList) {
            if (!isMessageValid(update)) {
                continue;
            }

            String text = update.message().text();
            Long id = update.message().chat().id();
            log.debug(id + " " +text);
            bot.execute(new SendMessage(update.message().chat().id(), "Привет!"));
/*
            if (text.equals(START)) {
                start.execute(update);
            }
            DialogState dialogState = clientService.getDialogState(id).state();
            if (dialogState == null) {
                log.debug("Пользователь %d не авторизован, попытка использования команд".formatted(id));
                notAuthorized.execute(update);
            }
            if (dialogState == DialogState.WaitMessage) {
                basicCommandsHandler.execute(update);
            } else if (dialogState == DialogState.WaitURLToAdd) {

            } else if (dialogState == DialogState.WaitURLToDelete) {

            } else {
                log.warn("Unknown dialogState %d".formatted(id));
            }

 */
        }
    }
}
