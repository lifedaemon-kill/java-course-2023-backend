//package edu.java.bot.controller;
//
//import com.pengrad.telegrambot.model.Update;
//import java.util.List;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import database.DialogState;
//import dto.response.DialogStateResponse;
//import edu.java.bot.api.BotClientService;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Controller;
//import reactor.core.publisher.Mono;
//
//@Log4j2
//@Controller
//public class UpdatesController {
//    private final static ExecutorService executor = Executors.newFixedThreadPool(10);
//    static BotClientService httpService;
//    public UpdatesController(BotClientService httpService) {
//        this.httpService = httpService;
//    }
//
//    public static void process(List<Update> updateList) {
//        for (Update update : updateList) {
//            executor.execute(() -> {
//                if (update.message() == null
//                    || update.message().text() == null
//                    || update.message().text().isEmpty()) {
//                    return;
//                }
//                String text = update.message().text();
//                Long id = update.message().chat().id();
//
//                Mono<DialogStateResponse> dialogState = httpService.getDialogState(id);
//
//            });
//            continue;
//            /*
//            //Первый запуск
//            if (text.equals(START)
//                || !dataBase.getDialogState().containsKey(id)
//                || !dataBase.getListLinks().containsKey(id)) {
//                var command = new StartCommand(update);
//                bot.execute(command.process(dataBase));
//                continue;
//            }
//
//            if (dataBase.getDialogState().get(id) == DialogState.WaitMessage) {
//                //Состояние ожидания сообщения (По умолчанию)
//                var command = WaitMesHandler.handle(update, dataBase);
//                bot.execute(command.process(dataBase));
//            } else {
//                //Особые состояния
//                var command = WaitActHandler.handle(update, dataBase);
//                bot.execute(command.process(dataBase));
//            }
//
//             */
//        }
//    }
//}
