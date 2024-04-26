/*
 * This file is generated by jOOQ.
 */

package edu.java.domain.jooq.codegen.tables.pojos;

import java.beans.ConstructorProperties;
import java.io.Serializable;
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
public class Linkschats implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long urlId;
    private Long tgChatId;

    public Linkschats() {
    }

    public Linkschats(Linkschats value) {
        this.id = value.id;
        this.urlId = value.urlId;
        this.tgChatId = value.tgChatId;
    }

    @ConstructorProperties({"id", "urlId", "tgChatId"})
    public Linkschats(
        @Nullable Long id,
        @NotNull Long urlId,
        @NotNull Long tgChatId
    ) {
        this.id = id;
        this.urlId = urlId;
        this.tgChatId = tgChatId;
    }

    /**
     * Getter for <code>LINKSCHATS.ID</code>.
     */
    @Nullable
    public Long getId() {
        return this.id;
    }

    /**
     * Setter for <code>LINKSCHATS.ID</code>.
     */
    public void setId(@Nullable Long id) {
        this.id = id;
    }

    /**
     * Getter for <code>LINKSCHATS.URL_ID</code>.
     */
    @jakarta.validation.constraints.NotNull
    @NotNull
    public Long getUrlId() {
        return this.urlId;
    }

    /**
     * Setter for <code>LINKSCHATS.URL_ID</code>.
     */
    public void setUrlId(@NotNull Long urlId) {
        this.urlId = urlId;
    }

    /**
     * Getter for <code>LINKSCHATS.TG_CHAT_ID</code>.
     */
    @jakarta.validation.constraints.NotNull
    @NotNull
    public Long getTgChatId() {
        return this.tgChatId;
    }

    /**
     * Setter for <code>LINKSCHATS.TG_CHAT_ID</code>.
     */
    public void setTgChatId(@NotNull Long tgChatId) {
        this.tgChatId = tgChatId;
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
        final Linkschats other = (Linkschats) obj;
        if (this.id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!this.id.equals(other.id)) {
            return false;
        }
        if (this.urlId == null) {
            if (other.urlId != null) {
                return false;
            }
        } else if (!this.urlId.equals(other.urlId)) {
            return false;
        }
        if (this.tgChatId == null) {
            if (other.tgChatId != null) {
                return false;
            }
        } else if (!this.tgChatId.equals(other.tgChatId)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.urlId == null) ? 0 : this.urlId.hashCode());
        result = prime * result + ((this.tgChatId == null) ? 0 : this.tgChatId.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Linkschats (");

        sb.append(id);
        sb.append(", ").append(urlId);
        sb.append(", ").append(tgChatId);

        sb.append(")");
        return sb.toString();
    }
}
