create table if not exists vacancy
(
    id          serial primary key,
    name        varchar(256) not null unique,
    description text         not null,
    link        varchar(256) not null,
    created     timestamp    not null,
    modified    timestamp    not null
);
