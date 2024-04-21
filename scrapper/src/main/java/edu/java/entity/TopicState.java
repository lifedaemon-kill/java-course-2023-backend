package edu.java.entity;

import java.time.OffsetDateTime;
import java.util.List;
import model.UpdateInfo;

public record TopicState(
    OffsetDateTime responseTime,
    int answersCount,
    List<UpdateInfo> events
) {
}
