-- создание таблицы "департаменты"
CREATE TABLE departments
(
    id   serial primary key,
    name varchar(255)
);

-- создание таблицы "работники"
CREATE TABLE emploees
(
    id          serial primary key,
    name        varchar(55),
    depart_name int references departments (id)
);

INSERT INTO departments(name) VALUES ('налоговый');
INSERT INTO departments(name) VALUES ('финансовый');
INSERT INTO departments(name) VALUES ('полицейский');
INSERT INTO departments(name) VALUES ('международный');
INSERT INTO departments(name) VALUES ('образовательный');

INSERT INTO emploees(name, depart_name) VALUES ('Олег', 1);
INSERT INTO emploees(name, depart_name) VALUES ('Дмитрий', 2);
INSERT INTO emploees(name, depart_name) VALUES ('Петр', 3);
INSERT INTO emploees(name, depart_name) VALUES ('Иван', 4);
INSERT INTO emploees(name, depart_name) VALUES ('Мария', null);
INSERT INTO emploees(name, depart_name) VALUES ('Анастасия', 5);
INSERT INTO emploees(name, depart_name) VALUES ('Ольга', 1);
INSERT INTO emploees(name, depart_name) VALUES ('Роман', null);
INSERT INTO emploees(name, depart_name) VALUES ('Александр', 3);
INSERT INTO emploees(name, depart_name) VALUES ('Марина', null);

-- left join
select e.name, d.name from emploees e left join departments d on e.depart_name = d.id;

-- right join
select d.name, e.name from emploees e right join departments d on e.depart_name = d.id;

-- full join
select e.name, d.name from emploees e full join departments d on e.depart_name = d.id;

-- cross join
select e.name, e.depart_name, d.name from emploees e cross join departments d;

-- работники, не относящиеся ни к одному департаменту
select e.name as не_работают_в_департаменте from emploees e left join departments d on e.depart_name = d.id
where d.id is null;

-- left join и right join, которые дают одинаковый результат
select e.name, d.name from emploees e left join departments d on e.depart_name = d.id;
select e.name, d.name from departments d right join emploees e on e.depart_name = d.id;
