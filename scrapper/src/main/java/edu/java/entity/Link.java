package edu.java.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.time.OffsetDateTime;

@Entity
@Table(name = "link")
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "url")
    @NotNull
    private URI url;

    @Column(name = "answers_count")
    @NotNull
    private int answersCount;

    @Column(name = "created_at")
    @NotNull
    private OffsetDateTime createdAt;
}
