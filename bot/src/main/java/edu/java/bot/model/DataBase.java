package edu.java.bot.model;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Description;

@Description("Имитация базы данных")
@Getter
@Setter
public class DataBase {
    private HashMap<Long, DialogState> dialogState = new HashMap<>();
    private HashMap<Long, List<URI>> urlList = new HashMap<>();

    public DataBase() {
    }
}
