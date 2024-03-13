package edu.java.api;

import dto.request.AddLinkRequest;
import dto.request.ChangeDialogStateRequest;
import dto.request.RemoveLinkRequest;
import dto.response.LinkResponse;
import dto.response.ListLinksResponse;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ScrapperService {
    private final DataBaseController db;

    ScrapperService(DataBaseController dataBaseController) {
        this.db = dataBaseController;
    }

    public ResponseEntity<Object> registerChat(Long id) {
        if (db.getDialogState(id) != null) {
            return ResponseEntity
                .status(HttpStatus.ALREADY_REPORTED)
                .contentType(MediaType.APPLICATION_JSON)
                .body("Chat already registered");
        } else {
            db.addDialogState(id);
            return ResponseEntity
                .status(HttpStatus.OK)
                .build();
        }
    }

    public ResponseEntity<Object> deleteChat(Long id) {
        if (db.getDialogState(id) != null) {
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body("Chat does not exist");
        } else {
            db.deleteChat(id);
            return ResponseEntity
                .status(HttpStatus.OK)
                .build();
        }
    }

    public ResponseEntity<Object> getLinks(Long id) {
        List<LinkResponse> list = new ArrayList<>();
        long count = 0L;
        for (Map.Entry<URI, List<Long>> entry : db.getLinkUsers().entrySet()) {
            if (entry.getValue().contains(id)) {
                list.add(new LinkResponse(count++, entry.getKey()));
            }
        }
        if (count == 0) {
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body("Links not found");
        }
        return ResponseEntity
            .status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(new ListLinksResponse(list, list.size()));
    }

    public ResponseEntity<Object> addLinkTracking(Long id, AddLinkRequest addLinkRequest) {
        if (db.getDialogState(id) == null) {
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body("Not found");
        }

        if (db.getLinkUsers().containsKey(addLinkRequest.link())) {
            db.getLinkUsers().get(addLinkRequest.link()).add(id);
        } else {
            db.getLinkUsers().put(addLinkRequest.link(), new ArrayList<>(List.of(id)));
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
   public ResponseEntity<Object> deleteLinkTracking(Long id, RemoveLinkRequest request){
        db.deleteIdFromLink(id, request);
        return ResponseEntity.status(HttpStatus.OK).build();
   }
    public ResponseEntity<Object> getDialogState(Long id) {
        if (db.getDialogState(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public ResponseEntity<Object> changeDialogState(Long id, ChangeDialogStateRequest dialogStateRequest){
        if (db.getDialogState(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        }
        db.getDialogState().put(id, dialogStateRequest.dialogState());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
