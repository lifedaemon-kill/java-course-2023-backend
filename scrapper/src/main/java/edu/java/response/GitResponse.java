package edu.java.response;

import java.time.OffsetDateTime;

public record GitResponse(
    String comment,
    OffsetDateTime time
) {
}
