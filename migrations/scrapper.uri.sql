create table uri
(
    id         bigint primary key generated always as identity,
    uri        text unique              not null,
    created_at timestamp with time zone not null
)
