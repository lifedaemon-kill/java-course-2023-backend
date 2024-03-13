package edu.java.api;

import api.exception.NotFoundException;
import database.DataBase;
import database.DialogState;
import dto.request.RemoveLinkRequest;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import org.springframework.stereotype.Controller;

@Getter
@Controller
public class DataBaseController {
    private final HashMap<Long, DialogState> dialogState;
    private final HashMap<URI, List<Long>> linkUsers;

    public DataBaseController(DataBase dataBase) {
        this.dialogState = dataBase.getDialogState();
        this.linkUsers = dataBase.getLinkUsers();
    }

    public void addDialogState(Long id) {
        dialogState.put(id, DialogState.WaitMessage);
    }

    public DialogState getDialogState(Long id) {
        return dialogState.get(id);
    }

    public void deleteChat(Long id) {
        dialogState.remove(id);

        for (Map.Entry<URI, List<Long>> entry : linkUsers.entrySet()) {
            entry.getValue().removeIf(value -> value.equals(id));
            if (entry.getValue().isEmpty()) {
                linkUsers.remove(entry.getKey());
            }
        }
    }

    public void deleteIdFromLink(Long id, RemoveLinkRequest request) {
        if (linkUsers.get(request.link()) == null) {
            throw new NotFoundException();
        } else {
            linkUsers.get(request.link()).remove(id);
        }
    }
}
