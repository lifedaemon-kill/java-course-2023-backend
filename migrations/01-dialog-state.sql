--liquibase formatted sql
--changeset author:action

create table dialog_state
(
    id             bigint generated always as identity,
    state          int                      not null,
    last_update_at timestamp with time zone not null,

    unique (id)
);
