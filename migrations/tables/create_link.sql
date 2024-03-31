--liquibase formatted sql
--changeset author:action

create table link
(
    id            bigint generated always as identity,
    url           text                     not null,
    answers_count int                      not null,
    created_at    timestamp with time zone not null,
    unique (url),
    primary key (id)
);
