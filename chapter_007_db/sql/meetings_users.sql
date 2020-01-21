-- Создание таблиц:

-- 1. Есть таблица встреч(id, name), есть таблица юзеров(id, name).
CREATE TABLE meeting (
	id serial PRIMARY KEY,
	"name" text
);
CREATE TABLE "user" (
	id serial PRIMARY KEY,
	"name" text
);

-- Нужно доработать модель базы данных, так чтобы пользователи могли учавствовать во встречах.
-- У каждого участника встречи может быть разный статус участия - например подтвердил участие, отклонил.
CREATE TABLE user_meeting (
	meeting_id int NOT NULL REFERENCES meeting(id),
	user_id int NOT NULL REFERENCES "user"(id),
	status text CHECK (status IN ('confirmed', 'rejected')),
	PRIMARY KEY (meeting_id, user_id)
);

-- Наполнение таблиц
INSERT INTO "user" ("name") VALUES ('Jack Sparrow'), ('Davy Jones'), ('Hector Barbossa');
INSERT INTO meeting ("name") VALUES ('Search ships'), ('Destroy ships'), ('Build ships');
INSERT INTO user_meeting VALUES (1, 1, 'confirmed'), (1, 2, 'confirmed'), (1, 3, 'confirmed');
INSERT INTO user_meeting VALUES (2, 1, 'rejected'), (2, 2, 'confirmed'), (2, 3, 'confirmed');

-- 2. Нужно написать запрос,
-- который получит список всех заявок и количество подтвердивших участников.
SELECT meeting."name", count(user_meeting.user_id)
FROM meeting
JOIN user_meeting ON meeting.id = user_meeting.meeting_id
WHERE user_meeting.status = 'confirmed'
GROUP BY meeting."name";

-- 3. Нужно получить все совещания, где не было ни одной заявки на посещения
SELECT meeting."name"
FROM meeting
LEFT JOIN user_meeting ON meeting.id = user_meeting.meeting_id
WHERE user_meeting.user_id IS NULL;
