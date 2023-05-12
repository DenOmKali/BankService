create table cards
(
    id              serial  not null
        constraint credit_cards_id_pk
        primary key,
    card_type       varchar not null,
    card_number     varchar not null,
    date_creation   date    not null,
    date_expiration date    not null,
    cvv2            varchar not null,
    pin_code        varchar not null,
    percentage      integer,
    arrears         integer,
    parent_email    varchar,
    balance         integer not null,
    valid           boolean not null,
    user_id         integer not null constraint credit_cards_user_id___fk references users
);