//package edu.java.bot.command;
//
//import com.pengrad.telegrambot.model.Update;
//import com.pengrad.telegrambot.request.SendMessage;
//import database.DataBase;
//import edu.java.bot.api.BotClientService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//
//public abstract class BaseCommand {
//    protected final Update update;
//    protected final Long id;
//    protected final String text;
//    @Autowired
//    protected BotClientService httpClient;
//
//    public abstract SendMessage processAndMessage();
//
//    public BaseCommand(Update update) {
//        this.update = update;
//        this.id = update.message().chat().id();
//        this.text = update.message().text();
//    }
//}
