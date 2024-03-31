package edu.java.api;

import api.exception.AlreadyRegisteredException;
import api.exception.NotFoundException;
import database.DialogState;
import dto.request.AddLinkRequest;
import dto.request.ChangeDialogStateRequest;
import dto.response.DialogStateResponse;
import dto.response.LinkResponse;
import dto.response.ListLinksResponse;
import edu.java.domain.DataBaseController;
import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ScrapperApiService {
    private final DataBaseController db;

    ScrapperApiService(DataBaseController dataBaseController) {
        this.db = dataBaseController;
    }

    public ResponseEntity<Object> registerChat(Long id) {
        if (db.getDialogState(id) != null) {
            throw new AlreadyRegisteredException();
        }
        db.createUserDialogState(id);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Object> deleteChat(Long id) {
        if (db.getDialogState(id) != null) {
            throw new NotFoundException();
        }
        db.deleteChat(id);
        return ResponseEntity.ok().build();
    }

    public ListLinksResponse getLinks(Long id) {
        if (db.getDialogState(id) == null) {
            throw new NotFoundException();
        }

        var links = db.getLinkList(id);

        return new ListLinksResponse(links, links.size());
    }

    public LinkResponse addLinkTracking(Long id, AddLinkRequest request) {
        if (db.getDialogState(id) == null) {
            throw new NotFoundException();
        }
        db.addIdToLink(id, request.link());
        return new LinkResponse(id, request.link());
    }

    public LinkResponse deleteLinkTracking(Long id, URI link) {
        if (db.getDialogState(id) == null) {
            throw new NotFoundException();
        }
        db.deleteIdFromLink(id, link);
        return new LinkResponse(id, link);
    }

    public DialogStateResponse getDialogState(Long id) {
        DialogState state = db.getDialogState(id);
        if (state == null) {
            throw new NotFoundException();
        }
        return new DialogStateResponse(id, state);
    }

    public DialogStateResponse changeDialogState(Long id, ChangeDialogStateRequest dialogStateRequest) {
        if (db.getDialogState(id) == null) {
            throw new NotFoundException();
        }

        db.putDialogState(id, dialogStateRequest.dialogState());
        return new DialogStateResponse(id, dialogStateRequest.dialogState());
    }
}
