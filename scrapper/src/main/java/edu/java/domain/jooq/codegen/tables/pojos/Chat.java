/*
 * This file is generated by jOOQ.
 */
package edu.java.domain.jooq.codegen.tables.pojos;


import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.annotation.processing.Generated;

import org.jetbrains.annotations.NotNull;


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
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Chat implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long tgChatId;
    private Integer state;
    private OffsetDateTime lastUpdateAt;

    public Chat() {}

    public Chat(Chat value) {
        this.tgChatId = value.tgChatId;
        this.state = value.state;
        this.lastUpdateAt = value.lastUpdateAt;
    }

    @ConstructorProperties({ "tgChatId", "state", "lastUpdateAt" })
    public Chat(
        @NotNull Long tgChatId,
        @NotNull Integer state,
        @NotNull OffsetDateTime lastUpdateAt
    ) {
        this.tgChatId = tgChatId;
        this.state = state;
        this.lastUpdateAt = lastUpdateAt;
    }

    /**
     * Getter for <code>CHAT.TG_CHAT_ID</code>.
     */
    @jakarta.validation.constraints.NotNull
    @NotNull
    public Long getTgChatId() {
        return this.tgChatId;
    }

    /**
     * Setter for <code>CHAT.TG_CHAT_ID</code>.
     */
    public void setTgChatId(@NotNull Long tgChatId) {
        this.tgChatId = tgChatId;
    }

    /**
     * Getter for <code>CHAT.STATE</code>.
     */
    @jakarta.validation.constraints.NotNull
    @NotNull
    public Integer getState() {
        return this.state;
    }

    /**
     * Setter for <code>CHAT.STATE</code>.
     */
    public void setState(@NotNull Integer state) {
        this.state = state;
    }

    /**
     * Getter for <code>CHAT.LAST_UPDATE_AT</code>.
     */
    @jakarta.validation.constraints.NotNull
    @NotNull
    public OffsetDateTime getLastUpdateAt() {
        return this.lastUpdateAt;
    }

    /**
     * Setter for <code>CHAT.LAST_UPDATE_AT</code>.
     */
    public void setLastUpdateAt(@NotNull OffsetDateTime lastUpdateAt) {
        this.lastUpdateAt = lastUpdateAt;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Chat other = (Chat) obj;
        if (this.tgChatId == null) {
            if (other.tgChatId != null)
                return false;
        }
        else if (!this.tgChatId.equals(other.tgChatId))
            return false;
        if (this.state == null) {
            if (other.state != null)
                return false;
        }
        else if (!this.state.equals(other.state))
            return false;
        if (this.lastUpdateAt == null) {
            if (other.lastUpdateAt != null)
                return false;
        }
        else if (!this.lastUpdateAt.equals(other.lastUpdateAt))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.tgChatId == null) ? 0 : this.tgChatId.hashCode());
        result = prime * result + ((this.state == null) ? 0 : this.state.hashCode());
        result = prime * result + ((this.lastUpdateAt == null) ? 0 : this.lastUpdateAt.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Chat (");

        sb.append(tgChatId);
        sb.append(", ").append(state);
        sb.append(", ").append(lastUpdateAt);

        sb.append(")");
        return sb.toString();
    }
}