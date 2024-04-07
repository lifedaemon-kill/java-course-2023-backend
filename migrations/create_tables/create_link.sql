--liquibase formatted sql
--changeset action::author

create table link
(
    id              bigint generated always as identity,
    url             text                     not null,
    answers_count   int                      not null,
    last_update_at timestamp with time zone not null,

    unique (url),
    primary key (id)
);
