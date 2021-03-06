-- создание таблицы "кузов"
CREATE TABLE body
(
    id   serial primary key,
    name varchar(55)
);

-- создание таблицы "двигатель"
CREATE TABLE engine
(
    id   serial primary key,
    name varchar(55)
);

-- создание таблицы "коробка передач"
CREATE TABLE transmission
(
    id   serial primary key,
    name varchar(55)
);

-- создание таблицы "машина"
CREATE TABLE car
(
    id              serial primary key,
    name            varchar(55),
    body_id         int references body (id),
    engine_id       int references engine (id),
    transmission_id int references transmission (id)
);
-- заполнение таблицы "кузов"
INSERT INTO body(name) VALUES ('sedan');
INSERT INTO body(name) VALUES ('jeep');
INSERT INTO body(name) VALUES ('hatchback');
INSERT INTO body(name) VALUES ('coupe');

-- заполнение таблицы "двигатель"
INSERT INTO engine(name) VALUES ('benzine');
INSERT INTO engine(name) VALUES ('diesel');
INSERT INTO engine(name) VALUES ('hybrid');

-- заполнение таблицы "коробка передач"
INSERT INTO transmission(name) VALUES ('mechanical');
INSERT INTO transmission(name) VALUES ('automatic');
INSERT INTO transmission(name) VALUES ('robotic');

-- заполнение таблицы "машина"
INSERT INTO car (name, body_id, engine_id, transmission_id) VALUES ('BMW', 1, 1, 2);
INSERT INTO car (name, body_id, engine_id, transmission_id) VALUES ('VOLVO', 2, 3, 3);
INSERT INTO car (name, body_id, engine_id, transmission_id) VALUES ('TOYOTA', 2, 2, 2);
INSERT INTO car (name, body_id, engine_id, transmission_id) VALUES ('MERCEDES', 4, 1, 3);
INSERT INTO car (name, body_id, engine_id, transmission_id) VALUES ('BMW', 4, 1, 2);
INSERT INTO car (name, body_id, engine_id, transmission_id) VALUES ('VOLVO', 2, 1, 2);
INSERT INTO car (name, body_id, engine_id, transmission_id) VALUES ('TOYOTA', 1, 2, 3);
INSERT INTO car (name, body_id, engine_id, transmission_id) VALUES ('MERCEDES', 2, 2, 2);
INSERT INTO car (name, body_id, engine_id, transmission_id) VALUES ('TOYOTA', 3, 3, 3);
INSERT INTO car (name, body_id, engine_id, transmission_id) VALUES ('BMW', null, 1, 2);
INSERT INTO car (name, body_id, engine_id, transmission_id) VALUES ('TOYOTA', 3, null, 3);
INSERT INTO car (name, body_id, engine_id, transmission_id) VALUES ('VOLVO', 3, 1, null);
INSERT INTO car (name, body_id, engine_id, transmission_id) VALUES ('MERCEDES', 2, null, 2);
INSERT INTO car (name, body_id, engine_id, transmission_id) VALUES ('TOYOTA', null, 3, 3);
INSERT INTO car (name, body_id, engine_id, transmission_id) VALUES ('BMW', 4, 1, null);
INSERT INTO car (name, body_id, engine_id, transmission_id) VALUES ('VOLVO', 2, null, 3);

-- машины со всеми деталями
SELECT c.name марка, b.name кузов, e.name двигатель, t.name коробка_передач
FROM car c
         JOIN body b ON c.body_id = b.id
         JOIN engine e ON c.engine_id = e.id
         JOIN transmission t ON c.transmission_id = t.id;

-- машины у которых нет кузова
SELECT c.name марка, b.name кузов
FROM car c
         LEFT JOIN body b ON c.body_id = b.id
WHERE b.id IS NULL;

-- машины у которых нет двигателя
SELECT c.name марка, e.name двигатель
FROM car c
         LEFT JOIN engine e ON c.engine_id = e.id
WHERE e.id IS NULL;

-- машины у которых нет коробки передач
SELECT c.name марка, t.name коробка_передач
FROM car c
         LEFT JOIN transmission t ON c.transmission_id = t.id
WHERE t.id IS NULL;
