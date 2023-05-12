create table news
(
    id      serial      not null
        constraint id_pk
        primary key,
    title   varchar     not null,
    content varchar     not null,
    img_url varchar(200) not null,
    date    date        not null
);
