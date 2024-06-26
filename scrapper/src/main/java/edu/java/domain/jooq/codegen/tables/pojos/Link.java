/*
 * This file is generated by jOOQ.
 */

package edu.java.domain.jooq.codegen.tables.pojos;

import jakarta.validation.constraints.Size;
import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.time.OffsetDateTime;
import javax.annotation.processing.Generated;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "https://www.jooq.org",
        "jOOQ version:3.18.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class Link implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String url;
    private Integer answersCount;
    private OffsetDateTime lastUpdateAt;

    public Link() {
    }

    public Link(Link value) {
        this.id = value.id;
        this.url = value.url;
        this.answersCount = value.answersCount;
        this.lastUpdateAt = value.lastUpdateAt;
    }

    @ConstructorProperties({"id", "url", "answersCount", "lastUpdateAt"})
    public Link(
        @Nullable Long id,
        @NotNull String url,
        @NotNull Integer answersCount,
        @NotNull OffsetDateTime lastUpdateAt
    ) {
        this.id = id;
        this.url = url;
        this.answersCount = answersCount;
        this.lastUpdateAt = lastUpdateAt;
    }

    /**
     * Getter for <code>LINK.ID</code>.
     */
    @Nullable
    public Long getId() {
        return this.id;
    }

    /**
     * Setter for <code>LINK.ID</code>.
     */
    public void setId(@Nullable Long id) {
        this.id = id;
    }

    /**
     * Getter for <code>LINK.URL</code>.
     */
    @jakarta.validation.constraints.NotNull
    @Size(max = 1000000000)
    @NotNull
    public String getUrl() {
        return this.url;
    }

    /**
     * Setter for <code>LINK.URL</code>.
     */
    public void setUrl(@NotNull String url) {
        this.url = url;
    }

    /**
     * Getter for <code>LINK.ANSWERS_COUNT</code>.
     */
    @jakarta.validation.constraints.NotNull
    @NotNull
    public Integer getAnswersCount() {
        return this.answersCount;
    }

    /**
     * Setter for <code>LINK.ANSWERS_COUNT</code>.
     */
    public void setAnswersCount(@NotNull Integer answersCount) {
        this.answersCount = answersCount;
    }

    /**
     * Getter for <code>LINK.LAST_UPDATE_AT</code>.
     */
    @jakarta.validation.constraints.NotNull
    @NotNull
    public OffsetDateTime getLastUpdateAt() {
        return this.lastUpdateAt;
    }

    /**
     * Setter for <code>LINK.LAST_UPDATE_AT</code>.
     */
    public void setLastUpdateAt(@NotNull OffsetDateTime lastUpdateAt) {
        this.lastUpdateAt = lastUpdateAt;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Link other = (Link) obj;
        if (this.id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!this.id.equals(other.id)) {
            return false;
        }
        if (this.url == null) {
            if (other.url != null) {
                return false;
            }
        } else if (!this.url.equals(other.url)) {
            return false;
        }
        if (this.answersCount == null) {
            if (other.answersCount != null) {
                return false;
            }
        } else if (!this.answersCount.equals(other.answersCount)) {
            return false;
        }
        if (this.lastUpdateAt == null) {
            if (other.lastUpdateAt != null) {
                return false;
            }
        } else if (!this.lastUpdateAt.equals(other.lastUpdateAt)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.url == null) ? 0 : this.url.hashCode());
        result = prime * result + ((this.answersCount == null) ? 0 : this.answersCount.hashCode());
        result = prime * result + ((this.lastUpdateAt == null) ? 0 : this.lastUpdateAt.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Link (");

        sb.append(id);
        sb.append(", ").append(url);
        sb.append(", ").append(answersCount);
        sb.append(", ").append(lastUpdateAt);

        sb.append(")");
        return sb.toString();
    }
}
