package edu.java.api;

import api.exception.LinkAlreadyAddedException;
import api.exception.NotFoundException;
import database.DataBase;
import database.DialogState;
import dto.response.LinkResponse;
import java.net.URI;
import java.util.ArrayList;
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

    //Region DialogState
    public void createUserDialogState(Long id) {
        putDialogState(id, DialogState.WaitMessage);
    }

    public DialogState getDialogState(Long id) {
        return dialogState.get(id);
    }

    public void putDialogState(Long id, DialogState state) {
        dialogState.put(id, state);
    }
    //End DialogState

    //Region Links
    public void addIdToLink(Long id, URI link) {
        if (linkUsers.containsKey(link)) {
            if (linkUsers.get(link).contains(id)) {
                throw new LinkAlreadyAddedException();
            }
            linkUsers.get(link).add(id);
        }
        linkUsers.put(link, new ArrayList<>(List.of(id)));
    }

    public void deleteIdFromLink(Long id, URI link) {
        if (linkUsers.get(link) == null) {
            throw new NotFoundException();
        }
        linkUsers.get(link)
            .remove(id);
    }

    public List<LinkResponse> getLinkList(Long id) {
        return null;
    }
    //End Links

    //Region Chat
    public void deleteChat(Long id) {
        dialogState.remove(id);

        for (Map.Entry<URI, List<Long>> entry : linkUsers.entrySet()) {
            entry.getValue().removeIf(value -> value.equals(id));
            if (entry.getValue().isEmpty()) {
                linkUsers.remove(entry.getKey());
            }
        }
    }
    //End Chat
}
