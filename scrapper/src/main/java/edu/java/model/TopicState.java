package edu.java.model;

import java.time.OffsetDateTime;

public record TopicState(
    OffsetDateTime responseTime,
    int answersCount
) {
}
