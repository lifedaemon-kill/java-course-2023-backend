/*
 * This file is generated by jOOQ.
 */
package edu.java.domain.jooq.entity.tables.records;


import edu.java.domain.jooq.entity.tables.Link;

import jakarta.validation.constraints.Size;

import java.beans.ConstructorProperties;
import java.time.OffsetDateTime;

import javax.annotation.processing.Generated;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;


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
public class LinkRecord extends UpdatableRecordImpl<LinkRecord> implements Record4<Long, String, Integer, OffsetDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>LINK.ID</code>.
     */
    public void setId(@Nullable Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>LINK.ID</code>.
     */
    @Nullable
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>LINK.URL</code>.
     */
    public void setUrl(@NotNull String value) {
        set(1, value);
    }

    /**
     * Getter for <code>LINK.URL</code>.
     */
    @jakarta.validation.constraints.NotNull
    @Size(max = 1000000000)
    @NotNull
    public String getUrl() {
        return (String) get(1);
    }

    /**
     * Setter for <code>LINK.ANSWERS_COUNT</code>.
     */
    public void setAnswersCount(@NotNull Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>LINK.ANSWERS_COUNT</code>.
     */
    @jakarta.validation.constraints.NotNull
    @NotNull
    public Integer getAnswersCount() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>LINK.LAST_UPDATE_AT</code>.
     */
    public void setLastUpdateAt(@NotNull OffsetDateTime value) {
        set(3, value);
    }

    /**
     * Getter for <code>LINK.LAST_UPDATE_AT</code>.
     */
    @jakarta.validation.constraints.NotNull
    @NotNull
    public OffsetDateTime getLastUpdateAt() {
        return (OffsetDateTime) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    @NotNull
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    @Override
    @NotNull
    public Row4<Long, String, Integer, OffsetDateTime> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    @NotNull
    public Row4<Long, String, Integer, OffsetDateTime> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    @NotNull
    public Field<Long> field1() {
        return Link.LINK.ID;
    }

    @Override
    @NotNull
    public Field<String> field2() {
        return Link.LINK.URL;
    }

    @Override
    @NotNull
    public Field<Integer> field3() {
        return Link.LINK.ANSWERS_COUNT;
    }

    @Override
    @NotNull
    public Field<OffsetDateTime> field4() {
        return Link.LINK.LAST_UPDATE_AT;
    }

    @Override
    @Nullable
    public Long component1() {
        return getId();
    }

    @Override
    @NotNull
    public String component2() {
        return getUrl();
    }

    @Override
    @NotNull
    public Integer component3() {
        return getAnswersCount();
    }

    @Override
    @NotNull
    public OffsetDateTime component4() {
        return getLastUpdateAt();
    }

    @Override
    @Nullable
    public Long value1() {
        return getId();
    }

    @Override
    @NotNull
    public String value2() {
        return getUrl();
    }

    @Override
    @NotNull
    public Integer value3() {
        return getAnswersCount();
    }

    @Override
    @NotNull
    public OffsetDateTime value4() {
        return getLastUpdateAt();
    }

    @Override
    @NotNull
    public LinkRecord value1(@Nullable Long value) {
        setId(value);
        return this;
    }

    @Override
    @NotNull
    public LinkRecord value2(@NotNull String value) {
        setUrl(value);
        return this;
    }

    @Override
    @NotNull
    public LinkRecord value3(@NotNull Integer value) {
        setAnswersCount(value);
        return this;
    }

    @Override
    @NotNull
    public LinkRecord value4(@NotNull OffsetDateTime value) {
        setLastUpdateAt(value);
        return this;
    }

    @Override
    @NotNull
    public LinkRecord values(@Nullable Long value1, @NotNull String value2, @NotNull Integer value3, @NotNull OffsetDateTime value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached LinkRecord
     */
    public LinkRecord() {
        super(Link.LINK);
    }

    /**
     * Create a detached, initialised LinkRecord
     */
    @ConstructorProperties({ "id", "url", "answersCount", "lastUpdateAt" })
    public LinkRecord(@Nullable Long id, @NotNull String url, @NotNull Integer answersCount, @NotNull OffsetDateTime lastUpdateAt) {
        super(Link.LINK);

        setId(id);
        setUrl(url);
        setAnswersCount(answersCount);
        setLastUpdateAt(lastUpdateAt);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised LinkRecord
     */
    public LinkRecord(edu.java.domain.jooq.entity.tables.pojos.Link value) {
        super(Link.LINK);

        if (value != null) {
            setId(value.getId());
            setUrl(value.getUrl());
            setAnswersCount(value.getAnswersCount());
            setLastUpdateAt(value.getLastUpdateAt());
            resetChangedOnNotNull();
        }
    }
}
