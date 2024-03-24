create table chat
(
    uri_id     bigint not null,
    tg_chat_id bigint not null,

    foreign key (uri_id) references uri(id)
        [ON DELETE {CASCADE|RESTRICT}]
        [ON UPDATE {CASCADE|RESTRICT}]
)
