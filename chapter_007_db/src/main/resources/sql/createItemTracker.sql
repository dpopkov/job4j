create table if not exists item (
    id serial primary key,
    name varchar(128),
    description varchar(128),
    created bigint
);
