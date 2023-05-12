create table exchange_statistics
(
    id            serial  not null
        constraint exchange_statistics_id_pk
        primary key,
    currency_name varchar not null,
    currency_buy  varchar not null,
    currency_sell varchar not null,
    currency_date date    not null
);