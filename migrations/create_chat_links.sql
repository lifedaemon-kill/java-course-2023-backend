--liquibase formatted sql
--changeset author:action

create table chat_links
(
    uri_id     bigint not null,
    tg_chat_id bigint not null,

    foreign key (uri_id) references link (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
