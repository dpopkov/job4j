-- create database test_items_db;

-- Connect to test_items_db before creating tables
create table permission (
  id   serial primary key,
  name varchar(20)
);

create table role (
  id   serial primary key,
  name varchar(20)
);

create table role_permission (
  role_id       int references role (id),
  permission_id int references permission (id),
  primary key (role_id, permission_id)
);

create table "user" (
  id       serial primary key,
  login    varchar(30) not null,
  password varchar(30) not null,
  role_id  int         not null references role (id)
);

create table category (
  id   serial primary key,
  name varchar(30) not null
);

create table state (
  id   serial primary key,
  name varchar(30) not null
);

create table item (
  id          serial primary key,
  title       varchar(128) not null,
  body        text         not null,
  created     timestamp    not null default now(),
  creator_id  int          not null references "user" (id),
  category_id int references category (id),
  state_id    int references state (id)
);

create table attachment (
  id         serial primary key,
  name       varchar(128) not null,
  body       bytea,
  created    timestamp    not null default now(),
  creator_id int          not null references "user" (id)
);

create table comment (
  id        serial primary key,
  body      text,
  created   timestamp not null default now(),
  author_id int       not null references "user" (id)
);

insert into role (name) values ('admin');
insert into role (name) values ('guest');

insert into permission (name) values ('read');
insert into permission (name) values ('comment');
insert into permission (name) values ('attach');

insert into role_permission (role_id, permission_id) values (1, 1);
insert into role_permission (role_id, permission_id) values (1, 2);
insert into role_permission (role_id, permission_id) values (1, 3);
insert into role_permission (role_id, permission_id) values (2, 1);

insert into "user" (login, password, role_id) values ('admin', 'admin', 1);

insert into category (name) values ('development');
insert into category (name) values ('testing');
insert into state (name) values ('state1');

insert into attachment (name, body, created, creator_id) values ('attachment-name', '\x54455354', now(), 1);

insert into comment (body, created, author_id) values ('comment-body', now(), 1);

insert into item (title, body, created, creator_id, category_id, state_id) values ('title1', 'body1', now(), 1, 1, 1);
