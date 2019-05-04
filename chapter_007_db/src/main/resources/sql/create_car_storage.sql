-- create database car_storage;

-- Кузов
create table body_type
(
    body_type_id serial primary key,
    name         varchar(30)
);

-- Двигатель
create table engine
(
    engine_id   serial primary key,
    description varchar(80)
);

-- Коробка передач
create table gearbox
(
    gearbox_id  serial primary key,
    description varchar(30)
);

-- Машина
create table car
(
    car_id       serial primary key,
    name         varchar(30),
    body_type_id int references body_type (body_type_id),
    engine_id    int references engine (engine_id),
    gearbox_id   int references gearbox (gearbox_id)
);

insert into body_type (name)
values ('Седан'),
       ('Лифтбек'),
       ('Внедорожник 5 дв.'),
       ('Хэтчбек 5 дв.');

insert into engine (description)
values ('2.4 л / 152 л.с. / Бензин'),
       ('2.0 л / 220 л.с. / Бензин'),
       ('2.0 л / 184 л.с. / Бензин'),
       ('1.4 л / 107 л.с. / Бензин'),
       ('2.0 л / 147 л.с. / Бензин'),
       ('1.6 л / 105 л.с. / Бензин');

insert into gearbox (description)
values ('Автоматическая'),
       ('Роботизированная'),
       ('Механическая'),
       ('Бесступенчатая');

insert into car (name, body_type_id, engine_id, gearbox_id)
values ('Toyota Camry V (XV30)', 1, 1, 1),
       ('Skoda Octavia RS III', 2, 2, 2),
       ('BMW X4 I (F26) 20i', 3, 3, 1),
       ('Kia Rio III', 4, 4, 1),
       ('Mazda 6 II (GH)', 1, 5, 3);
