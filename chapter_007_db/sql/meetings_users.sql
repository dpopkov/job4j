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
	status text CHECK (status IN ('accept', 'cancel')),
	PRIMARY KEY (meeting_id, user_id)
);

-- Наполнение таблиц
INSERT INTO "user" ("name") VALUES ('Jack Sparrow'), ('Davy Jones'), ('Hector Barbossa');
INSERT INTO meeting ("name") VALUES ('Search ships'), ('Destroy ships'), ('Build ships');
INSERT INTO user_meeting VALUES (1, 1, 'accept'), (1, 2, 'accept'), (1, 3, 'accept');
INSERT INTO user_meeting VALUES (2, 1, 'cancel'), (2, 2, 'accept'), (2, 3, 'accept');

-- 2. Нужно написать запрос,
-- который получит список всех заявок и количество подтвердивших участников.
SELECT meeting."name", count(user_meeting.user_id)
FROM meeting
JOIN user_meeting ON meeting.id = user_meeting.meeting_id
WHERE user_meeting.status = 'accept'
GROUP BY meeting."name";

-- 3. Нужно получить все совещания, где не было ни одной заявки на посещения
SELECT meeting."name"
FROM meeting
LEFT JOIN user_meeting ON meeting.id = user_meeting.meeting_id
WHERE user_meeting.user_id IS NULL;

-- Вариант получения всех совещаний, где либо не было ни одной заявки, либо у заявки не было определенного статуса
SELECT meeting.name
FROM meeting
LEFT JOIN user_meeting AS um1 ON meeting.id = um1.meeting_id
GROUP BY meeting.name, um1.meeting_id
HAVING (
	SELECT count(*) FROM user_meeting AS um2
	WHERE um2.meeting_id = um1.meeting_id AND um2.status IN ('accept', 'cancel')
) = 0;
-- Упрощеннй, без группировки:
SELECT DISTINCT meeting.name
FROM meeting
LEFT JOIN user_meeting AS um1 ON meeting.id = um1.meeting_id
WHERE (
	SELECT count(*) FROM user_meeting AS um2
	WHERE um2.meeting_id = um1.meeting_id AND um2.status IN ('accept', 'cancel')
) = 0;
--Более упрощенный вариант, который дает такой же результат:
SELECT meeting.name
FROM meeting
WHERE (
	SELECT count(*) FROM user_meeting
	WHERE meeting_id = meeting.id AND status IN ('accept', 'cancel')
) = 0;
--Этот запрос возвращает те совещания, где совсем не было заявок, либо у заявок пустой статус (NULL)

--Если нужно получить все совещания, где нет ни одной подвтержденной заявки, тогда

--Вариант 1:
SELECT meeting.name FROM meeting
WHERE (
	SELECT count(*) FROM user_meeting
	WHERE meeting_id = meeting.id AND status = 'accept'
) = 0;

--Вариант 2:
SELECT name FROM meeting
EXCEPT
SELECT DISTINCT meeting.name FROM meeting
LEFT JOIN user_meeting ON meeting.id = user_meeting.meeting_id
WHERE status = 'accept';