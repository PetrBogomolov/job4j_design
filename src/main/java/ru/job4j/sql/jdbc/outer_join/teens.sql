-- создание таблицы "подростки"
CREATE TABLE teens
(
    id     serial primary key,
    name   varchar(55),
    gender varchar(10)
);

-- заполнение таблицы
INSERT INTO teens(name, gender) VALUES ('Oleg', 'man');
INSERT INTO teens(name, gender) VALUES ('Olga', 'women');
INSERT INTO teens(name, gender) VALUES ('Petr', 'man');
INSERT INTO teens(name, gender) VALUES ('Maria', 'women');
INSERT INTO teens(name, gender) VALUES ('Ivan', 'man');
INSERT INTO teens(name, gender) VALUES ('Roman', 'man');
INSERT INTO teens(name, gender) VALUES ('Irina', 'women');
INSERT INTO teens(name, gender) VALUES ('Daria', 'women');
INSERT INTO teens(name, gender) VALUES ('Dmitrii', 'man');
INSERT INTO teens(name, gender) VALUES ('Maxim', 'man');

-- вывод всех возможных вариантов разнополых пар
SELECT t1.name, t2.name from teens t1 cross join teens t2
WHERE t1.gender NOT LIKE t2.gender;
