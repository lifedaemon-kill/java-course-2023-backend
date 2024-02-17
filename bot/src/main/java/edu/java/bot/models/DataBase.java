package edu.java.bot.models;

import org.springframework.context.annotation.Description;
import java.net.URI;
import java.util.HashMap;
import java.util.List;

@Description("Имитация базы данных")
public class DataBase {
    public static HashMap<Long, DialogState> dialogState = new HashMap<>();
    public static HashMap<Long, List<URI>> urlList = new HashMap<>();

    private DataBase() {
    }
}
