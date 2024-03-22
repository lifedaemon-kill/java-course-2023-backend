package edu.java.bot.command;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.api.BotClientService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import static edu.java.bot.utility.CommandArchive.START;
import static org.springframework.http.HttpStatus.ALREADY_REPORTED;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@Log4j2
public class StartCommand implements Command {
    private final TelegramBot bot;
    private final BotClientService service;

    public StartCommand(TelegramBot bot, BotClientService service) {
        this.bot = bot;
        this.service = service;
    }

    @Override
    public void execute(Update update) {
        Long id = update.message().chat().id();

        log.info(START + " Start registration %d %s".formatted(id, update.message().chat().username()));

        String helloMessage = """
            Доброго времени суток
            Регистрирую вас в нашей системе
            """;
        bot.execute(new SendMessage(id, helloMessage));
        ResponseEntity<Object> response = service.registerChat(id);
        String message;
        switch (response.getStatusCode()) {
            case OK:
                message = "Вы были успешно зарегистрированы";
                log.info(START + " Registration was successful %s".formatted(id));
                break;
            case ALREADY_REPORTED:
                message = "Вы уже зарегистрированы";
                log.info(START + " Trying to register several times %s".formatted(id));
                break;
            case BAD_REQUEST:
                message = "Произошла ошибка со стороны клиента, попробуйте снова\n\\start";
                log.error(START + " Wrong parameters %s".formatted(id));
                break;
            default:
                message = "Произошла непредвиденная ошибка, попробуйте снова\n\\start";
                log.error(START + " Unusual behavior %s".formatted(id));
                break;
        }
        bot.execute(new SendMessage(id, message));
    }
}
