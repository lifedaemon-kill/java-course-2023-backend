package dto.request;

import model.DialogState;

public record ChangeDialogStateRequest(
    DialogState dialogState
) {
}
