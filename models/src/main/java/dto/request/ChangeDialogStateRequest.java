package dto.request;

import database.DialogState;

public record ChangeDialogStateRequest(
    DialogState dialogState
)  {
}
