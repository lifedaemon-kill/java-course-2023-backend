--liquibase formatted sql
--changeset author:action

create table LinksChats
(
    id         bigint generated always as identity,
    url_id     bigint not null,
    tg_chat_id bigint not null,

    foreign key (url_id) references Link (id),
    foreign key (tg_chat_id) references Chat (tg_chat_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
