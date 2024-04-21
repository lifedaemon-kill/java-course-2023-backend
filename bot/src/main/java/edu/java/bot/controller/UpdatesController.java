package edu.java.bot.controller;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import dto.response.DialogStateResponse;
import edu.java.bot.httpclient.BotClientService;
import edu.java.bot.command.DeleteLinkCommand;
import edu.java.bot.command.NotAutorizedCommand;
import edu.java.bot.command.StartCommand;
import edu.java.bot.command.TrackCommand;
import edu.java.bot.handler.BasicCommandsHandler;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import model.DialogState;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static edu.java.bot.utility.CommandArchive.START;

@Log4j2
public class UpdatesController {
    private UpdatesController() {
    }

    private static boolean isMessageNotValid(Update update) {
        return update.message() == null
               || update.message().text() == null
               || update.message().text().isEmpty();
    }

    public static void process(List<Update> updateList, TelegramBot bot, BotClientService service) {
        for (Update update : updateList) {
            if (isMessageNotValid(update)) {
                bot.execute(new SendMessage(update.message().chat().id(), "Слов нет"));
                continue;
            }

            String text = update.message().text();
            Long id = update.message().chat().id();
            log.info(id + " " + text);

            if (text.equals(START)) {
                StartCommand start = new StartCommand(bot, service);
                start.execute(update);
                continue;
            }

            ResponseEntity<DialogStateResponse> stateResponse = service.getDialogState(id);

            if (stateResponse.getStatusCode() != HttpStatus.OK) {
                log.debug("Пользователь %d не авторизован, попытка использования команд".formatted(id));
                NotAutorizedCommand notAutorized = new NotAutorizedCommand(bot);
                notAutorized.execute(update);
                continue;
            }

            DialogState dialogState = stateResponse.getBody().state();
            if (dialogState == DialogState.WaitMessage) {
                BasicCommandsHandler basicCommandsHandler = new BasicCommandsHandler(bot, service);
                basicCommandsHandler.execute(update);
            } else if (dialogState == DialogState.WaitURLToAdd) {
                TrackCommand addLink = new TrackCommand(bot, service);
                addLink.execute(update);
            } else if (dialogState == DialogState.WaitURLToDelete) {
                DeleteLinkCommand delete = new DeleteLinkCommand(bot, service);
                delete.execute(update);
            } else {
                log.warn("Unknown dialogState %d".formatted(id));
            }
        }
    }
}
