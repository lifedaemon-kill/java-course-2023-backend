package edu.java.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.net.URI;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Link")
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "url", nullable = false)
    private URI url;

    @Column(name = "answers_count", nullable = false)
    private int answersCount;

    @Column(name = "last_update_at", nullable = false)
    private OffsetDateTime lastUpdateAt;
}
