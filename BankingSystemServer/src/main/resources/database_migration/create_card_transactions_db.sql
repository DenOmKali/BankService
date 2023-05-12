create table card_transactions
(
    id               serial  not null
        constraint card_transactions_id_pk
            primary key,
    sender_id        integer not null
        constraint card_transactions_sender_id___fk
            references users,
    receiver_id      integer not null
        constraint card_transactions_receiver_id___fk
            references users,
    sender_card_id   integer not null
        constraint card_transactions_sender_card_id___fk
            references cards,
    receiver_card_id integer not null
        constraint card_transactions_receiver_card_id___fk
            references cards,
    amount           integer not null,
    message          varchar
);

