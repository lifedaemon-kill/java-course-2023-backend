package edu.java.api.service.jdbc;

import api.exception.AlreadyRegisteredException;
import api.exception.NotFoundException;
import dto.request.ChangeDialogStateRequest;
import dto.response.DialogStateResponse;
import edu.java.api.service.interfaces.ChatService;
import edu.java.domain.interfaces.ChatRepository;
import edu.java.domain.interfaces.LinkChatRepository;
import edu.java.domain.interfaces.LinkRepository;
import edu.java.entity.Chat;
import model.DialogState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class JdbcChatService extends JdbcUtilityService implements ChatService {
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    protected LinkRepository linkRepository;
    @Autowired
    protected LinkChatRepository relationRepository;

    //Chat
    public ResponseEntity<Object> registerChat(Long id) {
        if (isChatExist(id)) {
            throw new AlreadyRegisteredException();
        }
        chatRepository.add(id);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Object> deleteChat(Long id) {
        if (!isChatExist(id)) {
            throw new NotFoundException();
        }
        chatRepository.remove(id);
        return ResponseEntity.ok().build();
    }

    public DialogStateResponse getDialogState(Long id) {
        if (!isChatExist(id)) {
            throw new NotFoundException();
        }
        Chat rowState = chatRepository.findById(id);

        DialogState state = DialogState.map(rowState.getState());
        return new DialogStateResponse(id, state);
    }

    public DialogStateResponse changeDialogState(Long id, ChangeDialogStateRequest dialogStateRequest) {
        if (!isChatExist(id)) {
            throw new NotFoundException();
        }
        int state = dialogStateRequest.dialogState().ordinal();
        chatRepository.update(id, state);

        return new DialogStateResponse(id, dialogStateRequest.dialogState());
    }
}
