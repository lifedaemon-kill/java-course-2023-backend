package edu.java.entity;

import model.UpdateInfo;
import java.time.OffsetDateTime;
import java.util.List;

public record TopicState(
    OffsetDateTime responseTime,
    int answersCount,
    List<UpdateInfo> events
) {
}
