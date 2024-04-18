package edu.java.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Chat")
public class Chat {
    @Id
    @Column(name = "tg_chat_id", nullable = false)
    Long id;
    @Column(name = "state", nullable = false)
    Integer state;
    @Column(name = "last_update_at", nullable = false)
    OffsetDateTime lastUpdateAt;
}
