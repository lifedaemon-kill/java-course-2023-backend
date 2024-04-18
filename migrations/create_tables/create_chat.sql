--liquibase formatted sql
--changeset author:action

create table Chat
(
    tg_chat_id     bigint                   not null,
    state          int                      not null,
    last_update_at timestamp with time zone not null,

    primary key (tg_chat_id)
);
