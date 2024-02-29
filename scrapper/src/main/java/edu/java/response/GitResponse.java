package edu.java.response;

import java.time.OffsetDateTime;

public record GitResponse(
    String data,
    OffsetDateTime time
) {
}
