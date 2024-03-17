package edu.java.bot.command;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.api.BotClientService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import static edu.java.bot.utility.CommandArchive.START;

@Log4j2
@Component
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
        ResponseEntity<Object> response = service.postRegisterChat(id);

        if (response.getStatusCode().is2xxSuccessful()) {
            String endMessage = "Вы были успешно зарегистрированы";
            bot.execute(new SendMessage(id, endMessage));
            log.info(START + " Registration was successful %s".formatted(id));
        } else {
            String errorMessage = "Вас не удалось зарегистрировать, попробуйте снова\n\\start";
            bot.execute(new SendMessage(id, errorMessage));
            log.info(START + " Registration was unsaccessful %s".formatted(id));
        }
    }
}
