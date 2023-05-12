create table users
(
    id           serial      not null
        constraint id_user_pk
        primary key,
    name         varchar(60) not null,
    surname      varchar(60) not null,
    phone_number varchar     not null,
    email        varchar(60) not null,
    password     varchar(60) not null,
    role         varchar     not null
);