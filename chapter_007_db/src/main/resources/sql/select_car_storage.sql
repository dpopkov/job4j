-- 1. Вывести список всех машин и все привязанные к ним детали.
select c.name as Car, bt.name as BodyType, e.description as Engine, gb.description as Gearbox
from car as c
         inner join body_type as bt
                    on c.body_type_id = bt.body_type_id
         inner join engine as e
                    on c.engine_id = e.engine_id
         inner join gearbox as gb
                    on c.gearbox_id = gb.gearbox_id;

-- 2. Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.
-- Неиспользуемые кузова
select bt.name
from car as c
         right join body_type as bt
                    on c.body_type_id = bt.body_type_id
where c.car_id is null;

-- Неиспользуемые двигатели
select e.description
from car as c
         right join engine as e
                    on c.engine_id = e.engine_id
where c.car_id is null;

-- Неиспользуемые коробки передач
select gb.description
from car as c
         right join gearbox as gb
                    on c.gearbox_id = gb.gearbox_id
where c.car_id is null;
