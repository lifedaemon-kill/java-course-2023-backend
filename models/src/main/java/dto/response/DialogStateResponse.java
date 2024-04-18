package dto.response;

import model.DialogState;

public record DialogStateResponse(
    Long id,
    DialogState state
) {
}
