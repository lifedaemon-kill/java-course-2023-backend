create table chat
(
    uri_id     bigint not null,
    tg_chat_id bigint not null,

    foreign key (uri_id) references scrapper.uri(id)
        ON DELETE CASCADE
)
