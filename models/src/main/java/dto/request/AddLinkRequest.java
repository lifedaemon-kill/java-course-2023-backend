package dto.request;

import java.net.URI;

public record AddLinkRequest(
    URI link
) {
}
