package edu.java.entity;

import java.time.OffsetDateTime;

public record TopicState(
    OffsetDateTime responseTime,
    int answersCount
) {
}
