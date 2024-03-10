package dto.response;

import java.net.URI;

public record LinkResponse(
    int id,
    URI url
) {
}
