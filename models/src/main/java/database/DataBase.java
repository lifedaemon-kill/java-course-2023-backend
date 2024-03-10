package database;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataBase {
    private HashMap<Long, DialogState> dialogState = new HashMap<>();
    private HashMap<Long, List<URI>> listLinks = new HashMap<>();

    public DataBase() {
    }
}
