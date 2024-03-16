package dto.response;

import database.DialogState;

public record DialogStateResponse(
    Long id,
    DialogState state
) {
}
