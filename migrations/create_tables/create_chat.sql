--liquibase formatted sql
--changeset action::author

create table chat
(
    tg_chat_id     bigint                   not null,
    state          int                      not null,
    last_update_at timestamp with time zone not null,

    unique (tg_chat_id),
    primary key (tg_chat_id)
);
