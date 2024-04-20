package edu.java.api.service.jpa;

import api.exception.AlreadyRegisteredException;
import api.exception.NotFoundException;
import dto.request.ChangeDialogStateRequest;
import dto.response.DialogStateResponse;
import edu.java.api.service.interfaces.ChatService;
import edu.java.domain.jpa.JpaChatRepository;
import edu.java.entity.Chat;
import model.DialogState;
import org.springframework.http.ResponseEntity;

public class JpaChatService implements ChatService {
    protected JpaChatRepository chatRepository;

    public JpaChatService(JpaChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public boolean isChatExist(Long id) {
        return chatRepository.findById(id) != null;
    }

    @Override
    public ResponseEntity<Object> registerChat(Long id) {
        if (isChatExist(id)) {
            throw new AlreadyRegisteredException();
        }
        chatRepository.add(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Object> deleteChat(Long id) {
        if (!isChatExist(id)) {
            throw new NotFoundException();
        }
        chatRepository.remove(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public DialogStateResponse getDialogState(Long id) {
        if (!isChatExist(id)) {
            throw new NotFoundException();
        }
        Chat rowState = chatRepository.findById(id);

        DialogState state = DialogState.map(rowState.getState());
        return new DialogStateResponse(id, state);
    }

    @Override
    public DialogStateResponse changeDialogState(Long id, ChangeDialogStateRequest dialogStateRequest) {
        if (!isChatExist(id)) {
            throw new NotFoundException();
        }
        int state = dialogStateRequest.dialogState().ordinal();
        chatRepository.update(id, state);

        return new DialogStateResponse(id, dialogStateRequest.dialogState());
    }
}
