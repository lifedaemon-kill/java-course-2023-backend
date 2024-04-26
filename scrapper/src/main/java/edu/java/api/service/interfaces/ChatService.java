package edu.java.api.service.interfaces;

import dto.request.ChangeDialogStateRequest;
import dto.response.DialogStateResponse;
import org.springframework.http.ResponseEntity;

public interface ChatService {
    ResponseEntity<Object> registerChat(Long id);

    ResponseEntity<Object> deleteChat(Long id);

    DialogStateResponse getDialogState(Long id);

    DialogStateResponse changeDialogState(Long id, ChangeDialogStateRequest dialogStateRequest);

}
