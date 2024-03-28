--liquibase formatted sql
--changeset author:action

create table uri
(
    id         bigint generated always as identity,
    uri        text                     not null,
    created_at timestamp with time zone not null,
    unique (uri),
    primary key (id)
);
